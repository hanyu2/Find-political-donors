package me.hanyu.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Used for testing built-in functions
 * */
public class Test {
	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d.toString());
		System.out.println(new BigDecimal("2.0").setScale(0, BigDecimal.ROUND_HALF_UP).intValue()); 
		System.out.println(new BigDecimal("2.1").setScale(0, BigDecimal.ROUND_HALF_UP).intValue()); 
		System.out.println(new BigDecimal("2.5").setScale(0, BigDecimal.ROUND_HALF_UP).intValue()); 
		System.out.println(new BigDecimal("2.99").setScale(0, BigDecimal.ROUND_HALF_UP).intValue());
		Map<String, Integer> map = new HashMap<String,Integer>();
		map.put("aa", 1);
		System.out.println(map.containsKey("aa"));
	}
}
