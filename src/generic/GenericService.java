package generic;

import java.util.ArrayList;

import beans.Entity;

public abstract class GenericService<T extends Entity> {

	protected GenericRepository<T> repository;

	public GenericService(GenericRepository<T> repository) {
		this.repository=repository;
	}

	public ArrayList<T> getAll() {
		return repository.getAll();
	}

	public T getById(String id) {
		return repository.getById(id);
	}

	public T addNew(T restaurant) {
		return repository.addNew(restaurant);
	}

	public void update(T user) {
		repository.update(user);
	}

}
