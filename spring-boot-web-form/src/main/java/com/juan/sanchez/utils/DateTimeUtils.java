package com.juan.sanchez.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

	public static String currentDateTime() {
		return LocalDateTime.now()
				            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}

	private DateTimeUtils() {

	}

}
