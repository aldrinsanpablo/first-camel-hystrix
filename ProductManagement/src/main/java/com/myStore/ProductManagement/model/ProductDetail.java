package com.myStore.ProductManagement.model;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
public class ProductDetail {

	private static String FALLBACK_MSG = "Item %s not yet available.";
	
	private Integer id;
	private Integer productId;
	private String availableStocks;
	private String price;

	public ProductDetail() {
		super();
	}
	
	public ProductDetail(boolean isAvailable) {
		super();
		if ( !isAvailable ) {
			this.availableStocks = String.format(FALLBACK_MSG, "stocks");
			this.price = String.format(FALLBACK_MSG, "price");
		}
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getAvailableStocks() {
		return availableStocks;
	}

	public void setAvailableStocks(String availableStocks) {
		this.availableStocks = availableStocks;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

}
