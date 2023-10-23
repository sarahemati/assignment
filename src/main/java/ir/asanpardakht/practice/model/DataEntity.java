package ir.asanpardakht.practice.model;

public class DataEntity extends BasicEntity {
	private String data;

	public DataEntity() {
		super();
	}

	public DataEntity(int id, String data) {
		super.setId(id);
		this.data = data;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
