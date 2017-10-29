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

import me.hanyu.constants.Constants;
import me.hanyu.models.Analyzer;
import me.hanyu.models.Record;
import me.hanyu.utils.ListUtils;
import me.hanyu.utils.StringUtils;
import me.hanyu.utils.ValidateUtils;

public class DateAnalyzer extends Analyzer{
	String output_file_dir = Constants.OUTPUT_FILE_DATE_DIR;
	private Map<String, Map<String,List<Integer>>> recipientToDateDonations;
	private FileOutputStream outputStream;
	private BufferedWriter bw;
	
	public DateAnalyzer() throws FileNotFoundException{
		recipientToDateDonations = new TreeMap<String, Map<String,List<Integer>>>();
		outputStream = new FileOutputStream(output_file_dir);
		bw = new BufferedWriter(new OutputStreamWriter(outputStream));
	}
	
	/**
	 * Filter invalid records for this analyzer
	 */
	@Override
	public Record filter(String line) {
		String[] lineSeg = line.split("\\|");
		String cmte_id = lineSeg[0];
		String transaction_date = lineSeg[13];
		String transaction_amount = lineSeg[14];
		String other_id = lineSeg[15];
		//Filter invalid records
		if(!ValidateUtils.isEmpty(other_id)){
			return null;
		}
		if(!ValidateUtils.isValidDate(transaction_date)){
			return null;
		}
		if(ValidateUtils.isEmpty(cmte_id) || ValidateUtils.isEmpty(transaction_amount)){
			return null;
		}

		Record record = new Record(cmte_id, transaction_date, Integer.parseInt(transaction_amount), other_id);
		return record;
	}
	
	/**
	 * Save record to data structure for computation
	 */
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

	/**
	 * Write records to file
	 */
	private void writeToFile() {
		for(String recipient : recipientToDateDonations.keySet()){
			for(String date : recipientToDateDonations.get(recipient).keySet()){
				List<Integer> donations = recipientToDateDonations.get(recipient).get(date); 
				int median = ListUtils.getMedian(donations);
				int totalTransactionNumber = donations.size();
				int totalTransactionAmount = ListUtils.sum(donations);
				String outputString = StringUtils.buildDateOutput(recipient, date, median,totalTransactionNumber, totalTransactionAmount);
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
