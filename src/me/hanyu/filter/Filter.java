package me.hanyu.filter;

import me.hanyu.model.Record;

public interface Filter {
	Record filter(String line);
}
