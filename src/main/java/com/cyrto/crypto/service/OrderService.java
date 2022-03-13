package com.cyrto.crypto.service;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.cyrto.crypto.dao.OrderRepository;
import com.cyrto.crypto.entity.Orders;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepo;
	
	@Async
	public CompletableFuture<Orders> createOrder(Orders order) {
		return CompletableFuture.completedFuture(saveOrder(order));
	}
	
	private Orders saveOrder(Orders order) {
		return orderRepo.save(order);
	}
	
	@Async
	public CompletableFuture<List<Orders>> createListOfOrders(List<Orders> orders) {
		return CompletableFuture.completedFuture(saveAllOrder(orders));
	}
	private List<Orders> saveAllOrder(List<Orders> orders) {
		return orderRepo.saveAll(orders);
	}
	
	@Async
	public CompletableFuture<Orders> getOrderById(long orderId) {
		return CompletableFuture.completedFuture(orderRepo.findById(orderId).orElse(null));
	}
	
	@Async
	public CompletableFuture<List<Orders>> findAllOrders(){
		return CompletableFuture.completedFuture(orderRepo.findAll());
	}
	
	@Async
	public CompletableFuture<Orders>  updateOrder(Orders order) {
		Orders oldOrder =null;
		Optional<Orders> optionalOrder = orderRepo.findById(order.getOrderId());
		if(optionalOrder.isPresent()) {
			oldOrder = optionalOrder.get();
			oldOrder.setType(order.getType());
			oldOrder.setPricePerUnit(order.getPricePerUnit());
			oldOrder.setOrderQuantity(order.getOrderQuantity());
			orderRepo.save(oldOrder);
		}
		return CompletableFuture.completedFuture(orderRepo.save(oldOrder));
		
	}
	
	@Async
	public CompletableFuture<String> removeOrderById(long orderId) {
		orderRepo.deleteById(orderId);
		return CompletableFuture.completedFuture("Order with "+orderId+" deleted");
	}
	
	
	
}