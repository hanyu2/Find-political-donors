package me.hanyu.utils;

public class ValidateUtils {
	
	/**
	 * Check field value is empty 
	 * */
	public static boolean isEmpty(String str) {
		return str == null || "".equals(str);
	}
	
	/**
	 * Check validation of date
	 * */
	public static boolean isValidDate(String date){
		if(date == null || date.length() != 8){
			return false;
		}
		return true;
	}
	
	/**
	 * Check validation of Zip
	 * */
	public static boolean isValidZip(String zip){
		if(zip == null || zip.length() < 5){
			return false;
		}
		return true;
	}
	
	/**
	 * Call this after isValidZip
	 * Trim Zip
	 * */
	public static String trimZip(String zip){
		if(zip.length() == 9){
			return zip.substring(0, 5);
		}
		return "";
	}
	
}
