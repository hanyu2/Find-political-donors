package me.hanyu.models;

public class Record {
	private String cmteId; 
	private String zipCode;
	private String transactionDate;
	private int transactionAmount;
	private String otherId;
	
	public Record(String cmte_id, String zip_code, String transaction_date, String transaction_amount,
			String other_id) {
		this.cmteId = cmte_id;
		this.zipCode = zip_code;
		this.transactionDate = transaction_date;
		this.transactionAmount = Integer.parseInt(transaction_amount);
		this.otherId = other_id;
	}
	
	public String getCmteId() {
		return cmteId;
	}

	public void setCmteId(String cmteId) {
		this.cmteId = cmteId;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getTransactionAmount() {
		return transactionAmount;
	}

	public void setTransactionAmount(int transactionAmount) {
		this.transactionAmount = transactionAmount;
	}

	public String getOtherId() {
		return otherId;
	}

	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
}
