package me.blackilykat.test_client.util;

import java.util.Arrays;

public class ArrayUtil {
	public static String[] removeFirst(String[] original,int index) {
		return Arrays.stream(original).toList().subList(index, original.length).toArray(new String[0]);
	}
}
