package com.example.mainpackage.items;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.mainpackage.orders.ItemOrder;
import com.example.mainpackage.orders.ItemOrderService;
import com.example.mainpackage.security.ApplicationUser;

@RequestMapping("api/v1/items")
@RestController
public class ItemController {
	
	private final ItemService itemService;
	private final ItemOrderService itemOrderService;

	@Autowired
	public ItemController(ItemService itemService, ItemOrderService orderService) {
		this.itemService = itemService;
		this.itemOrderService = orderService;
	}
	
	@GetMapping
	//@PreAuthorize("hasAnyRole('ADMIN', 'STANDARD_USER')")
	public Iterable<Item> returnAllItems(){
		System.out.println("Returned all items");
		return this.itemService.findAll();
	}
	
	@PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAnyRole('ADMIN')")
	public String addNewItem(@RequestBody Item itemToRegister) {
		ApplicationUser applicationUser = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println(applicationUser);
		Item addedItem = this.itemService.addNewItem(new Item(itemToRegister.getName(), itemToRegister.getPrice()));
		return String.format("Item with name: %s added to database with id: %d", itemToRegister.getName(), addedItem.getId());
	}
	
	@GetMapping(path = "/{id}")
	public Item getItemById(@PathVariable int id){
		return this.itemService.findById(id);
	}
	
	@PostMapping(path = "/ids", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public Iterable<Item> getMultipleItemsById(@RequestBody int[] idList){
		List<Item> itemListToReturn = new ArrayList(); 
		
		/*
		for (int i = 0; i < itemList.size(); i++) {
			itemListToReturn.add(this.itemService.findById(itemList.get(i).getId()));
		}
		*/
		
		for (int i = 0; i < idList.length; i++) {
			itemListToReturn.add(this.itemService.findById(idList[i]));
		}
		
		return itemListToReturn;
	}
	
	@PostMapping(path = "/checkout", consumes = {MediaType.APPLICATION_JSON_VALUE})
	@PreAuthorize("hasAnyRole('STANDARD_USER')")
	public String checkoutItems(@RequestBody Iterable<ItemQty> itemList){
		ApplicationUser applicationUser = (ApplicationUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		long userId = applicationUser.getId();
		System.out.println("Current user checking out: " +applicationUser);
		System.out.println("With item list: " +itemList);
		
		for (ItemQty item: itemList) {
			ItemOrder itemOrder = new ItemOrder(userId, item.getId(), item.getQuantity(), System.currentTimeMillis());
			itemOrderService.addNewItemOrder(itemOrder);
		}
		
		return "Success";
	}
	
}
