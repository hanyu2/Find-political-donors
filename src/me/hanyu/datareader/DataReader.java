package me.hanyu.datareader;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.hanyu.analyzers.DateAnalyzer;
import me.hanyu.analyzers.ZipAnalyzer;
import me.hanyu.constants.Constants;
import me.hanyu.models.Analyzer;
import me.hanyu.models.Record;

public class DataReader {
	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream(Constants.INPUT_FILE_DIR);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String line;

		//Analyzer list
		List<Analyzer> analyzers = new ArrayList<Analyzer>();
		analyzers.add(new ZipAnalyzer());
		analyzers.add(new DateAnalyzer());
		
		//Read input file line by line
		while ((line = br.readLine()) != null)   {
			for(Analyzer analyzer : analyzers){
				Record record = analyzer.filter(line);
				if(record != null){
					analyzer.saveRecord(record);
				}
			}
		}
		//Close the input stream
		br.close();
		//Close all output streams
		for(Analyzer analyzer : analyzers){
			analyzer.close();
		}
	}
}
