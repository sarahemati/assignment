package ir.asanpardakht.practice;

import java.util.HashMap;
import java.util.Map;

public class LeastFrequentlyUsedCache<T> {

	private final Map<Integer, T> cache;
	private final Map<Integer, Integer> idFrequencyMap;
	private final int cacheSize;

	public LeastFrequentlyUsedCache(int cacheSize) {
		cache = new HashMap<>(cacheSize);
		idFrequencyMap = new HashMap<>(cacheSize);
		this.cacheSize = cacheSize;
	}

	public T get(int key) {
		if (cache.containsKey(key)) {
			idFrequencyMap.put(key, idFrequencyMap.get(key) + 1);
			return cache.get(key);
		}
		return null;
	}

	public void put(int key, T value) {
		if (cache.size() >= this.cacheSize) {
			 idFrequencyMap.entrySet().stream().min(Map.Entry.comparingByValue())
					.ifPresent(integerIntegerEntry -> cache.remove(integerIntegerEntry.getKey()));
		}
		cache.put(key, value);
		idFrequencyMap.put(key, 1);
	}

}
