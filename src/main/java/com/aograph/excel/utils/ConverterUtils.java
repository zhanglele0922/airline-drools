package com.aograph.excel.utils;

import com.aograph.excel.converter.DictionaryConverter;

public class ConverterUtils {
	private ConverterUtils() {
		super();
	}

	// 转换器
	public static String converter(String propName, int index, String value, DictionaryConverter... converters) {
		if (converters != null && converters.length > 0) {
			for (DictionaryConverter converter : converters) {
				value = converter.convert(propName, index, value);
			}
		}
		return value;
	}
}
