package com.hit.entities;

public abstract class BaseEntity<TId extends Comparable<TId>> implements java.io.Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * Entity's identifier.
	 */
	private TId id;

	/*
	 * Gets object's identifier.
	 */
	public TId getId() {
		return id;
	}

	/*
	 * Every entity should set it's default value. Otherwise an exception is thrown.
	 */
	public TId defaultValue() throws Exception {
		throw new Exception("Not implemented");
	}

	/*
	 * Every entity should set it's value incrementation logic. Otherwise an
	 * exception is thrown.
	 */
	public TId nextValue(TId value) throws Exception {
		throw new Exception("Not implemented");
	}
}