package ir.asanpardakht.practice.dao;

import java.util.Collections;
import java.util.List;
import java.util.NavigableMap;
import java.util.TreeMap;

import ir.asanpardakht.practice.model.BasicEntity;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public abstract class GeneralDao<T extends BasicEntity> {
	protected class CacheObject {
		private T t;
		private int loadCount = 0;

		public CacheObject() {
			super();
		}

		public CacheObject(T t, int loadCount) {
			super();
			this.t = t;
			this.loadCount = loadCount;
		}

		public T getT() {
			return t;
		}

		public void setT(T t) {
			this.t = t;
		}

		public int getLoadCount() {
			return loadCount;
		}

		public void incLoadCount() {
			this.loadCount++;
		}

		@Override
		public String toString() {
			Jsonb jsonb = JsonbBuilder.create();
			return jsonb.toJson(this);
		}

	}

	protected int cacheSize = 100;
	protected NavigableMap<Integer, CacheObject> cache = new TreeMap<>(Collections.reverseOrder());
	protected FileDatabase<T> fileDatabase = new FileDatabase<>();

	public GeneralDao() {
	}

	public GeneralDao(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public boolean save(T t) throws Exception {
		fileDatabase.append(t);
		return true;
	}

	public boolean save(List<T> list) throws Exception {
		fileDatabase.append(list);
		return true;
	}

	public boolean replace(T t) throws Exception {
		fileDatabase.replace(t);
		return true;
	}

	public boolean replace(List<T> list) throws Exception {
		fileDatabase.replace(list);
		return true;
	}

	protected void putOnCache(CacheObject cacheObject) {
		if (cache.size() >= cacheSize) {
			cache.remove(cache.lastEntry().getValue().getT().getId());
		}
		cache.put(cacheObject.getT().getId(), cacheObject);
	}

}
