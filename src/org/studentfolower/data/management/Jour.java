package org.studentfolower.data.management;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Jour {
	public static void main(String[] args) {
		System.out.println(new Jour("01/02/2015"));
		System.out.println(new Jour("26/05/2015").isIn(Calendar.getInstance()));
	}

	Calendar c = Calendar.getInstance();

	public Jour(int month, int date) {

		c.set(c.get(Calendar.YEAR), month, date);
		System.out.println(c);

	}

	public Jour(int year, int month, int date) {
		c.set(year, month, date);
	}

	/**
	 * format of str : dd/MM/yyyy ( 28/09/2015 )
	 * 
	 * @param str
	 */
	public Jour(String str) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			c.setTime(sdf.parse(str));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		c.set(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DATE));
	}

	public boolean isIn(Calendar cal) {
		return c.get(Calendar.YEAR) == cal.get(Calendar.YEAR)
				&& c.get(Calendar.MONTH) == cal.get(Calendar.MONTH)
				&& c.get(Calendar.DATE) == cal.get(Calendar.DATE);

	}

	public boolean isIn(Date dat) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(dat);
		return c.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)
				&& c.get(Calendar.MONTH) == calendar.get(Calendar.MONTH)
				&& c.get(Calendar.DATE) == calendar.get(Calendar.DATE);

	}

	@Override
	public String toString() {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(c.getTime());
	}

}
