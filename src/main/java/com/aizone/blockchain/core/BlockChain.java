package com.aizone.blockchain.core;

import com.aizone.blockchain.db.DBAccess;
import com.aizone.blockchain.encrypt.HashUtils;
import com.aizone.blockchain.encrypt.SignUtils;
import com.aizone.blockchain.encrypt.WalletUtils;
import com.aizone.blockchain.event.MineBlockEvent;
import com.aizone.blockchain.event.SendTransactionEvent;
import com.aizone.blockchain.mine.Miner;
import com.aizone.blockchain.net.ApplicationContextProvider;
import com.aizone.blockchain.net.base.Node;
import com.aizone.blockchain.net.client.AppClient;
import com.aizone.blockchain.wallet.Account;
import com.google.common.base.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * 区块链主类
 * @since 24-6-6
 */
@Component
public class BlockChain {

    private static Logger logger = LoggerFactory.getLogger(BlockChain.class);

    @Autowired
    private DBAccess dbAccess;

    @Autowired
    private AppClient appClient;

    @Autowired
    private Miner miner;

    @Autowired
    private TransactionPool transactionPool;

    @Autowired
    private TransactionExecutor executor;
    /**
     * 挖取一个区块
     * @return
     */
    public Block mining() throws Exception {

        Optional<Block> lastBlock = getLastBlock();
        Block block = miner.newBlock(lastBlock);
        transactionPool.getTransactions().forEach(e -> block.getBody().addTransaction(e));
        executor.run(block);

        //清空交易池
//        transactionPool.clearTransactions();
        //存储区块
        dbAccess.putLastBlockIndex(block.getHeader().getIndex());
        dbAccess.putBlock(block);
        logger.info("Find a New Block, {}", block);

        //触发挖矿事件，并等待其他节点确认区块
        ApplicationContextProvider.publishEvent(new MineBlockEvent(block));
        return block;
    }

    /**
     * 发送交易
     * @param transaction
     * @param privateKey1 付款人私钥，用来签名交易
     * @param privateKey2 付款人私钥，用来签名交易
     */
    public Transaction sendTransaction(Transaction transaction, String privateKey1, String privateKey2) throws Exception {
        //从数据库查询到用户的公钥
        Optional<Account> sender = dbAccess.getAccount(transaction.getSender());
        Optional<Account> recipient = dbAccess.getAccount(transaction.getRecipient());
        if (!sender.isPresent()) {
            throw new RuntimeException("付款人地址不存在");
        }
        if (!recipient.isPresent()) {
            throw new RuntimeException("收款人地址不存在");
        }
        transaction.setPublicKey(sender.get().getPublicKey());
        transaction.setTxHash(HashUtils.sha256Hex(transaction.toString()));
        Map<String, Object> stringObjectMap = WalletUtils.setStep(sender);
        transaction.setStrsStep(stringObjectMap);

        //对交易数据进行环签名
        /**
         * 调用签名算法   PK  SK1 SK2
         * function [C, z1, z2, theta, t0, h0, Lpk] = signature(n, q, h, f, PK, s1, s2, N, miu)
         */
        Object[] strsSign = SignUtils.strsSign(stringObjectMap, sender.get().getPublicKey(), privateKey1,privateKey2);
        transaction.setStrsSign(strsSign);
        transaction.setStrsSignature(WalletUtils.encodeObjectToBase58WithCompression(strsSign));


        /**
         * 验证签名
         */
        String result = SignUtils.strsVerify(stringObjectMap,strsSign);
        if (!"1".equals(result)){
            throw new RuntimeException("签名验证失败，非法的私钥");
        }

        //打包数据到交易池
        transactionPool.addTransaction(transaction);

        //触发交易事件，向全网广播交易，并等待确认
        ApplicationContextProvider.publishEvent(new SendTransactionEvent(transaction));
        return transaction;
    }

    /**
     * 获取最后一个区块
     * @return
     */
    public Optional<Block> getLastBlock() {
        return dbAccess.getLastBlock();
    }

    /**
     * 添加一个节点
     * @param ip
     * @param port
     * @return
     */
    public void addNode(String ip, int port) throws Exception {

        appClient.addNode(ip, port);
        Node node = new Node(ip, port);
        dbAccess.addNode(node);
    }
}
