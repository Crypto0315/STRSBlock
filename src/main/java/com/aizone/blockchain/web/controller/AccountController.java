package com.aizone.blockchain.web.controller;

import com.aizone.blockchain.db.DBAccess;
import com.aizone.blockchain.encrypt.WalletUtils;
import com.aizone.blockchain.utils.JsonVo;
import com.aizone.blockchain.wallet.Account;
import com.aizone.blockchain.wallet.Personal;
import com.aizone.blockchain.web.vo.AccountVo;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @since 24-6-6
 */
@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private Personal personal;
    @Autowired
    private DBAccess dbAccess;

    /**
     * 创建账户
     * @param request
     * @return
     */
    @PostMapping("/new")
    public ModelAndView  newAccount(HttpServletRequest request) throws Exception {
        Account account = personal.STRSAccount();
        AccountVo vo = new AccountVo();
        BeanUtils.copyProperties(account, vo);
        // 创建 ModelAndView 对象，用于返回一个新的页面
        ModelAndView modelAndView = new ModelAndView("success"); // "account" 是视图名称
        modelAndView.addObject("account", account); // 将 account 对象传递到视图中
        return modelAndView;
    }

    @GetMapping("/get_coinbase")
    public String showCoinbasePage() {
        return "coinbase"; // 返回Thymeleaf模板页面的名称
    }

    /**
     * 获取挖矿账号
     * @param request
     * @return
     */
    @PostMapping("/get_coinbase")
    @ResponseBody
    public JsonVo getAccount(HttpServletRequest request) {

        Optional<String> coinBaseAccount = dbAccess.getCoinBaseAddress();
        JsonVo success = JsonVo.success();
        if (coinBaseAccount.isPresent()) {
            success.setItem(coinBaseAccount.get());
        } else {
            success.setMessage("CoinBase Account is not created");
        }
        return success;
    }


    @GetMapping("/detail")
    public String showAccountDetailPage() {
        return "detail"; // 返回 Thymeleaf 模板页面的名称
    }


    /**
     * 获取指定的钱包账户
     *
     * @return
     */
    @PostMapping("/getAccount")
    @ResponseBody
    public JsonVo coinbase(@RequestBody Map<String, String> params) {
        Preconditions.checkNotNull(params.get("address"), "address can not be null");
        Optional<Account> coinBaseAccount = dbAccess.getAccount(params.get("address"));


        JsonVo success = JsonVo.success();
        if (coinBaseAccount.isPresent()) {
            Account account = coinBaseAccount.get();
            HashMap<Object, Object> map = new HashMap<>();
            map.put("address",account.getAddress());
            map.put("balance",account.getBalance());
            map.put("locked",account.isLocked());
            map.put("miu",account.getMiu());
            map.put("publicKey",WalletUtils.encodeObjectToBase58WithCompression(account.getPublicKey()));
            map.put("privateKey1",account.getPrivateKey1());
            map.put("privateKey2",account.getPrivateKey2());
            success.setItem(map);
        } else {
            success.setMessage("Account does not exist");
        }
        return success;
    }

    @GetMapping("/set_coinbase")
    public String showSetCoinbasePage() {
        return "set_coinbase"; // 返回Thymeleaf模板页面的名称
    }
    /**
     * 设置挖矿账号
     * @return
     */
    @PostMapping("/set_coinbase")
    @ResponseBody
    public JsonVo setCoinbase(@RequestBody Map<String, String> params) {

        Preconditions.checkNotNull(params.get("address"), "address can not be null");
        dbAccess.putCoinBaseAddress(params.get("address"));
        return JsonVo.success();
    }

    @GetMapping("/wallets")
    public String walletsPage() {
        return "wallets"; // wallets.html 是你的Thymeleaf模板页面
    }

    /**
     * 列出所有的账号
     * @param request
     * @return
     */
    @PostMapping("/list")
    @ResponseBody
    public JsonVo listAccounts(HttpServletRequest request) {
        List<Account> accounts = dbAccess.listAccounts();
        JsonVo success = JsonVo.success();
        success.setItem(accounts);
        return success;
    }
}
