package beans;

import java.util.UUID;

public class Entity {
	private String id;
	public Entity() {
		this.id=UUID.randomUUID().toString();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Entity(String id) {
		this.id=id;
	}
	
}
