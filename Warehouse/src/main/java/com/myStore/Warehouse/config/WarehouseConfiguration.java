package com.myStore.Warehouse.config;

import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.myStore.Warehouse.model.tables.daos.WarehouseProductDetailDao;
import com.myStore.Warehouse.model.tables.daos.WarehouseProductOrderDao;
import com.myStore.Warehouse.response.WarehouseCustomResponse;
import com.myStore.Warehouse.validation.WarehouseProductDetailValidator;
import com.myStore.Warehouse.validation.WarehouseProductOrderValidator;

@Configuration
public class WarehouseConfiguration {

	@Autowired 
	private DSLContext dsl;
	
	@Bean
	public WarehouseProductDetailDao warehouseProductDetailDao() {
		return new WarehouseProductDetailDao(dsl.configuration());
	}
	
	@Bean
	public WarehouseProductOrderDao warehouseProductOrderDao() {
		return new WarehouseProductOrderDao(dsl.configuration());
	}
	
	@Bean
	public WarehouseCustomResponse warehouseCustomResponse() {
		return new WarehouseCustomResponse();
	}
	
	@Bean
	public WarehouseProductOrderValidator warehouseProductOrderValidator() {
		return new WarehouseProductOrderValidator();
	}
	
	@Bean
	public WarehouseProductDetailValidator warehouseProductDetailValidator() {
		return new WarehouseProductDetailValidator();
	}
}
