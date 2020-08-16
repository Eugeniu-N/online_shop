package com.example.mainpackage.items;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemService{
	private final ItemRepository repository;
	
	@Autowired
	public ItemService(ItemRepository repository) {
		this.repository = repository;
	}
	
	public Item findById(long id) {
		return repository.findById(id);
	};
		
	public Item findByName(String name) {
		return findByName(name);
	};
	
	public Item addNewItem(Item item) {
		return repository.save(item);
	}
	
	public Iterable<Item> findAll(){
		return repository.findAll();
	}

	
}