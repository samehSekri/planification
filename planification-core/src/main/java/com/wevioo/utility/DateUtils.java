package com.wevioo.utility;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Date Utilities
 * 
 * @author olr
 * 
 */
public abstract class DateUtils {

	/**
	 * Date format
	 */
	public static final String DATE_FORMAT_DAY = "yyyyMMdd";
	public static final String DATE_FORMAT_DAY_MASIT = "yyyy-MM-DD";
	public static final String DATE_FORMAT_SIMPLE = "dd/MM/yyyy";
	public static final String DATE_FORMAT_SIMPLE2 = "dd-MM-yyyy";
	public static final String DATE_FORMAT_SIMPLE3 = "yyyy-MM-dd";
	public static final String DATE_FORMAT_SECOND = "dd/MM/yyyy HH:mm:ss";
	public static final String DATE_FORMAT_TIMESTAMP_FULL = "yyyyMMddHHmmss";
	public static final String DATE_FORMAT_HOURS = "HHmmss";
	public static final String DATE_FORMAT_HOURS_MUNITE_SECOND = "HH:mm:ss";
	public static final String DATE_FORMAT_HOURS_MUNITE = "HH:mm";
	public static final String DATE_FORMAT_TIMESTAMP_MASIT = "yyyyMMdd'/'HH'.'mm";

	/**
	 * Date formatter
	 */
	public static final DateFormat DATE_FORMATTER_SIMPLE = new SimpleDateFormat(DATE_FORMAT_SIMPLE);
	public static final DateFormat DATE_FORMATTER_SIMPLE2 = new SimpleDateFormat(DATE_FORMAT_SIMPLE2);
	public static final DateFormat DATE_FORMATTER_SIMPLE3 = new SimpleDateFormat(DATE_FORMAT_SIMPLE3);
	public static final DateFormat DATE_FORMATTER_SECOND = new SimpleDateFormat(DATE_FORMAT_SECOND);
	public static final DateFormat DATE_FORMATTER_DAY = new SimpleDateFormat(DATE_FORMAT_DAY);
	public static final DateFormat DATE_FORMATTER_TIMESTAMP_FULL = new SimpleDateFormat(DATE_FORMAT_TIMESTAMP_FULL);
	public static final DateFormat DATE_FORMATTER_HOURS = new SimpleDateFormat(DATE_FORMAT_HOURS);
	public static final DateFormat DATE_FORMATTER_HOURS_MUNITE_SECOND = new SimpleDateFormat(DATE_FORMAT_HOURS_MUNITE_SECOND);
	public static final DateFormat DATE_FORMATTER_HOURS_MUNITE = new SimpleDateFormat(DATE_FORMAT_HOURS_MUNITE);
	public static final DateFormat DATE_FORMATTER_TIMESTAMP_MASIT = new SimpleDateFormat("yyyyMMdd'/'HH'.'mm");
	public static final DateFormat DATE_FORMATTER_DAY_MASIT = new SimpleDateFormat(DATE_FORMAT_DAY_MASIT);
	public static final DateFormat DATE_FORMATTER_SIMPLE_TIME_ZONE = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
	
	/**
	 * Date to String
	 * 
	 * @param date
	 * @return
	 */
	public static String formatDate(Date date, DateFormat dateFormat) {
		if (date == null) {
			return "";
		}
		return dateFormat.format(date);
	}

	/**
	 * String to date
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static Date formatToDate(String time, DateFormat dateFormat) throws ParseException {
		return dateFormat.parse(time);
	}

	/**
	 * Format to timestamp
	 * 
	 * @param time
	 * @return
	 * @throws ParseException
	 */
	public static java.sql.Timestamp formatToTimeStamp(String time, DateFormat dateFormat) throws ParseException {
		java.sql.Timestamp timestamp = null;
		Date parsedDate = dateFormat.parse(time);
		timestamp = new java.sql.Timestamp(parsedDate.getTime());
		return timestamp;
	}

	/**
	 * Is date in weekend
	 * 
	 * @param date
	 * @return
	 */
	public static boolean isWeekend(Date dateFerier) {
		Calendar c = Calendar.getInstance();
		c.setTime(dateFerier);
		if ((c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) || (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY)) {
			return true;
		}
		return false;
	}

	/**
	 * Difference in minutes between two dates
	 * 
	 * @param earlierDate
	 * @param laterDate
	 * @return
	 */
	public static int minutesDiff(Date earlierDate, Date laterDate) {
		if (earlierDate == null || laterDate == null)
			return 0;

		return (int) ((laterDate.getTime() / 60000) - (earlierDate.getTime() / 60000));
	}

	/**
	 * Difference in days between two dates
	 * 
	 * @param earlierDate
	 * @param laterDate
	 * @return
	 */
	public static int daysDiff(Date earlierDate, Date laterDate) {
		if (earlierDate == null || laterDate == null)
			return 0;
		return (int) (((laterDate.getTime() / 60000) - (earlierDate.getTime() / 60000)) / (60 * 24));
	}

	/**
	 * add 24 Hours to date
	 * 
	 * @param perdiodeDate2
	 * @return
	 */
	public static Date add24Hours(Date perdiodeDate2) {
		Calendar c = Calendar.getInstance();
		c.setTime(perdiodeDate2);
		c.set(Calendar.MINUTE, 59);
		c.set(Calendar.HOUR, 23);

		return c.getTime();
	}
	
	/**
	 * add 24 Hours to date
	 * 
	 * @param perdiodeDate2
	 * @return
	 */
	@SuppressWarnings("deprecation")
	public static Date getDateForString(String dateString) {
		String[] separation1 = dateString.split(" ");
		String[] separation2 = separation1[0].split("/");
		String[] separation3 = separation1[1].split(":");
		return new Date(Integer.parseInt(separation2[2])-1900, (Integer.parseInt(separation2[1])-1), Integer.parseInt(separation2[0]), Integer.parseInt(separation3[0]), Integer.parseInt(separation3[1]), Integer.parseInt(separation3[2]));
	}

}
