package me.hanyu.cases;

import me.hanyu.filter.Filter;

public abstract class Analyzer{
	public abstract Filter getFilter();
	public abstract void writeToFile();
}
