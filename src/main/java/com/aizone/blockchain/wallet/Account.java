package com.aizone.blockchain.wallet;

import com.aizone.blockchain.encrypt.WalletUtils;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 钱包账户
 *
 * @since 24-6-6
 */
public class Account implements Serializable {

    /**
     * 钱包私钥
     */
    protected String privateKey1;

    /**
     * 钱包私钥
     */
    protected String privateKey2;

    /**
     * 钱包公钥
     */
    protected double[][] publicKey;
    protected Long n;

    protected Double q;
    protected double[][] h;

    protected double[][] f;
    protected Double N1;



    protected String miu;


    /**
     * 钱包地址
     */
    protected String address;
    /**
     * 账户余额
     */
    protected BigDecimal balance;
    /**
     * 账户锁定状态
     */
    protected boolean locked = false;

    public Account() {
    }

    public Account(String privateKey1, String privateKey2,double[][] publicKey) {
        this.privateKey1 = privateKey1;
        this.privateKey2 = privateKey2;
        this.publicKey = publicKey;
        this.address = WalletUtils.generateAddress(publicKey);
        this.balance = BigDecimal.ZERO;
    }

    public Account(String privateKey1, String privateKey2, double[][] publicKey, BigDecimal balance) {
        this.privateKey1 = privateKey1;
        this.privateKey2 = privateKey2;
        this.publicKey = publicKey;
        this.address = WalletUtils.generateAddress(publicKey);
        this.balance = balance;
    }

    public Account(double[][] publicKey,Long n, Double N1,String miu,Double q,double[][] h,double[][] f) {
        this.publicKey = publicKey;
        this.address = WalletUtils.generateAddress(publicKey);
        this.balance = BigDecimal.ZERO;
        this.n = n;
        this.N1 = N1;
        this.q = q;
        this.h = h;
        this.f = f;
        this.miu = miu;
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

    public double[][] getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(double[][] publicKey) {
        this.publicKey = publicKey;
    }

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

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public Long getN() {
        return n;
    }

    public void setN(Long n) {
        this.n = n;
    }


    public Double getQ() {
        return q;
    }

    public void setQ(Double q) {
        this.q = q;
    }

    public double[][] getF() {
        return f;
    }

    public void setF(double[][] f) {
        this.f = f;
    }

    public double[][] getH() {
        return h;
    }

    public void setH(double[][] h) {
        this.h = h;
    }

    public String getMiu() {
        return miu;
    }

    public void setMiu(String miu) {
        this.miu = miu;
    }

    public Double getN1() {
        return N1;
    }

    public void setN1(Double n1) {
        N1 = n1;
    }

    @Override
    public String toString() {
        return "Account{" +
                "privateKey1='" + privateKey1 + '\'' +
                "privateKey2='" + privateKey2 + '\'' +
                ", publicKey=" + WalletUtils.encodeObjectToBase58WithCompression(publicKey) +
                ", address='" + address + '\'' +
                ", balance=" + balance +
                '}';
    }
}
