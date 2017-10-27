package me.hanyu.datacleaner;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import me.hanyu.analyzers.DateAnalyzer;
import me.hanyu.analyzers.ZipAnalyzer;
import me.hanyu.cases.Analyzer;
import me.hanyu.constant.Constants;

public class DataReader {
	public static void main(String[] args) throws IOException {
		FileInputStream inputStream = new FileInputStream(Constants.INPUT_FILE_DIR);
		BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		String strLine;

		List<Analyzer> analyzers = new ArrayList<Analyzer>();
		analyzers.add(new ZipAnalyzer());
		analyzers.add(new DateAnalyzer());
		
		
		
		//Read input file line by line
		while ((strLine = br.readLine()) != null)   {
			
		}
	
		//Close the input stream
		br.close();
	}
}
