package me.hanyu.filter.impl;

import me.hanyu.filter.Filter;
import me.hanyu.utils.ValidUtils;

public class ZipFilter implements Filter {

	@Override
	public String filter(String line) {
		String[] lineSeg = line.split("|");
		StringBuffer buffer = new StringBuffer();
		String cmte_id = lineSeg[0];
		String zip_code = lineSeg[10];
		String transaction_date = lineSeg[13];
		String transaction_amount = lineSeg[14];
		String other_id = lineSeg[15];
		
		if(!ValidUtils.isSetEmpty(other_id)){
			return "";
		}
		

	}

}
