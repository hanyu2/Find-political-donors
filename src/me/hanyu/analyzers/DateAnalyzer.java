package me.hanyu.analyzers;

import me.hanyu.cases.Analyzer;
import me.hanyu.filter.Filter;
import me.hanyu.filter.impl.DateFilter;

public class DateAnalyzer extends Analyzer{
	Filter filter;
	public DateAnalyzer(){
		filter = new DateFilter();
	}
	
	@Override
	public Filter getFilter() {
		return this.filter;
	}

	@Override
	public void writeToFile() {
		// TODO Auto-generated method stub
		
	}
	
}
