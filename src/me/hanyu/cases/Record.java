package me.hanyu.cases;

public class Record {
	String cmte_id; 
	String zip_code;
	String transaction_date;
	String transaction_amount;
	String other_id;
	
	public Record(String cmte_id, String zip_code, String transaction_date, String transaction_amount,
			String other_id) {
		this.cmte_id = cmte_id;
		this.zip_code = zip_code;
		this.transaction_date = transaction_date;
		this.transaction_amount = transaction_amount;
		this.other_id = other_id;
	}
	
	public String getCmte_id() {
		return cmte_id;
	}
	public void setCmte_id(String cmte_id) {
		this.cmte_id = cmte_id;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getTransaction_date() {
		return transaction_date;
	}
	public void setTransaction_date(String transaction_date) {
		this.transaction_date = transaction_date;
	}
	public String getTransaction_amount() {
		return transaction_amount;
	}
	public void setTransaction_amount(String transaction_amount) {
		this.transaction_amount = transaction_amount;
	}
	public String getOther_id() {
		return other_id;
	}
	public void setOther_id(String other_id) {
		this.other_id = other_id;
	}
	
}
