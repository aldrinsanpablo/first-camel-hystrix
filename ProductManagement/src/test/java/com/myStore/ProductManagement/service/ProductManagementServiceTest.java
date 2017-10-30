package com.myStore.ProductManagement.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.myStore.ProductManagement.exception.DuplicateEntryException;
import com.myStore.ProductManagement.exception.ServiceException;
import com.myStore.ProductManagement.model.tables.pojos.Category;
import com.myStore.ProductManagement.model.tables.pojos.Product;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ProductManagementServiceTest {

	@Mock
	ProductManagementService productManagementService;

	Product testProduct;

	Category testCategory;

	@Before
	public void setupMock() {
		MockitoAnnotations.initMocks(this);

		Timestamp ts = new Timestamp(System.currentTimeMillis());

		this.testCategory = new Category();
		this.testCategory.setId(1);
		this.testCategory.setCode("CATEGORY_CODE");
		this.testCategory.setName("Test Category");
		this.testCategory.setCreateBy("mockito");
		this.testCategory.setCreateTs(ts);
		this.testCategory.setUpdateBy("mockito");
		this.testCategory.setUpdateTs(ts);

		this.testProduct = new Product();
		this.testProduct.setId(1);
		this.testProduct.setCode("TS-123456");
		this.testProduct.setName("Test Product 123456");
		this.testProduct.setCreateBy("mockito");
		this.testProduct.setCreateTs(ts);
		this.testProduct.setUpdateBy("mockito");
		this.testProduct.setUpdateTs(ts);
	}

	@Test
	public void getCategory() {

		try {
			when(this.productManagementService.getCategory(1)).thenReturn(this.testCategory);

			Category retrievedCategory = this.productManagementService.getCategory(1);
			assertThat(retrievedCategory.getId(), is(equalTo(this.testCategory.getId())));
			assertThat(retrievedCategory.getCode(), is(equalTo(this.testCategory.getCode())));
			assertThat(retrievedCategory.getName(), is(equalTo(this.testCategory.getName())));
			assertThat(retrievedCategory.getCreateBy(), is(equalTo(this.testCategory.getCreateBy())));
			assertThat(retrievedCategory.getCreateTs(), is(equalTo(this.testCategory.getCreateTs())));
			assertThat(retrievedCategory.getUpdateBy(), is(equalTo(this.testCategory.getUpdateBy())));
			assertThat(retrievedCategory.getUpdateTs(), is(equalTo(this.testCategory.getUpdateTs())));
		} catch (ServiceException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}

	@Test
	public void getCategories() {
		List<Category> categoryList = new ArrayList<Category>();
		categoryList.add(this.testCategory);

		try {
			when(this.productManagementService.getCategories(1, 1)).thenReturn(categoryList);

			List<Category> retrievedList = this.productManagementService.getCategories(1, 1);
			assertTrue(retrievedList != null);
			assertThat(retrievedList.size(), is(equalTo(1)));
			Category retrievedCategory = retrievedList.get(0);
			assertThat(retrievedCategory.getId(), is(equalTo(this.testCategory.getId())));
			assertThat(retrievedCategory.getCode(), is(equalTo(this.testCategory.getCode())));
			assertThat(retrievedCategory.getName(), is(equalTo(this.testCategory.getName())));
			assertThat(retrievedCategory.getCreateBy(), is(equalTo(this.testCategory.getCreateBy())));
			assertThat(retrievedCategory.getCreateTs(), is(equalTo(this.testCategory.getCreateTs())));
			assertThat(retrievedCategory.getUpdateBy(), is(equalTo(this.testCategory.getUpdateBy())));
			assertThat(retrievedCategory.getUpdateTs(), is(equalTo(this.testCategory.getUpdateTs())));
		} catch (ServiceException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}

	@Test
	public void createCategory() {
		try {
			when(this.productManagementService.createCategory(any(Category.class))).thenReturn(this.testCategory);

			Category retrievedCategory = this.productManagementService.createCategory(this.testCategory);
			assertThat(retrievedCategory.getId(), is(equalTo(this.testCategory.getId())));
			assertThat(retrievedCategory.getCode(), is(equalTo(this.testCategory.getCode())));
			assertThat(retrievedCategory.getName(), is(equalTo(this.testCategory.getName())));
			assertThat(retrievedCategory.getCreateBy(), is(equalTo(this.testCategory.getCreateBy())));
			assertThat(retrievedCategory.getCreateTs(), is(equalTo(this.testCategory.getCreateTs())));
			assertThat(retrievedCategory.getUpdateBy(), is(equalTo(this.testCategory.getUpdateBy())));
			assertThat(retrievedCategory.getUpdateTs(), is(equalTo(this.testCategory.getUpdateTs())));
		} catch (ServiceException | DuplicateEntryException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}

	@Test
	public void updateCategory() {
		try {
			when(this.productManagementService.updateCategory(any(Category.class))).thenReturn(this.testCategory);

			Category retrievedCategory = this.productManagementService.updateCategory(this.testCategory);
			assertThat(retrievedCategory.getId(), is(equalTo(this.testCategory.getId())));
			assertThat(retrievedCategory.getCode(), is(equalTo(this.testCategory.getCode())));
			assertThat(retrievedCategory.getName(), is(equalTo(this.testCategory.getName())));
			assertThat(retrievedCategory.getCreateBy(), is(equalTo(this.testCategory.getCreateBy())));
			assertThat(retrievedCategory.getCreateTs(), is(equalTo(this.testCategory.getCreateTs())));
			assertThat(retrievedCategory.getUpdateBy(), is(equalTo(this.testCategory.getUpdateBy())));
			assertThat(retrievedCategory.getUpdateTs(), is(equalTo(this.testCategory.getUpdateTs())));
		} catch (ServiceException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}

	@Test
	public void deleteCategory() {
		try {
			when(this.productManagementService.deleteCategory(any(Category.class))).thenReturn(true);
			assertTrue(this.productManagementService.deleteCategory(this.testCategory));
		} catch (ServiceException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}

	@Test
	public void getProduct() {
		try {
			when(this.productManagementService.getProduct(1)).thenReturn(this.testProduct);

			Product retrievedProduct = this.productManagementService.getProduct(1);
			assertThat(retrievedProduct.getId(), is(equalTo(this.testProduct.getId())));
			assertThat(retrievedProduct.getCode(), is(equalTo(this.testProduct.getCode())));
			assertThat(retrievedProduct.getName(), is(equalTo(this.testProduct.getName())));
			assertThat(retrievedProduct.getCreateBy(), is(equalTo(this.testProduct.getCreateBy())));
			assertThat(retrievedProduct.getCreateTs(), is(equalTo(this.testProduct.getCreateTs())));
			assertThat(retrievedProduct.getUpdateBy(), is(equalTo(this.testProduct.getUpdateBy())));
			assertThat(retrievedProduct.getUpdateTs(), is(equalTo(this.testProduct.getUpdateTs())));
		} catch (ServiceException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}

	@Test
	public void getProducts() {
		List<Product> productList = new ArrayList<Product>();
		productList.add(this.testProduct);

		try {
			when(this.productManagementService.getProducts(1, 1)).thenReturn(productList);

			List<Product> retrievedList = this.productManagementService.getProducts(1, 1);
			assertTrue(retrievedList != null);
			assertThat(retrievedList.size(), is(equalTo(1)));
			Product retrievedProduct = retrievedList.get(0);
			assertThat(retrievedProduct.getId(), is(equalTo(this.testProduct.getId())));
			assertThat(retrievedProduct.getCode(), is(equalTo(this.testProduct.getCode())));
			assertThat(retrievedProduct.getName(), is(equalTo(this.testProduct.getName())));
			assertThat(retrievedProduct.getCreateBy(), is(equalTo(this.testProduct.getCreateBy())));
			assertThat(retrievedProduct.getCreateTs(), is(equalTo(this.testProduct.getCreateTs())));
			assertThat(retrievedProduct.getUpdateBy(), is(equalTo(this.testProduct.getUpdateBy())));
			assertThat(retrievedProduct.getUpdateTs(), is(equalTo(this.testProduct.getUpdateTs())));
		} catch (ServiceException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}

	@Test
	public void createProduct() {
		try {
			when(this.productManagementService.createProduct(any(Product.class))).thenReturn(this.testProduct);
			Product createdProduct = this.productManagementService.createProduct(this.testProduct);
			assertThat(createdProduct.getId(), is(equalTo(this.testProduct.getId())));
			assertThat(createdProduct.getCode(), is(equalTo(this.testProduct.getCode())));
			assertThat(createdProduct.getName(), is(equalTo(this.testProduct.getName())));
			assertThat(createdProduct.getCreateBy(), is(equalTo(this.testProduct.getCreateBy())));
			assertThat(createdProduct.getCreateTs(), is(equalTo(this.testProduct.getCreateTs())));
			assertThat(createdProduct.getUpdateBy(), is(equalTo(this.testProduct.getUpdateBy())));
			assertThat(createdProduct.getUpdateTs(), is(equalTo(this.testProduct.getUpdateTs())));
		} catch (ServiceException | DuplicateEntryException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}

	@Test
	public void updateProduct() {
		try {
			when(this.productManagementService.updateProduct(any(Product.class))).thenReturn(this.testProduct);

			Product retrievedProduct = this.productManagementService.updateProduct(this.testProduct);
			assertThat(retrievedProduct.getId(), is(equalTo(this.testProduct.getId())));
			assertThat(retrievedProduct.getCode(), is(equalTo(this.testProduct.getCode())));
			assertThat(retrievedProduct.getName(), is(equalTo(this.testProduct.getName())));
			assertThat(retrievedProduct.getCreateBy(), is(equalTo(this.testProduct.getCreateBy())));
			assertThat(retrievedProduct.getCreateTs(), is(equalTo(this.testProduct.getCreateTs())));
			assertThat(retrievedProduct.getUpdateBy(), is(equalTo(this.testProduct.getUpdateBy())));
			assertThat(retrievedProduct.getUpdateTs(), is(equalTo(this.testProduct.getUpdateTs())));
		} catch (ServiceException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}

	@Test
	public void deleteProduct() {
		try {
			when(this.productManagementService.deleteProduct(any(Product.class))).thenReturn(true);
			assertTrue(this.productManagementService.deleteProduct(this.testProduct));
		} catch (ServiceException e) {
			System.out.println(e.getInfo());
			assertTrue(false);
		}
	}
	
	@Test
	public void test() {
		Double dbl = 123098123789897123978789123123.1237913047807894509245;
		Integer intgr = 999999999;
		assertTrue(dbl != null);
		System.out.println(dbl);
	}

}
