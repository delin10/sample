package org.delin.util;

import java.util.HashMap;
import java.util.Map;

public class ParamsUtils {
	/**
	 * return a map null should check before call
	 * 
	 * @param keys
	 * @param values
	 * @return
	 */
	public static Map<String, Object> putAll(String[] keys, Object[] values) {
		int keyLen = keys.length, valueLen = values.length, len = keyLen > valueLen ? valueLen : keyLen;
		Map<String, Object> params = new HashMap<>();
		for (int i = 0; i < len; ++i) {
			params.put(keys[i], values[i]);
		}
		return params;
	}

	public static String[] params(String... args) {
		return args;
	}

	public static Object[] params(Object... args) {
		return args;
	}
}
