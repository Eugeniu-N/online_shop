package com.example.mainpackage.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemOrderService{
	private final ItemOrderRepository repository;
	
	@Autowired
	public ItemOrderService(ItemOrderRepository repository) {
		this.repository = repository;
	}
	
	public ItemOrder findById(long id) {
		return repository.findById(id);
	};
	
	public ItemOrder addNewItemOrder(ItemOrder itemOrder) {
		return repository.save(itemOrder);
	}
	
	public Iterable<ItemOrder> findAll(){
		return repository.findAll();
	}

	
}