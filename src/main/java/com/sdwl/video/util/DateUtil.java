package com.sdwl.video.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class DateUtil {


	/**
	 * convert date and time to String like format
	 */
	public static String formatDateTime( String format, Date date ) {
		return new SimpleDateFormat(format).format(date);
	}




}
