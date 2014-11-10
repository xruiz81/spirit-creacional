package com.spirit.bi;

public class Statistics {
	private static int cacheHits;
	private static int querys;

	public static int getCacheHits() {
		return cacheHits;
	}

	public static synchronized void addCacheHits() {
		Statistics.cacheHits++;
	}

	public static int getQuerys() {
		return querys;
	}

	public static synchronized void addQuerys() {
		Statistics.querys++;
	}

	public static void print() {
		System.out.println("------------------------");
		System.out.println("cacheHits " + cacheHits);
		System.out.println("querys " + querys);
		System.out.println("------------------------");
	}

	public static void reset() {
		Statistics.cacheHits = 0;
		Statistics.querys = 0;
	}
}
