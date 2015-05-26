package org.studentfolower.util;

import java.util.ArrayList;

public class ArrayUtils {

	public static String[] toArray(ArrayList<String> str) {
		String[] stockArr = new String[str.size()];
		stockArr = str.toArray(stockArr);
		return stockArr;
	}

	public ArrayUtils() {
		// TODO Auto-generated constructor stub
	}

}
