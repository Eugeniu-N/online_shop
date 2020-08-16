package com.example.mainpackage.items;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, Long>{

	Item findById(long id);
	
	Item findByName(String username);
	
	<S extends Item> S save(S item);
	
	boolean existsByName(String name);
}
