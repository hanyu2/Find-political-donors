package me.hanyu.filter;
import me.hanyu.models.Record;

public interface Filter {
	Record filter(String line);
}
