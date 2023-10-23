package ir.asanpardakht.practice.dao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import ir.asanpardakht.practice.model.DataEntity;

public class DataDao extends GeneralDao<DataEntity> {
	private static class SingletonHolder {
		private static DataDao instance;

		public synchronized static DataDao getInstance() throws Exception {
			if (instance == null) {
				instance = new DataDao();
			}
			return instance;
		}
	}

	private DataDao() {
	}

	public static DataDao getInstance() throws Exception {
		return SingletonHolder.getInstance();
	}

	public DataEntity select(int id) throws Exception {
		if (cache.containsKey(id)) {
			cache.get(id).incLoadCount();
		} else {
			DataEntity dataEntity = find(id).orElseThrow(() -> new Exception("Id " + id + " not found."));
			putOnCache(new CacheObject(dataEntity, 1));
		}
		return cache.get(id).getT();
	}

	public List<String> showCache() {
		return cache.entrySet().stream().map(e -> e.getValue().toString()).collect(Collectors.toList());
	}

	private Optional<DataEntity> find(int id) throws Exception {
		String json = fileDatabase.find(id);
		if (json == null) {
			return Optional.empty();
		}
		return Optional.of(DataEntity.fromString(json, DataEntity.class));
	}

}
