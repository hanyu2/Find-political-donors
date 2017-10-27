package me.hanyu.analyzers;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import me.hanyu.filter.impl.ZipFilter;
import me.hanyu.utils.ListUtils;
import me.hanyu.utils.StringUtils;

public class ZipAnalyzer extends Analyzer {
	String output_file_dir = Constants.OUTPUT_FILE_ZIP_DIR;
	private Filter filter;
	private Map<String, List<Integer>> zipToDonations;
	private FileOutputStream outputStream;
	private BufferedWriter bw;

	public ZipAnalyzer() throws FileNotFoundException {
		filter = new ZipFilter();
		zipToDonations = new HashMap<String, List<Integer>>();
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
		String zip = record.getZip_code();
		if (zipToDonations.containsKey(zip)) {
			donations = zipToDonations.get(zip);
		} else {
			donations = new ArrayList<Integer>();
		}
		int insertIndex = Collections.binarySearch(donations, record.getTransaction_amount());
		if (insertIndex < 0) {
			insertIndex = -insertIndex + 1;
		}
		donations.add(insertIndex, record.getTransaction_amount());
		zipToDonations.put(zip, donations);
		writeToFile(record, donations);
	}

	@Override
	public void writeToFile(Record record, List<Integer> donations) {
		int median = ListUtils.getMedian(donations);
		int totalTransactionNumber = donations.size();
		int totalTransactionAmount = ListUtils.sum(donations);
		String outputString = StringUtils.buildOutput(record, median, totalTransactionNumber, totalTransactionAmount);
		try {
			bw.write(outputString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void close()  {
		try {
			outputStream.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
