package com.amazon.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.request.response.OrderRequest;
import com.amazon.request.response.OrderResponse;
import com.amazon.service.OrderService;



@RestController
@RequestMapping("invoice")
public class OrderController {
	@Autowired
	private OrderService orderService ;
	
	private static final Logger log = LoggerFactory.getLogger(OrderController.class);
	
	@PostMapping("create")
	public ResponseEntity createOrder(@RequestBody OrderRequest orderRequest) {
		
		
		log.info("Received request to create invoice for customer: {}", orderRequest.getCustomerName());
		
		OrderResponse response = orderService.createOrder(orderRequest);
		
		log.info("Invoice creation request completed successfully for customer: {}", orderRequest.getCustomerName());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
		
	}
	@GetMapping("listInvoice")
	public ResponseEntity<List<OrderResponse>> getAllOrder(@RequestParam int page, @RequestParam int size){
		
		log.info("Received request to fetch invoices - page: {}, size: {}", page, size);
		
		List<OrderResponse> response = orderService.getOrders(page, size);
		
		log.info("Order list request completed successfully - returned {} invoices", response.size());
		
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);
	}

}
