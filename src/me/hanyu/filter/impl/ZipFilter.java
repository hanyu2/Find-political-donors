package me.hanyu.filter.impl;

import me.hanyu.cases.Record;
import me.hanyu.filter.Filter;
import me.hanyu.utils.ValidateUtils;

public class ZipFilter implements Filter {

	@Override
	public Record filter(String line) {
		String[] lineSeg = line.split("|");
		String cmte_id = lineSeg[0];
		String zip_code = lineSeg[10];
		String transaction_date = lineSeg[13];
		String transaction_amount = lineSeg[14];
		String other_id = lineSeg[15];
		
		if(!ValidateUtils.equalsEmpty(other_id)){
			return null;
		}
		if(!ValidateUtils.isValidDate(transaction_date)){
			return null;
		}
		if(ValidateUtils.isEmpty(cmte_id) || ValidateUtils.isEmpty(transaction_amount)){
			return null;
		}
		Record record = new Record(cmte_id, zip_code, transaction_date, transaction_amount, other_id);
		return record;
	}

}
