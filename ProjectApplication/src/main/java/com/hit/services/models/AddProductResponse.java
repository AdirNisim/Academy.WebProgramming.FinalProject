package com.hit.services.models;

public class AddProductResponse {
	public boolean success;

	public AddProductResponse(boolean success) {
		this.success = success;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}
}