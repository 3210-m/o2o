package com.tl.o2o.util;

import javax.servlet.http.HttpServletRequest;

/**
 * @author tangli
 * @create 2018-11-09 下午12:19
 **/
public class HttpServletRequestUtil {

	public static int getInt(HttpServletRequest request, String key) {
		try {
			return Integer.decode(request.getParameter(key));
		} catch (Exception e) {
			return -1;
		}
	}

	public static long getLong(HttpServletRequest request, String key) {
		try {
			return Long.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1L;
		}
	}

	public static double getDouble(HttpServletRequest request, String key) {
		try {
			return Double.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return -1d;
		}
	}

	public static boolean getBoolean(HttpServletRequest request, String key) {
		try {
			return Boolean.valueOf(request.getParameter(key));
		} catch (Exception e) {
			return false;
		}
	}

	public static String getString(HttpServletRequest request, String key) {
		try {
			String s = request.getParameter(key);
			return s == null || s.equals("") ? null : s.trim();
		} catch (Exception e) {
			return null;
		}
	}
}
