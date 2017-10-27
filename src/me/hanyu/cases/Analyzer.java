package me.hanyu.cases;

import java.util.List;

import me.hanyu.filter.Filter;

public abstract class Analyzer{	
	public abstract Filter getFilter();
	public abstract void saveRecord(Record record);
	public abstract void writeToFile(Record record, List<Integer> donations);
	public abstract void close();
	
}
