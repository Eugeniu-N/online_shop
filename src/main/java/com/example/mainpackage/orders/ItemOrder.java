package com.example.mainpackage.orders;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ItemOrder{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	// Spring converts camelCase to snake_case
	// A camelCase field in java must be camel_case column in the SQL table
	private Long userId;
	private Long itemId;
	private int quantity;
	private Long dateMillis;
	//private String firstName;
	//private String lastName;

	protected ItemOrder() {}
	
	public ItemOrder(long userId, long itemId, int quantity, long dateMillis) {
		this.userId = userId;
		this.itemId = itemId;
		this.quantity = quantity;
		this.dateMillis = dateMillis;
	}

	public Long getId() {
		return id;
	}
	
	public Long getItemId() {
		return itemId;
	}

	public int getQuantity() {
		return quantity;
	}

	public Long getDate() {
		return dateMillis;
	}

	public Long getUserId() {
		return userId;
	}
	
	@Override
	public String toString() {
		return "ItemOrder [id=" + id + ", userId=" + userId + ", itemId=" + itemId + ", quantity=" + quantity
				+ ", dateMillis=" + dateMillis +"]";
	}
	
}