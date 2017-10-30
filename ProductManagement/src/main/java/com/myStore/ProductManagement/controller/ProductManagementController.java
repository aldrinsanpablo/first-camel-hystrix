package com.myStore.ProductManagement.controller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.myStore.ProductManagement.exception.DuplicateEntryException;
import com.myStore.ProductManagement.exception.ServiceException;
import com.myStore.ProductManagement.model.tables.pojos.Category;
import com.myStore.ProductManagement.model.tables.pojos.Product;
import com.myStore.ProductManagement.service.ProductManagementService;
import com.myStore.ProductManagement.validation.CategoryValidator;
import com.myStore.ProductManagement.validation.ProductValidator;
import com.myStore.ProductManagement.web.CustomResponse;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
@RestController
@RequestMapping("/api/v1/prdmgt/")
public class ProductManagementController {

	@Autowired
	public ProductManagementService productManagementService;

	@Autowired
	public ProductValidator productValidator;

	@Autowired
	public CategoryValidator categoryValidator;

	@RequestMapping("*")
	public CustomResponse fallbackMethod() {
		return new CustomResponse("404", "NOT FOUND", null);
	}

	@RequestMapping(value = "error", method = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT })
	public CustomResponse getError(@RequestParam(required = false) String code) throws ServiceException {
		return new CustomResponse("400", "BAD REQUEST", Arrays.asList(new ObjectError("error.redirect", code)));
	}

	@RequestMapping(value = "prd/{id}", method = RequestMethod.GET)
	public Product getProduct(@PathVariable int id, HttpServletResponse response) throws IOException {
		try {
			return this.productManagementService.getProduct(id);
		} catch (NullPointerException npe) {
			response.sendRedirect("/api/v1/prdmgt/error?code=NullPointerException");
		} catch (ServiceException e) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "prd/list/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public List<Product> getProductsWithPageNoAndSize(@PathVariable Integer pageSize, @PathVariable Integer pageNo,
			HttpServletResponse response) throws IOException {
		try {
			return this.productManagementService.getProducts(pageSize, pageNo);
		} catch (NullPointerException npe) {
			response.sendRedirect("/api/v1/prdmgt/error?code=NullPointerException");
		} catch (ServiceException e) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "prd/list/{pageNo}", method = RequestMethod.GET)
	public List<Product> getProductsWithPageNo(@PathVariable Integer pageNo, HttpServletResponse response)
			throws IOException {
		try {
			return this.productManagementService.getProducts(null, pageNo);
		} catch (NullPointerException npe) {
			response.sendRedirect("/api/v1/prdmgt/error?code=NullPointerException");
		} catch (ServiceException e) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "prd/list", method = RequestMethod.GET)
	public List<Product> getProductsDefault(HttpServletResponse response) throws IOException {
		try {
			return this.productManagementService.getProducts(null, null);
		} catch (NullPointerException npe) {
			response.sendRedirect("/api/v1/prdmgt/error?code=NullPointerException");
		} catch (ServiceException e) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "prd/listByCategory/{categoryId}/{pageNo}/{pageSize}", method = RequestMethod.GET)
	public List<Product> getProductsByCategoryId(@PathVariable Integer pageSize, @PathVariable Integer pageNo,
			@PathVariable Integer categoryId, HttpServletResponse response) throws IOException {
		try {
			return this.productManagementService.getProductsByCategoryId(pageSize, pageNo, categoryId);
		} catch (NullPointerException npe) {
			response.sendRedirect("/api/v1/prdmgt/error?code=NullPointerException");
		} catch (ServiceException e) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "prd/create", method = RequestMethod.POST)
	public Object createProduct(@RequestBody Product product, BindingResult bindingResult, HttpServletResponse response)
			throws IOException {
		try {
			this.productValidator.validate(product, bindingResult);
			if (!bindingResult.hasErrors()) {
				product.setId(this.productManagementService.getNextId(product));
				return this.productManagementService.createProduct(product);
			} else {
				return new CustomResponse("error.validation", "Validation failed!", bindingResult.getAllErrors());
			}
		} catch (DuplicateEntryException e) {
			response.sendRedirect("/api/v1/prdmgt/error?code=DuplicateEntryException");
		} catch (NullPointerException | ServiceException se) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "prd/update", method = RequestMethod.PUT)
	public Object updateProduct(@RequestBody Product product, BindingResult bindingResult, HttpServletResponse response)
			throws IOException {
		try {
			this.productValidator.validate(product, bindingResult);
			if (!bindingResult.hasErrors()) {
				return this.productManagementService.updateProduct(product);
			} else {
				return new CustomResponse("error.validation", "Validation failed!", bindingResult.getAllErrors());
			}
		} catch (NullPointerException | ServiceException se) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "prd/delete/{id}", method = RequestMethod.PUT)
	public Object deleteProduct(@PathVariable Integer productId, HttpServletResponse response) throws IOException {
		try {
			if (this.productManagementService.deleteProductById(productId)) {
				return new CustomResponse("success.delete", "Product successfully deleted.", null);
			} else {
				return new CustomResponse("error.delete", "Unable to delete product.", null);
			}
		} catch (NullPointerException | ServiceException se) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "cat/{id}", method = RequestMethod.GET)
	public Category getCategory(@PathVariable int id, HttpServletResponse response) throws IOException {
		try {
			return this.productManagementService.getCategory(id);
		} catch (NullPointerException npe) {
			response.sendRedirect("/api/v1/prdmgt/error?code=NullPointerException");
		} catch (ServiceException e) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "cat/list/{pageSize}/{pageNo}", method = RequestMethod.GET)
	public List<Category> getCategories(@PathVariable Integer pageSize, @PathVariable Integer pageNo,
			HttpServletResponse response) throws IOException {
		try {
			return this.productManagementService.getCategories(pageSize, pageNo);
		} catch (NullPointerException npe) {
			response.sendRedirect("/api/v1/prdmgt/error?code=NullPointerException");
		} catch (ServiceException e) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "cat/create", method = RequestMethod.POST)
	public Object createCategory(@RequestBody Category category, BindingResult bindingResult,
			HttpServletResponse response) throws IOException {
		try {
			this.categoryValidator.validate(category, bindingResult);
			if (!bindingResult.hasErrors()) {
				category.setId(this.productManagementService.getNextId(category));
				this.productManagementService.createCategory(category);
			} else {
				return new CustomResponse("error.validation", "Validation failed!", bindingResult.getAllErrors());
			}
		} catch (DuplicateEntryException e) {
			response.sendRedirect("/api/v1/prdmgt/error?code=DuplicateEntryException");
		} catch (NullPointerException | ServiceException se) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "cat/update", method = RequestMethod.PUT)
	public Object updateCategory(@RequestBody Category category, BindingResult bindingResult,
			HttpServletResponse response) throws IOException {
		try {
			this.categoryValidator.validate(category, bindingResult);
			if (!bindingResult.hasErrors()) {
				return this.productManagementService.updateCategory(category);
			} else {
				return new CustomResponse("error.validation", "Validation failed!", bindingResult.getAllErrors());
			}
		} catch (NullPointerException | ServiceException se) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

	@RequestMapping(value = "cat/delete/{id}", method = RequestMethod.PUT)
	public Object deleteCategory(@PathVariable Integer categoryId, HttpServletResponse response) throws IOException {
		try {
			if (this.productManagementService.deleteCategoryById(categoryId)) {
				return new CustomResponse("success.delete", "Product successfully deleted.", null);
			} else {
				return new CustomResponse("error.delete", "Unable to delete product.", null);
			}
		} catch (NullPointerException | ServiceException se) {
			response.sendRedirect("/api/v1/prdmgt/error?code=ServiceException");
		}
		return null;
	}

}
