package com.aizone.blockchain.core;import com.aizone.blockchain.db.DBAccess;import com.aizone.blockchain.encrypt.SignUtils;import com.aizone.blockchain.encrypt.WalletUtils;import com.aizone.blockchain.enums.TransactionStatusEnum;import com.aizone.blockchain.wallet.Account;import com.google.common.base.Optional;import org.springframework.beans.factory.annotation.Autowired;import org.springframework.stereotype.Component;import java.util.Map;/** * 交易执行器 * @since 24-6-6 */@Componentpublic class TransactionExecutor {    @Autowired    private DBAccess dbAccess;    @Autowired    private TransactionPool transactionPool;    /**     * 执行区块中的交易     * @param block     */    public void run(Block block) throws Exception {        for (Transaction transaction : block.getBody().getTransactions()) {            synchronized (this) {                Optional<Account> recipient = dbAccess.getAccount(transaction.getRecipient());                //挖矿奖励                if (null == transaction.getSender()) {                    recipient.get().setBalance(recipient.get().getBalance().add(transaction.getAmount()));                    dbAccess.putAccount(recipient.get());                    continue;                }                //账户转账                Optional<Account> sender = dbAccess.getAccount(transaction.getSender());                Map<String, Object> stringObjectMap = WalletUtils.setStep(sender);                transaction.setStrsStep(stringObjectMap);                /**                 * 验证签名                 */                String verify = SignUtils.strsVerify(stringObjectMap,transaction.getStrsSign());                //验证签名                if (!"1".equals(verify)){                    transaction.setStatus(TransactionStatusEnum.FAIL);                    transaction.setErrorMessage("交易签名错误");                    continue;                }                //验证账户余额                if (sender.get().getBalance().compareTo(transaction.getAmount()) == -1) {                    transaction.setStatus(TransactionStatusEnum.FAIL);                    transaction.setErrorMessage("账户余额不足");                    continue;                }                if (transaction.getStrsSign() != null && transaction.getStrsSign().length > 0) {                    transaction.setStrsSignature(WalletUtils.encodeObjectToBase58WithCompression(transaction.getStrsSign()));                }                //执行转账操作,更新账户余额                sender.get().setBalance(sender.get().getBalance().subtract(transaction.getAmount()));                recipient.get().setBalance(recipient.get().getBalance().add(transaction.getAmount()));                dbAccess.putAccount(sender.get());                dbAccess.putAccount(recipient.get());            }//end synchronize        }// end for        //清空交易池//        transactionPool.clearTransactions();    }}