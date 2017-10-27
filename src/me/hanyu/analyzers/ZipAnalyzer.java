package me.hanyu.analyzers;

import me.hanyu.cases.Analyzer;
import me.hanyu.filter.Filter;
import me.hanyu.filter.impl.ZipFilter;

public class ZipAnalyzer extends Analyzer{
	Filter filter;
	public ZipAnalyzer(){
		filter = new ZipFilter();
	}

	@Override
	public Filter getFilter() {
		return this.filter;
	}

}
