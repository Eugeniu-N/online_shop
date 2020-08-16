package com.example.mainpackage.orders;

import org.springframework.data.repository.CrudRepository;

public interface ItemOrderRepository extends CrudRepository<ItemOrder, Long>{

	ItemOrder findById(long id);
	
	<S extends ItemOrder> S save(S order);
}
