package me.hanyu.utils;

import me.hanyu.model.Record;

public class StringUtils {
	/**
	 * Build zip output string from record
	 * */
	public static String buildZipOutput(Record record, int median, int totalTransactionNumber, int totalTransactionAmount){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(record.getCmteId()).append("|").append(record.getZipCode()).append("|")
						.append(median).append("|").append(totalTransactionNumber).append("|").append(totalTransactionAmount);
		return stringBuilder.toString();
	}
	
	/**
	 * Build date output string
	 * */
	public static String buildDateOutput(String recipient, String date, int median, int totalTransactionNumber, int totalTransactionAmount){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(recipient).append("|").append(date).append("|")
						.append(median).append("|").append(totalTransactionNumber).append("|").append(totalTransactionAmount);
		return stringBuilder.toString();
	}
}
