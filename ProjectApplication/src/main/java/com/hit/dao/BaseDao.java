package com.hit.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

import com.hit.entities.BaseEntity;

import java.io.FileOutputStream;

/*
 * Base database handler with required CRUD operations.
 */
public class BaseDao<T extends BaseEntity<TId>, TId extends Comparable<TId>> implements IDao<T, TId> {
	private final File db;

	/*
	 * Initializes a data access object with a default path.
	 */
	public BaseDao(String path) {
		String basePath = System.getProperty("user.dir");
		File file = new File(basePath + "\\" + path);
		if (!file.exists()) {
			try {
				boolean createdSuccessfully = file.createNewFile();

				if (!createdSuccessfully) {
					throw new IOException("File couln't be created successfuly.");
				}
			} catch (IOException ex) {
				System.out.println(ex.getMessage());
			}
		}

		this.db = file;
	}

	@Override
	public void delete(T entity) {
		// Wasn't needed for basic functionality.
	}

	/*
	 * @inheritDoc
	 */
	@Override
	@SuppressWarnings("unchecked")
	public T find(TId id) throws IllegalArgumentException {
		try {
			FileInputStream inputStream = new FileInputStream(this.db.getPath());
			ObjectInputStream objectStream = new ObjectInputStream(inputStream);

			Object result = objectStream.readObject();
			objectStream.close();

			return ((HashMap<TId, T>) result).get(id);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	/*
	 * @inheritDoc
	 */
	@Override
	public boolean save(T entity) throws IllegalArgumentException {
		ObjectOutputStream objectStream = null;
		try {
			HashMap<TId, T> collection = this.getAll();
			Optional<TId> maxId = collection.keySet().stream().max(Comparator.naturalOrder());
			TId nextId = entity.nextValue(maxId.orElse(entity.defaultValue()));
			collection.put(nextId, entity);

			FileOutputStream outputStream = new FileOutputStream(this.db.getPath());
			objectStream = new ObjectOutputStream(outputStream);

			objectStream.writeObject(collection);

			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		} finally {
			try {
				objectStream.flush();
				objectStream.close();
			} catch (Exception ex) {
				System.out.println(ex.getMessage());

				// The object was written although there's a problem in infrastructure.
				return true;
			}
		}
	}

	/*
	 * @inheritDoc
	 */
	@Override
	@SuppressWarnings("unchecked")
	public HashMap<TId, T> getAll() {
		try {
			FileInputStream inputStream = new FileInputStream(this.db.getPath());
			ObjectInputStream objectStream = new ObjectInputStream(inputStream);

			Object result = objectStream.readObject();
			objectStream.close();

			return (HashMap<TId, T>) result;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return new HashMap<TId, T>();
		}
	}
}
