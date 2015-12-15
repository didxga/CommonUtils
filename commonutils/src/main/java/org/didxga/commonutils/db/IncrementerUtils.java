package org.didxga.commonutils.db;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class IncrementerUtils {

	public static Map<String, Incrementer> holder = new ConcurrentHashMap<String, Incrementer>();
	
	public static int getNextNumber(String domain) {
		Incrementer incrementer = getIncrementer(domain);
		return incrementer.increment();
	}
	
	private static Incrementer getIncrementer(String domain) {
		
		return holder.putIfAbsent(domain, createIncrementer(domain));
	}
	
	private static Incrementer createIncrementer(String domain) {
		return new IncrementerUtils().new Incrementer();
	}
	
	private class Incrementer {
		
		private AtomicInteger current = new AtomicInteger(0);
		
		public Incrementer() {
			//TODO init code
		}
		
		public int increment() {
			return current.incrementAndGet();
		}
	}
}
