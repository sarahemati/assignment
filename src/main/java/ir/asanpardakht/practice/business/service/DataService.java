package ir.asanpardakht.practice.business.service;

import java.util.List;

import ir.asanpardakht.practice.dao.DataDao;
import ir.asanpardakht.practice.model.DataEntity;

public class DataService {
	public int generateDataToFile(int count) throws Exception {
		List<DataEntity> dataEntities = DataGenerator.generateDataEntity(count);
		DataDao dao = DataDao.getInstance();
		dao.replace(dataEntities);
		return dataEntities.size();
	}

	public DataEntity select(int id) throws Exception {
		return DataDao.getInstance().select(id);
	}

	public List<String> showCache() throws Exception {
		return DataDao.getInstance().showCache();
	}

	public boolean save(DataEntity dataEntity) throws Exception {
		return DataDao.getInstance().save(dataEntity);
	}

	public boolean save(List<DataEntity> list) throws Exception {
		return DataDao.getInstance().save(list);
	}

	public boolean replace(DataEntity dataEntity) throws Exception {
		return DataDao.getInstance().replace(dataEntity);
	}

	public boolean replace(List<DataEntity> list) throws Exception {
		return DataDao.getInstance().replace(list);
	}

}
