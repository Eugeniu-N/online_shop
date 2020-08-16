package com.example.mainpackage.items;

public class ItemQty extends Item {
	
	private int quantity;

	public int getQuantity() {
		return quantity;
	}
	
	@Override
	public String toString() {
		return String.format("%s and quantity: %d", super.toString(), this.getQuantity());
	}
}
