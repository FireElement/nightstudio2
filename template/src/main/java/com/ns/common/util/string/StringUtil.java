package com.ns.common.util.string;

public class StringUtil {
	public static String getStrOfLength(char prefix, String str, int length) {
		StringBuilder builder = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			builder.append(prefix);
		}
		builder.append(str);
		String result = builder.substring(builder.length() - length);
		return result;
	}

	public static void main(String[] args) {
		System.out.println((long) Math.pow(10, 12));
		System.out.println(StringUtil.getStrOfLength('0', "123", 12));
	}
}
