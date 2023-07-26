package com.hit.dao;

import java.util.HashMap;

import com.hit.entities.BaseEntity;

/*
 * Data access object interface.
 */
public interface IDao<T extends BaseEntity<TId>, TId extends Comparable<TId>>  {
	/*
	 * Gets all data requested from a given persistence.
	 */
	HashMap<TId, T> getAll();

	/*
	 * Deletes an object from a given persistence.
	 */
	void delete(T entity);

	/*
	 * Finds a certain object by it's identifier in a given persisted dataset.
	 */
	T find(TId id) throws IllegalArgumentException;

	/*
	 * Saves an object to a given data persistence solution.
	 */
	boolean save(T entity) throws IllegalArgumentException;
}
