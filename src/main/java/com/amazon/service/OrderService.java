package com.amazon.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.amazon.entity.OrderEntity;
import com.amazon.repository.OrderRepository;
import com.amazon.request.response.OrderRequest;
import com.amazon.request.response.OrderResponse;



@Service
public class OrderService {

	@Autowired
	OrderRepository orderRepository;
	
	private static final Logger log = LoggerFactory.getLogger(OrderService.class);

	public OrderResponse createOrder(OrderRequest orderRequest) {
		
		log.info("Creating order for customer: {}", orderRequest.getCustomerName());

		OrderEntity entity = new OrderEntity();

		entity.setCustomerName(orderRequest.getCustomerName());
		entity.setGst(orderRequest.getGst());
		entity.setStatus("PAID");
		entity.setInvId("INV1234");
		
		log.debug("Saving invoice with invoice ID: {}", entity.getInvId());

		entity = orderRepository.save(entity);
		
		log.info("Order saved successfully with database ID: {}", entity.getId());

		OrderResponse orderResponse = new OrderResponse();

		if (entity.getId() > 0) {

			orderResponse.setCustomerName(entity.getCustomerName());

			orderResponse.setGst(entity.getGst());

			orderResponse.setStatus(entity.getStatus());

			orderResponse.setInvId(entity.getInvId());

			orderResponse.setId(entity.getId());
			
			log.info("Invoice response created successfully for invoice ID: {}", entity.getId());
		}

		return orderResponse;
	}

	// Get Invoices with Pagination

	// Redis cache key example:

	// invoices::0-2 invoices::1-10 invoices::2-10

	// First request -> Database Subsequent request -> Redis

	@Cacheable(value = "invoices", key = "#page + '-' + #size")
	public List<OrderResponse> getOrders(int page, int size) {

		log.info("Fetching invoices - page: {}, size: {}", page, size);

		log.debug("Fetching invoices from DATABASE...");

		Pageable pageable = PageRequest.of(page, size);

		Page<OrderEntity> result = orderRepository.findAll(pageable);
		
		log.info("Fetched {} invoices from database for page: {}, size: {}",
				result.getNumberOfElements(), page, size);


		List<OrderResponse> response = new ArrayList<>();
		
		

		for (OrderEntity orderEntity : result) {

			OrderResponse orderResponse = new OrderResponse();

			orderResponse.setId(orderEntity.getId());

			orderResponse.setInvId(orderEntity.getInvId());

			orderResponse.setCustomerName(orderEntity.getCustomerName());

			orderResponse.setGst(orderEntity.getGst());

			orderResponse.setStatus(orderEntity.getStatus());

			response.add(orderResponse);
		}
		
		log.info("Successfully prepared {} order responses", response.size());

		return response;
	}
}