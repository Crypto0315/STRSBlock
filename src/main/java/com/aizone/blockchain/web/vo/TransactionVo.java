package com.aizone.blockchain.web.vo;

import java.math.BigDecimal;

/**
 * 发送交易参数 VO
 *
 * @since 24-6-6
 */
public class TransactionVo {

	/**
	 * 付款人地址
	 */
	private String sender;
	/**
	 * 收款人地址
	 */
	private String recipient;
	/**
	 * 交易金额
	 */
	private BigDecimal amount;
	/**
	 * 付款人私钥1
	 */
	private String privateKey1;

	/**
	 * 付款人私钥2
	 */
	private String privateKey2;
	/**
	 * 附加数据
	 */
	private String data;

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getPrivateKey1() {
		return privateKey1;
	}

	public void setPrivateKey1(String privateKey1) {
		this.privateKey1 = privateKey1;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getPrivateKey2() {
		return privateKey2;
	}

	public void setPrivateKey2(String privateKey2) {
		this.privateKey2 = privateKey2;
	}
}
