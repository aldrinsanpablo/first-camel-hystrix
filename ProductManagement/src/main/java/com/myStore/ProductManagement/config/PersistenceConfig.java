package com.myStore.ProductManagement.config;

import org.apache.camel.CamelContext;
import org.jooq.DSLContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myStore.ProductManagement.model.tables.daos.CategoryDao;
import com.myStore.ProductManagement.model.tables.daos.ProductCategoryDao;
import com.myStore.ProductManagement.model.tables.daos.ProductDao;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
@Configuration
public class PersistenceConfig {

	@Autowired
	private DSLContext dsl;
	
	@Autowired
	private CamelContext camelContext;
	
	@Bean
	public ProductDao productDao() {
		return new ProductDao(dsl.configuration());
	}
	
	@Bean
	public CategoryDao categoryDao() {
		return new CategoryDao(dsl.configuration());
	}
	
	@Bean
	public ProductCategoryDao productCategoryDao() {
		return new ProductCategoryDao(dsl.configuration());
	}
	
	@Bean
	public ObjectMapper objectMapper() {
	    return new ObjectMapper();
	}
	
}
