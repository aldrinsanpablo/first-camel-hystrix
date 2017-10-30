package com.myStore.Warehouse.controller;

import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jayway.jsonpath.spi.mapper.MappingException;
import com.myStore.Warehouse.constants.WarehouseConstants;
import com.myStore.Warehouse.exceptions.WarehouseCustomException;
import com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail;
import com.myStore.Warehouse.model.tables.pojos.WarehouseProductOrder;
import com.myStore.Warehouse.response.WarehouseCustomResponse;
import com.myStore.Warehouse.service.WarehouseService;
import com.myStore.Warehouse.validation.WarehouseProductDetailValidator;
import com.myStore.Warehouse.validation.WarehouseProductOrderValidator;

@RestController
@Validated
@RequestMapping("myStore/warehouse/")
public class WarehouseController {
	
	private static final Logger logger = LoggerFactory.getLogger(WarehouseController.class);
		
	@Autowired
	public WarehouseProductOrderValidator orderValidator;
	
	@Autowired
	public WarehouseProductDetailValidator stockValidator;
	
	@Autowired
	public WarehouseCustomResponse cusResponse;
	
	@Autowired
	public WarehouseService warehouseService;
	
	@RequestMapping("*")
	public Object fallbackMethod() {
		logger.debug("An invalid HTTP Request has been received.");
		cusResponse.setCode(WarehouseConstants.HTTP_INVALID_REQUEST_CODE);
		cusResponse.setMessage(WarehouseConstants.HTTP_INVALID_REQUEST_MESSAGE);
		return cusResponse;
	}
		
	@RequestMapping(value = "stocks/{id}", method = RequestMethod.GET)
	public Object getProductDetail(@PathVariable int id) { //get stocks and price
		logger.info("Get Product Details (Stocks) for Product ID " + id);
		try {
			WarehouseProductDetail result = warehouseService.getProdDetail(id);
			if(result == null) {
				logger.error("Product Detail ID: " + id + " is not found");
				throw new WarehouseCustomException(WarehouseConstants.EXCEPTION_RECORD_NOT_FOUND);
			}
			else { 
				return result;
			}
		}
		catch ( DataAccessException e) {
			logger.error("Error found on service level...");
			cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
			cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
			return cusResponse; 
		}
		
	}
	
	@RequestMapping(value = "stocks/create", method = RequestMethod.POST)
	public Object createProductDetail(@RequestBody WarehouseProductDetail prodDetail, BindingResult result) {
		logger.info("Creating new product detail...");
		stockValidator.validate(prodDetail, result);
		if(result.hasErrors()) {
			logger.error(WarehouseConstants.PROCESS_FAILED_VALIDATON_CODE);			
			cusResponse.setCode(WarehouseConstants.PROCESS_FAILED_VALIDATON_CODE);
			cusResponse.setMessage(WarehouseConstants.PROCESS_FAILED_VALIDATION_MESSAGE);
			cusResponse.setBody(result.getAllErrors());
			return cusResponse;
		}
		else {
			logger.info(WarehouseConstants.PROCESS_SUCCESSFUL_VALIDATON_MESSAGE);
			try {
				return warehouseService.createProdDetail(prodDetail);
			}
			catch ( DataAccessException e) {
				logger.error("Error found on service level...");
				cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
				cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
				return cusResponse; 
			}
		}
	}
	
	@RequestMapping(value = "stocks/update/{id}", method = RequestMethod.PUT)
	public Object updateProductDetail(@PathVariable int id, 
			@RequestBody WarehouseProductDetail prodDetail,
			BindingResult result) {
		logger.info("Updating product detail: " + id);		
		
		stockValidator.validate(prodDetail, result);
		if(result.hasErrors()) {
			logger.error(WarehouseConstants.PROCESS_FAILED_VALIDATON_CODE);			
			cusResponse.setCode(WarehouseConstants.PROCESS_FAILED_VALIDATON_CODE);
			cusResponse.setMessage(WarehouseConstants.PROCESS_FAILED_VALIDATION_MESSAGE);
			cusResponse.setBody(result.getAllErrors());
			return cusResponse;
		}
		else {
			logger.info(WarehouseConstants.PROCESS_SUCCESSFUL_VALIDATON_MESSAGE);
			try {
				WarehouseProductDetail toUpdate = warehouseService.getProdDetail(id);
				if(toUpdate == null) {
					logger.error("Product Detail ID: " + id + " is not found");
					throw new WarehouseCustomException(WarehouseConstants.EXCEPTION_RECORD_NOT_FOUND);
				}
				else {
					toUpdate.setAvailableStocks(prodDetail.getAvailableStocks());
					toUpdate.setPrice(prodDetail.getPrice());
					toUpdate.setProductId(prodDetail.getProductId());
					return warehouseService.updateProdDetail(toUpdate);
				}
							
			}
			catch ( DataAccessException e) {
				logger.error("Error found on service level...");
				cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
				cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
				return cusResponse; 
			}
		}
	}
	
	@RequestMapping(value = "stocks/delete/{id}", method = RequestMethod.DELETE)
	public Object deleteProductDetail(@PathVariable int id) {
		logger.info("Deleting product detail: " + id);
		try {
			WarehouseProductDetail toDelete = warehouseService.getProdDetail(id);		
			if(toDelete == null) {
				logger.error("Product Detail ID: " + id + " is not found");
				throw new WarehouseCustomException(WarehouseConstants.EXCEPTION_RECORD_NOT_FOUND);
			}
			else { 				
				return warehouseService.deleteProdDetail(toDelete);
			}
		}
		catch ( DataAccessException e) {
			logger.error("Error found on service level...");
			cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
			cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
			return cusResponse; 
		}			
	}
		
	@RequestMapping(value = "order/page/{pageNo}/byStatus/{status}", method = RequestMethod.GET)
	public Object getOrdersByStatus(@PathVariable int pageNo, @PathVariable String status) {
		logger.info("Getting product details by status and page: Status = " + status + ", Page = " + pageNo);
		try {
			return warehouseService.getOrdersByStatus(pageNo, status);
		}
		catch ( DataAccessException | MappingException e) {
			logger.error("Error found on service level...");
			cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
			cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
			return cusResponse; 
		}
	}
	
	@RequestMapping(value = "order/page/{pageNo}", method = RequestMethod.GET)
	public Object getOrders(@PathVariable int pageNo) {
		logger.info("Getting product details with page: Page = " + pageNo);
		try {
			return warehouseService.getOrders(pageNo);
		}
		catch ( DataAccessException | MappingException e) {
			logger.error("Error found on service level...");
			cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
			cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
			return cusResponse; 
		}
	}
	
	@RequestMapping(value = "order/byStatus/{status}", method = RequestMethod.GET)
	public Object getOrdersByStatus(@PathVariable String status) {
		logger.info("Getting product details by status: Status = " + status);
		try {
			return warehouseService.getOrdersByStatus(status);
		}
		catch ( DataAccessException | MappingException e) {
			logger.error("Error found on service level...");
			cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
			cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
			return cusResponse; 
		}
	}
	
	@RequestMapping(value = "order", method = RequestMethod.GET)
	public Object getOrders() {
		logger.info("Getting orders list...");
		try {
			return warehouseService.getOrders();
		}
		catch ( DataAccessException | MappingException e) {
			logger.error("Error found on service level...");
			cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
			cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
			return cusResponse; 
		}
	}
	
	@RequestMapping(value = "order/{id}", method = RequestMethod.GET)
	public Object getOrder(@PathVariable int id) {
		logger.info("Getting order with id=" + id);
		try {		
			WarehouseProductOrder order = warehouseService.getOrderById(id); 	
			if(order == null) {
				logger.error("Product Order ID: " + id + " is not found");
				throw new WarehouseCustomException(WarehouseConstants.EXCEPTION_RECORD_NOT_FOUND);
			}
			else { 
				return order;
			}
		}
		catch ( DataAccessException e) {
			logger.error("Error found on service level...");
			cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
			cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
			return cusResponse; 
		}
	}
	
	@RequestMapping(value = "order/create", method = RequestMethod.POST)
	public Object createOrder(@RequestBody WarehouseProductOrder order, BindingResult result) {
		logger.info("Creating new order...");
		orderValidator.validate(order, result);
		if(result.hasErrors()) {
			logger.error(WarehouseConstants.PROCESS_FAILED_VALIDATON_CODE);			
			cusResponse.setCode(WarehouseConstants.PROCESS_FAILED_VALIDATON_CODE);
			cusResponse.setMessage(WarehouseConstants.PROCESS_FAILED_VALIDATION_MESSAGE);
			cusResponse.setBody(result.getAllErrors());
			return cusResponse;
		}
		else {
			logger.info(WarehouseConstants.PROCESS_SUCCESSFUL_VALIDATON_MESSAGE);
			try {
				return warehouseService.createOrder(order);
			}
			catch ( DataAccessException e) {
				logger.error("Error found on service level...");
				cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
				cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
				return cusResponse; 
			}
		}
	}
	
	@RequestMapping(value = "order/cancel/{id}", method = RequestMethod.PUT)
	public Object cancelOrder(@PathVariable int id) {
		logger.info("Cancelling order: " + id);
		try {
			WarehouseProductOrder orderToUpdae = warehouseService.getOrderById(id);
			if(orderToUpdae == null) {
				logger.error("Product Order ID: " + id + " is not found");
				throw new WarehouseCustomException(WarehouseConstants.EXCEPTION_RECORD_NOT_FOUND);
			}
			else {
					return warehouseService.cancelOrder(orderToUpdae);				
			}
		}
		catch ( DataAccessException e) {
			logger.error("Error found on service level...");
			cusResponse.setCode(WarehouseConstants.SERVICE_ERROR_CODE);
			cusResponse.setMessage(WarehouseConstants.SERVICE_ERROR_MESSAGE);
			return cusResponse; 
		}
	}
	
	
	
}
