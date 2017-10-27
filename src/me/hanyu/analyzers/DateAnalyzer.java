package me.hanyu.analyzers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import me.hanyu.cases.Analyzer;
import me.hanyu.cases.Record;
import me.hanyu.constant.Constants;
import me.hanyu.filter.Filter;
import me.hanyu.filter.impl.DateFilter;

public class DateAnalyzer extends Analyzer{
	String output_file_dir = Constants.OUTPUT_FILE_DATE_DIR;
	private Filter filter;
	private Map<String, List<Integer>> dateToDonations;
	private FileOutputStream outputStream;
	private BufferedWriter bw;
	
	public DateAnalyzer() throws FileNotFoundException{
		filter = new DateFilter();
		dateToDonations = new HashMap<String, List<Integer>>();
		outputStream = new FileOutputStream(output_file_dir);
		bw = new BufferedWriter(new OutputStreamWriter(outputStream));
	}
	
	@Override
	public Filter getFilter() {
		return this.filter;
	}
	
	@Override
	public void saveRecord(Record record) {
		List<Integer> donations;
		String date = record.getTransaction_date();
		if (dateToDonations.containsKey(date)) {
			donations = dateToDonations.get(date);
		} else {
			donations = new ArrayList<Integer>();
		}
		int insertIndex = Collections.binarySearch(donations, record.getTransaction_amount());
		if (insertIndex < 0) {
			insertIndex = -insertIndex + 1;
		}
		donations.add(insertIndex, record.getTransaction_amount());
		dateToDonations.put(date, donations);
		writeToFile(record, donations);
	}

	@Override
	public void writeToFile() {
		
	}

	
}
