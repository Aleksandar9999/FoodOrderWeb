package generic;

import java.util.ArrayList;
import java.util.UUID;

import beans.Entity;

public abstract class GenericService<T extends Entity,E extends Entity> {

	protected GenericRepository<T,E> repository;

	public GenericService(GenericRepository<T,E> repository) {
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
