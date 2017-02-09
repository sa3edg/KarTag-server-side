package com.kartag.common.util;

public class StringUtil {

	public static boolean isEmpty(String input)
	{
		return (input == null || "".equals(input));
	}
	public static boolean isNotEmpty(String input)
	{
		return (input != null && !"".equals(input));
	}
}
