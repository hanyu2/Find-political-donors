package me.hanyu.filter;

import me.hanyu.cases.Record;

public interface Filter {
	Record filter(String line);
}
