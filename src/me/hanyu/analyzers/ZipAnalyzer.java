package me.hanyu.analyzers;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hanyu.cases.Analyzer;
import me.hanyu.cases.Record;
import me.hanyu.filter.Filter;
import me.hanyu.filter.impl.ZipFilter;

public class ZipAnalyzer extends Analyzer{
	Filter filter;
	Map<String, List<Integer>> zipToDonations;
	public ZipAnalyzer(){
		filter = new ZipFilter();
		zipToDonations = new HashMap<String, List<Integer>>();
	}

	@Override
	public Filter getFilter() {
		return this.filter;
	}

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int getMedian(Record record) {
		String zip = record.getZip_code();
		List<Integer> donations;
		if(zipToDonations.containsKey(zip)){
			donations = zipToDonations.get(zip);
		}else{
			donations = new ArrayList<Integer>();
		}
		int insertIndex = Collections.binarySearch(donations, record.getTransaction_amount());
		if(insertIndex < 0){
			insertIndex = -insertIndex + 1;
		}
		donations.add(insertIndex, record.getTransaction_amount());
		zipToDonations.put(zip, donations);
		int median;
		int donationSize = donations.size();
		if(donationSize % 2 == 1){
			median = donations.get(donationSize / 2); 
		}else{
			double doubleMedian = (double)(donations.get(donationSize / 2 - 1) + donations.get(donationSize / 2 )) / 2;
			median = new BigDecimal(String.valueOf(doubleMedian)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		}
		return median;
	}

}
