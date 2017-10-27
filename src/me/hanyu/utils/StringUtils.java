package me.hanyu.utils;

import me.hanyu.cases.Record;

public class StringUtils {
	/**
	 * Build output string from record
	 * */
	public static String buildOutput(Record record, int median, int totalTransactionNumber, int totalTransactionAmount){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(record.getCmte_id()).append("|").append(record.getZip_code()).append("|")
						.append(median).append("|").append(totalTransactionNumber).append("|").append(totalTransactionAmount);
		return stringBuilder.toString();
	}
}
