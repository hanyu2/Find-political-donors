package me.hanyu.utils;

import me.hanyu.constant.Constants;

public class ValidateUtils {
	
	/**
	 * Check field value equals empty 
	 * */
	public static boolean equalsEmpty(String field){
		if(field == null){
			return true;
		}
		return field.toLowerCase().equals(Constants.VALID_EMPTY_FIELD);
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
	
	/**
	 *Check field is empty
	 * */
	public static boolean isEmpty(String field){
		return field == null || "".equals(field);
	}
}
