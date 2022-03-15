package com.cyrto.crypto.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cyrto.crypto.entity.Orders;
import com.cyrto.crypto.model.CoinType;
import com.cyrto.crypto.service.OrderService;

@RestController
@RequestMapping("/cryptoapp")
public class CryptoController {

	@Autowired
	private OrderService service;
	
	@PostMapping("/addorder")
	public Orders addOrder(@RequestBody Orders order) {
		Orders responseOrder = null;
		try {
			responseOrder = service.createOrder(order).get();  
		}catch(InterruptedException | ExecutionException e) {
			
		}
		return responseOrder;
	}
	
	@GetMapping("/getordersummary/{type}")
	public List<Orders> getSummaryByCoinType(@PathVariable CoinType type){
		List<Orders> listOfOrders = service.getSummaryInfo(type);
		return listOfOrders;
	}
	
	
	@PostMapping("/addorders")
	public List<Orders> addOrders(@RequestBody List<Orders> orders) {
		List<Orders> responseOrdersList = null;
		try {
			responseOrdersList =  service.createListOfOrders(orders).get();
		}catch(InterruptedException | ExecutionException e) {
			
		}
		return responseOrdersList;
	}
	
	@GetMapping("/getorderbyid/{id}")
	public Orders getOrderById(@PathVariable long id) {
		Orders responseOrder = null;
		try {
			responseOrder = service.getOrderById(id).get();
		}catch(InterruptedException | ExecutionException e) {
			
		}
		return responseOrder;
	}
	
	@GetMapping("/listoforders")
	public List<Orders> findAllOrders(){
		List<Orders> responseList = null;
		try {
			responseList = service.findAllOrders().get();
		}catch(InterruptedException | ExecutionException e) {
			
		}
		return responseList;
	}
	
	@PutMapping("/updateorder")
	public Orders updateOrder(@RequestBody Orders order) {
		Orders updatedOrder = null;
		try {
			updatedOrder = service.updateOrder(order).get();
		}catch(InterruptedException | ExecutionException e) {
			
		}
		return updatedOrder;
	}
	
	@DeleteMapping("/deleteorderbyid/{id}")
	public String deleteOrder(@PathVariable long id) {
		String response=null;
		try {
			response = service.removeOrderById(id).get();
		}catch(InterruptedException | ExecutionException e) {
			
		}
		return response;
	}
	
	
	
}

