package ir.asanpardakht.practice.model;

import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;

public abstract class BasicEntity {
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		Jsonb jsonb = JsonbBuilder.create();
		return jsonb.toJson(this);
	}

	public static <E extends BasicEntity> E fromString(String json, Class<E> e) {
		return JsonbBuilder.create().fromJson(json, e);
	}
}
