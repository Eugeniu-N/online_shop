package com.example.mainpackage.items;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Item{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Spring converts camelCase to snake_case
	// A camelCase field in java must be camel_case column in the SQL table
	private String name;
	private int price;
	//private String firstName;
	//private String lastName;

	protected Item() {}

	public Item(String name, int price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return String.format("Item with id: %d has name: %s and price $%d", id, name, price);
	}

	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public int getPrice() {
		return price;
	}
}