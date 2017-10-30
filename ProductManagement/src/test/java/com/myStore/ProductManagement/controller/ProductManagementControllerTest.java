package com.myStore.ProductManagement.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
@RunWith(SpringRunner.class)
@WebMvcTest(ProductManagementController.class)
public class ProductManagementControllerTest {

	@Autowired
	private MockMvc mvc;

	@MockBean
	private ProductManagementController productManagementController;

	@Test
	public void getProduct() throws Exception {
		MvcResult
		mvcResult = this.mvc.perform(get("/api/v1/prdmgt/prd/3")).andExpect(status().isOk()).andReturn();
		System.out.println(mvcResult);
	}
}
