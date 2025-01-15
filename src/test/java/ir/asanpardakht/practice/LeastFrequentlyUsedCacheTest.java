package ir.asanpardakht.practice;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class LeastFrequentlyUsedCacheTest {

	private final LeastFrequentlyUsedCache<String> cache =
			new LeastFrequentlyUsedCache<>(2);

	@Test
	void testCacheNotFoundKey() {
		assertNull(cache.get(1));
	}

	@Test
	void testCacheFoundKey() {
		cache.put(1, "Test");
		assertNotNull(cache.get(1));
	}

	@Test
	void testCacheRemoveLeastFrequentlyUsed() {
		cache.put(1, "value 1");
		cache.put(2, "value 2");

		cache.get(1);
		cache.get(2);
		cache.get(1);

		cache.put(3, "value 3");

		assertNotNull(cache.get(1));
		assertNull(cache.get(2));
		assertNotNull(cache.get(3));
	}
}
