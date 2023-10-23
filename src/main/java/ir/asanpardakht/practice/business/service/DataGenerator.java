package ir.asanpardakht.practice.business.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.stream.Collectors;

import ir.asanpardakht.practice.model.DataEntity;

public class DataGenerator {
	public static List<DataEntity> generateDataEntity(int count) {
		Map<Integer, DataEntity> result = new HashMap<>();
		do {
			Random rnd = new Random();
			DataEntity dataEntity = new DataEntity();
			int id = Math.abs(rnd.nextInt());
			dataEntity.setId(id);
			dataEntity.setData(UUID.randomUUID().toString());
			result.put(id, dataEntity);
		} while (result.size() < count);
		return result.entrySet().stream().map(Entry::getValue).collect(Collectors.toList());
	}
}
