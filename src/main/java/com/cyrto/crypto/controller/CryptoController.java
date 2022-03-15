package com.cyrto.crypto.controller;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cyrto.crypto.entity.Orders;
import com.cyrto.crypto.exception.AsyncCallFailedException;
import com.cyrto.crypto.model.CoinType;
import com.cyrto.crypto.service.OrderService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/cryptoapp")
public class CryptoController {

	static final Log log = LogFactory.getLog(CryptoController.class);
	
	@Autowired
	private OrderService service;
	
	
	// Sample implementation of Exception handling using Controller advice
	@PostMapping("/addorder")
	public ResponseEntity<?> addOrder(@RequestBody Orders order) {
		Orders responseOrder = null;
		try {
			responseOrder = service.createOrder(order).get();  
		}catch(InterruptedException | ExecutionException e) {
			log.error("Unable to save orders due to ",e);
			throw new AsyncCallFailedException("Proxy object is null due to "+e.getLocalizedMessage());
		}
		return ResponseEntity.status(HttpStatus.OK).body(responseOrder);
	}
	
	@GetMapping("/getordersummary/{type}")
	public ResponseEntity<?> getSummaryByCoinType(@PathVariable CoinType type){
		List<Orders> listOfOrders = new ArrayList<>();
		try {
			listOfOrders = service.getSummaryInfo(type).get();
		} catch (InterruptedException | ExecutionException e) {
			log.error("Unable to save orders due to ",e);
		}
		return ResponseEntity.status(HttpStatus.OK).body(listOfOrders);
	}
	
	
	@PostMapping("/addorders")
	public  ResponseEntity<?> addOrders(@RequestBody List<Orders> orders) {
		List<Orders> responseOrdersList = null;
		try {
			responseOrdersList =  service.createListOfOrders(orders).get();
		}catch(InterruptedException | ExecutionException e) {
			log.error("Unable to save orders due to ",e);
		}
		
		return  ResponseEntity.status(HttpStatus.OK).body(responseOrdersList);
	}
	
	@GetMapping("/getorderbyid/{id}")
	public  ResponseEntity<?> getOrderById(@PathVariable long id) {
		Orders responseOrder = null;
		try {
			responseOrder = service.getOrderById(id).get();
		}catch(InterruptedException | ExecutionException e) {
			log.error("Unable to get order by id "+id,e);
		}
		return  ResponseEntity.status(HttpStatus.OK).body(responseOrder);
	}
	
	/*
	 * Imagine this method return huge data; for example 1GB of orders, In such situations
	 * if the end user is accessing this API using Webclient like browser, then the browser cannot
	 * handle that much of size and at the same time we do not want to take an overhead of storing the 
	 * response in file format on network drive either.In such situation we try to convert the response into Json file 
	 * on the fly and allow it to download using following implementation. 
	 * 
	 * Springboot automatically autowires HttpResponse as method parameter as shown
	 */
	@RequestMapping(value="/listoforders", method=RequestMethod.GET, produces = MediaType.APPLICATION_PROBLEM_JSON_VALUE)
	public  ResponseEntity<?> findAllOrders(HttpServletResponse response){
		List<Orders> responseList = null;
		try {
			responseList = service.findAllOrders().get();
			 
			response.setHeader("Content-Disposition","attachment; filename=allorders.json");
			ObjectMapper mapper = new ObjectMapper();
			byte[] dataInbytes = mapper.writeValueAsBytes(responseList)	;	
			InputStream is = new ByteArrayInputStream(dataInbytes);
			return ResponseEntity.ok().contentLength(dataInbytes.length).
					contentType(MediaType.parseMediaType("application/octet-stream")).
					body(new InputStreamResource(is));
		}catch(InterruptedException | ExecutionException | JsonProcessingException e) {
			log.error("Unable to find all orders ",e);
		}
		return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PutMapping("/updateorder")
	public  ResponseEntity<?> updateOrder(@RequestBody Orders order) {
		Orders updatedOrder = null;
		try {
			updatedOrder = service.updateOrder(order).get();
		}catch(InterruptedException | ExecutionException e) {
			log.error("Unable to update order ",e);
		}
		return  ResponseEntity.status(HttpStatus.OK).body(updatedOrder);
	}
	
	@DeleteMapping("/deleteorderbyid/{id}")
	public  ResponseEntity<?> deleteOrder(@PathVariable long id) {
		String response=null;
		try {
			response = service.removeOrderById(id).get();
		}catch(InterruptedException | ExecutionException e) {
			log.error("Unable to delete order ",e);
		}
		return  ResponseEntity.status(HttpStatus.OK).body(response);
	}
	
}

