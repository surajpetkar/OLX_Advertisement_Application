package com.zensar.model;

public class MasterdataCategory {

	private String id;
	private String category;
	
	public MasterdataCategory(String id, String category) {
		super();
		this.id = id;
		this.category = category;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public String toString() {
		return "MasterdataCategory [id=" + id + ", category=" + category + "]";
	}
	
}
