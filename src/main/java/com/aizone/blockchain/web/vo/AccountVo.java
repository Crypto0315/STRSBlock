package com.aizone.blockchain.web.vo;


import com.aizone.blockchain.wallet.Account;

import java.math.BigDecimal;

/**
 * account VO
 *  
 * @since 24-6-6
 */
public class AccountVo {

    private String privateKey1;
    private String privateKey2;


    /**
     * 钱包地址
     */
    protected String address;
    /**
     * 账户余额
     */
    protected BigDecimal balance;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public String getPrivateKey1() {
        return privateKey1;
    }

    public void setPrivateKey1(String privateKey1) {
        this.privateKey1 = privateKey1;
    }


    public String getPrivateKey2() {
        return privateKey2;
    }


    public void setPrivateKey2(String privateKey2) {
        this.privateKey2 = privateKey2;
    }

    @Override
    public String toString() {
        return "AccountVo{" +
                "address='" + getAddress() + '\'' +
                "privateKey1='" + getPrivateKey1() + '\'' +
                "privateKey2='" + getPrivateKey2() + '\'' +
                "balance='" + getBalance() + '\'' +
                '}';
    }


}
