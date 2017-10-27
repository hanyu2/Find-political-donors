package me.hanyu.cases;

import me.hanyu.filter.Filter;

public abstract class Analyzer{
	public abstract Filter getFilter();
	public abstract int getMedian(Record record);
	public abstract void writeToFile();
}
