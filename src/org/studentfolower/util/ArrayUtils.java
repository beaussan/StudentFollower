package org.studentfolower.util;

import java.util.Collection;
import java.util.List;

public class ArrayUtils {

	public static String[] toArray(Collection<String> str) {
		String[] stockArr = new String[str.size()];
		stockArr = str.toArray(stockArr);
		return stockArr;
	}

	public static String[] toArray(List<String> str) {
		String[] stockArr = new String[str.size()];
		stockArr = str.toArray(stockArr);
		return stockArr;
	}

	private ArrayUtils() {
	}

}
