package generic;

import java.util.ArrayList;
import java.util.UUID;

import beans.Entity;

public abstract class GenericFileService<T extends Entity> {

	protected GenericFileRepository<T> repository;

	public GenericFileService(GenericFileRepository<T> repository) {
		this.repository=repository;
	}

	public ArrayList<T> getAll() {
		return repository.getAll();
	}

	public T getById(String id) {
		return repository.getById(id);
	}

	public T addNew(T restaurant) {
		restaurant.setId(UUID.randomUUID().toString());
		return repository.addNew(restaurant);
	}

	public void update(T user) {
		repository.update(user);
	}

}
