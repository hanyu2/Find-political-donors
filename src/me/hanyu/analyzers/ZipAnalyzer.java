package me.hanyu.analyzers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hanyu.constant.Constants;
import me.hanyu.model.Analyzer;
import me.hanyu.model.Record;
import me.hanyu.utils.ListUtils;
import me.hanyu.utils.StringUtils;
import me.hanyu.utils.ValidateUtils;

public class ZipAnalyzer extends Analyzer {
	String output_file_dir = Constants.OUTPUT_FILE_ZIP_DIR;
	private Map<String, List<Integer>> zipToDonations;
	private FileOutputStream outputStream;
	private BufferedWriter bw;

	public ZipAnalyzer() throws FileNotFoundException {
		zipToDonations = new HashMap<String, List<Integer>>();
		outputStream = new FileOutputStream(output_file_dir);
		bw = new BufferedWriter(new OutputStreamWriter(outputStream));
	}
	
	@Override
	public Record filter(String line) {
		String[] lineSeg = line.split("\\|");
		String cmte_id = lineSeg[0];
		String zip_code = lineSeg[10];
		String transaction_date = lineSeg[13];
		String transaction_amount = lineSeg[14];
		String other_id = lineSeg[15];
		
		if(!ValidateUtils.isEmpty(other_id)){
			return null;
		}
		if(!ValidateUtils.isValidDate(transaction_date)){
			return null;
		}
		if(ValidateUtils.isEmpty(cmte_id) || ValidateUtils.isEmpty(transaction_amount)){
			return null;
		}
		zip_code = zip_code.substring(0, 5);
		Record record = new Record(cmte_id, zip_code, transaction_date, transaction_amount, other_id);
		return record;
	}
	
	@Override
	public void saveRecord(Record record) {
		List<Integer> donations;
		String zip = record.getZipCode();
		if (zipToDonations.containsKey(zip)) {
			donations = zipToDonations.get(zip);
		} else {
			donations = new ArrayList<Integer>();
		}
		ListUtils.insert(record.getTransactionAmount(), donations);
		zipToDonations.put(zip, donations);
		writeToFile(record, donations);
	}

	public void writeToFile(Record record, List<Integer> donations) {
		int median = ListUtils.getMedian(donations);
		int totalTransactionNumber = donations.size();
		int totalTransactionAmount = ListUtils.sum(donations);
		String outputString = StringUtils.buildZipOutput(record, median, totalTransactionNumber, totalTransactionAmount);
		System.out.println(outputString);
		try {
			bw.flush();
			bw.write(outputString + "\n");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close()  {
		try {
			bw.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
