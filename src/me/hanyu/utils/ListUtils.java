package me.hanyu.utils;

import java.math.BigDecimal;
import java.util.List;

public class ListUtils {
	
	/**
	 * Sum of the list
	 * */
	public static int sum(List<Integer> list){
		int sum = 0;
		for(int e : list){
			sum += e;
		}
		return sum;
	}
	
	public static int getMedian(List<Integer> list){
		int median;
		int listSize = list.size();
		if(listSize % 2 == 1){
			median = list.get(listSize / 2); 
		}else{
			double doubleMedian = (double)(list.get(listSize / 2 - 1) + list.get(listSize / 2 )) / 2;
			median = new BigDecimal(String.valueOf(doubleMedian)).setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		}
		return median;
	}
}
