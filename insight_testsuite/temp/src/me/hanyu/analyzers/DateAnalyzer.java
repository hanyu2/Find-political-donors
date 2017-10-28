package me.hanyu.analyzers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import me.hanyu.cases.Analyzer;
import me.hanyu.cases.Record;
import me.hanyu.constant.Constants;
import me.hanyu.filter.Filter;
import me.hanyu.filter.impl.DateFilter;
import me.hanyu.utils.ListUtils;
import me.hanyu.utils.StringUtils;

public class DateAnalyzer extends Analyzer{
	String output_file_dir = Constants.OUTPUT_FILE_DATE_DIR;
	private Filter filter;
	private Map<String, Map<String,List<Integer>>> recipientToDateDonations;
	private FileOutputStream outputStream;
	private BufferedWriter bw;
	
	public DateAnalyzer() throws FileNotFoundException{
		filter = new DateFilter();
		recipientToDateDonations = new TreeMap<String, Map<String,List<Integer>>>();
		outputStream = new FileOutputStream(output_file_dir);
		bw = new BufferedWriter(new OutputStreamWriter(outputStream));
	}
	
	@Override
	public Filter getFilter() {
		return this.filter;
	}
	
	@Override
	public void saveRecord(Record record) {
		String recipient = record.getCmteId();
		Map<String, List<Integer>> dateToDonations;
		if(recipientToDateDonations.containsKey(recipient)){
			dateToDonations = recipientToDateDonations.get(recipient);
			List<Integer> donations;
			if(dateToDonations.containsKey(record.getTransactionDate())){
				donations = dateToDonations.get(record.getTransactionDate());
			}else{
				donations = new ArrayList<Integer>();
			}
			ListUtils.insert(record.getTransactionAmount(), donations);
			dateToDonations.put(record.getTransactionDate(), donations);
		}else{
			dateToDonations = new TreeMap<String, List<Integer>>();
			List<Integer> donations = new ArrayList<Integer>();
			ListUtils.insert(record.getTransactionAmount(), donations);
			dateToDonations.put(record.getTransactionDate(), donations);
		}
		recipientToDateDonations.put(recipient, dateToDonations);
	}

	private void writeToFile() {
		for(String recipient : recipientToDateDonations.keySet()){
			for(String date : recipientToDateDonations.get(recipient).keySet()){
				List<Integer> donations = recipientToDateDonations.get(recipient).get(date); 
				int median = ListUtils.getMedian(donations);
				int totalTransactionNumber = donations.size();
				int totalTransactionAmount = ListUtils.sum(donations);
				String outputString = StringUtils.buildDateOutput(recipient, date, median,totalTransactionNumber, totalTransactionAmount);
				System.out.println(outputString);
				try {
					bw.flush();
					bw.write(outputString + "\n");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void close() {
		writeToFile();
		try {
			bw.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
