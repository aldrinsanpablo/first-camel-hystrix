package com.myStore.ProductManagement.service;

import static com.myStore.ProductManagement.model.Tables.CATEGORY;
import static com.myStore.ProductManagement.model.Tables.PRODUCT;
import static com.myStore.ProductManagement.model.Tables.PRODUCT_CATEGORY;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.camel.CamelExecutionException;
import org.apache.camel.ProducerTemplate;
import org.apache.commons.lang3.ObjectUtils;
import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myStore.ProductManagement.exception.DuplicateEntryException;
import com.myStore.ProductManagement.exception.ServiceException;
import com.myStore.ProductManagement.model.ProductDetail;
import com.myStore.ProductManagement.model.tables.daos.CategoryDao;
import com.myStore.ProductManagement.model.tables.daos.ProductCategoryDao;
import com.myStore.ProductManagement.model.tables.daos.ProductDao;
import com.myStore.ProductManagement.model.tables.pojos.Category;
import com.myStore.ProductManagement.model.tables.pojos.Product;
import com.myStore.ProductManagement.model.tables.pojos.ProductCategory;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
@Service
public class ProductManagementService {

	@Autowired
	private DSLContext dsl;

	@Value("${default.page.size}")
	private int defaultPageSize;

	@Autowired
	private ProductDao productDAO;

	@Autowired
	private CategoryDao categoryDAO;

	@Autowired
	private ProductCategoryDao productCategoryDAO;
	
	@Autowired
	private ProducerTemplate producerTemplate;

	@Autowired
	private ObjectMapper objectMapper;

	@Value("${from.endpoint}")
	private String warehouseEndpoint;

	private int getOffSet(Integer pageSize, Integer pageNo) {
		int pgSize = ObjectUtils.firstNonNull(pageSize, defaultPageSize);
		int pgNo = ObjectUtils.firstNonNull(pageNo, 1);
		if (pgNo > 1) {
			return pgSize*(pgNo-1);
		} else {
			return 0;
		}
	}
	
	public int getNextId(Object target) throws ServiceException {
		int nextId = 1;
		if (target != null) {
			try {
				if (target instanceof Category) {
					nextId += this.categoryDAO.count();
				} else if (target instanceof Product) {
					nextId += this.productDAO.count();
				}
			} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
				throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
			}
		}
		return nextId;
	}

	public Product getProduct(int id) throws ServiceException {
		try {
			return this.attachProductInfo(this.productDAO.fetchOneById(id));
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(this.getClass().getName() + "." + e.getStackTrace()[0].getMethodName(),
					e.getClass().getName());
		}
	}

	public List<Product> getProducts(Integer pageSize, Integer pageNo) throws ServiceException {
		List<Product> returnList = null;
		try {
			List<Product> productList = this.dsl
					.select(PRODUCT.ID, PRODUCT.CODE, PRODUCT.NAME, PRODUCT.CREATE_TS, PRODUCT.CREATE_BY,
							PRODUCT.UPDATE_TS, PRODUCT.UPDATE_BY)
					.from(PRODUCT).offset(getOffSet(pageSize,pageNo))
					.limit(ObjectUtils.firstNonNull(pageSize, defaultPageSize)).fetchInto(Product.class);
			if (productList != null) {
				returnList = new ArrayList<Product>();
				for (Product product : productList) {
					returnList.add(this.attachProductInfo(product));
				}
			}
			return returnList;
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException | DataAccessException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public List<Product> getProductsByCategoryId(Integer pageSize, Integer pageNo, Integer categoryId)
			throws ServiceException {
		List<Product> returnList = null;
		try {
			List<Product> productList = this.dsl
					.select(PRODUCT.ID, PRODUCT.CODE, PRODUCT.NAME, PRODUCT.CREATE_TS, PRODUCT.CREATE_BY,
							PRODUCT.UPDATE_TS, PRODUCT.UPDATE_BY)
					.from(PRODUCT).join(PRODUCT_CATEGORY).on(PRODUCT.ID.eq(PRODUCT_CATEGORY.PRODUCT_ID))
					.where(PRODUCT_CATEGORY.CATEGORY_ID.eq(categoryId)).offset(getOffSet(pageSize,pageNo))
					.limit(ObjectUtils.firstNonNull(pageSize, defaultPageSize)).fetchInto(Product.class);
			if (productList != null) {
				returnList = new ArrayList<Product>();
				for(Product product:productList) {
					returnList.add(this.attachProductInfo(product));
				}
			}
			return returnList;
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException | DataAccessException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public Product createProduct(Product newProduct) throws ServiceException, DuplicateEntryException {
		try {
			if (this.productDAO.existsById(newProduct.getId())) {
				throw new DuplicateEntryException();
			} else {
				this.productDAO.insert(newProduct);
				return this.getProduct(newProduct.getId());
			}
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public Product updateProduct(Product productToUpdate) throws ServiceException {
		try {
			this.productDAO.update(productToUpdate);
			return this.productDAO.fetchOneById(productToUpdate.getId());
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public boolean deleteProduct(Product productToDelete) throws ServiceException {
		try {
			this.productDAO.delete(productToDelete);
			return !this.productDAO.existsById(productToDelete.getId());
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}
	
	public boolean deleteProductById(int productId) throws ServiceException {
		try {
			this.productDAO.deleteById(productId);
			return !this.productDAO.existsById(productId);
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public Product createProductCategory(ProductCategory productCategory) throws ServiceException {
		try {
			this.productCategoryDAO.insert(productCategory);
			return this.getProduct(productCategory.getProductId());
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}
	
	public boolean saveProductCategories(List<ProductCategory> mappingList) throws ServiceException {
		boolean returnValue = false;
		try {
			this.productCategoryDAO.insert(mappingList);
			returnValue=true;
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
		return returnValue;
	}
	
	public Category getCategory(int id) throws ServiceException {
		try {
			return this.categoryDAO.fetchOneById(id);
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public List<Category> getCategories(Integer pageSize, Integer pageNo) throws ServiceException {
		try {
			return this.dsl.select(CATEGORY.ID, CATEGORY.CODE, CATEGORY.NAME).from(CATEGORY)
					.offset(getOffSet(pageSize,pageNo))
					.limit(ObjectUtils.firstNonNull(pageSize, defaultPageSize)).fetchInto(Category.class);
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException | DataAccessException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public List<Category> getCategoriesByProductId(Integer productId) throws ServiceException {
		try {
			return this.dsl.select(CATEGORY.ID, CATEGORY.CODE, CATEGORY.NAME).from(CATEGORY).join(PRODUCT_CATEGORY)
					.on(CATEGORY.ID.eq(PRODUCT_CATEGORY.CATEGORY_ID)).where(PRODUCT_CATEGORY.PRODUCT_ID.eq(productId))
					.fetchInto(Category.class);
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException | DataAccessException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public Category createCategory(Category newCategory) throws ServiceException, DuplicateEntryException {
		try {
			if (this.categoryDAO.fetchOneById(newCategory.getId()) != null) {
				this.categoryDAO.insert(newCategory);
				return this.categoryDAO.fetchOneById(newCategory.getId());
			} else {
				throw new DuplicateEntryException();
			}
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public Category updateCategory(Category categoryToUpdate) throws ServiceException {
		try {
			this.categoryDAO.update(categoryToUpdate);
			return this.categoryDAO.fetchOneById(categoryToUpdate.getId());
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public boolean deleteCategory(Category categoryToDelete) throws ServiceException {
		try {
			this.categoryDAO.delete(categoryToDelete);
			return !this.categoryDAO.existsById(categoryToDelete.getId());
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}

	public boolean deleteCategoryById(int categoryId) throws ServiceException {
		try {
			this.categoryDAO.deleteById(categoryId);
			return !this.categoryDAO.existsById(categoryId);
		} catch (IllegalStateException | IllegalArgumentException | NullPointerException e) {
			throw new ServiceException(e.getStackTrace()[0].getMethodName(), e.getClass().getName());
		}
	}
	
	public ProductDetail getProductDetail(int productId) throws CamelExecutionException, IOException {
		ProductDetail det = null;
		Object result = producerTemplate.requestBodyAndHeader(warehouseEndpoint, null, "productId",
				Integer.toString(productId), String.class);
		det = this.objectMapper.readValue(result.toString(), ProductDetail.class);
		return det;
	}

	public Product attachProductInfo(Product product) throws ServiceException {
		Product retrievedProduct = new Product(product);
		try {
			retrievedProduct.setProductDetail(this.getProductDetail(retrievedProduct.getId()));
		} catch (CamelExecutionException | IOException e) {
			retrievedProduct.setProductDetail(new ProductDetail(false));
		}
		retrievedProduct.setCategories(this.getCategoriesByProductId(retrievedProduct.getId()));
		return retrievedProduct;
	}

	public ProductDao getProductDAO() {
		return productDAO;
	}

	public void setProductDAO(ProductDao productDAO) {
		this.productDAO = productDAO;
	}

	public CategoryDao getCategoryDAO() {
		return categoryDAO;
	}

	public void setCategoryDAO(CategoryDao categoryDAO) {
		this.categoryDAO = categoryDAO;
	}

	public DSLContext getDsl() {
		return dsl;
	}

	public void setDsl(DSLContext dsl) {
		this.dsl = dsl;
	}

	public ProductCategoryDao getProductCategoryDAO() {
		return productCategoryDAO;
	}

	public void setProductCategoryDAO(ProductCategoryDao productCategoryDAO) {
		this.productCategoryDAO = productCategoryDAO;
	}

}
