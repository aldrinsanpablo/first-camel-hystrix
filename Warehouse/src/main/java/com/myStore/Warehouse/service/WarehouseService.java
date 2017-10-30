package com.myStore.Warehouse.service;

import static com.myStore.Warehouse.model.Tables.WAREHOUSE_PRODUCT_DETAIL;
import static com.myStore.Warehouse.model.Tables.WAREHOUSE_PRODUCT_ORDER;

import java.util.Calendar;
import java.util.List;

import org.jooq.DSLContext;
import org.jooq.exception.DataAccessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.jayway.jsonpath.spi.mapper.MappingException;
import com.myStore.Warehouse.model.tables.daos.WarehouseProductDetailDao;
import com.myStore.Warehouse.model.tables.daos.WarehouseProductOrderDao;
import com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail;
import com.myStore.Warehouse.model.tables.pojos.WarehouseProductOrder;

@Service
public class WarehouseService {
	
	@Autowired
	private DSLContext dsl;

	@Autowired
	private WarehouseProductOrderDao orderDAO;

	@Autowired
	private WarehouseProductDetailDao prodDetailsDAO;

	@Value("${order.cancel.limit}")
	private Long orderCancelLimit;

	@Value("${display.record.limit}")
	private int displayRecordLimit;

	public WarehouseProductDetail getProdDetail(int id) throws DataAccessException {
		return prodDetailsDAO.fetchOneById(id);
	}
	
	public int createProdDetail(WarehouseProductDetail prodDetail) throws DataAccessException {
		prodDetailsDAO.insert(prodDetail);
		return prodDetailsDAO.fetchOne(WAREHOUSE_PRODUCT_DETAIL.PRODUCT_ID, prodDetail.getProductId()).getId();
	}
	
	public WarehouseProductDetail updateProdDetail(WarehouseProductDetail prodDetail) throws DataAccessException {
		prodDetailsDAO.update(prodDetail);
		return prodDetail;
	}
	
	public WarehouseProductDetail deleteProdDetail(WarehouseProductDetail prodDetail) throws DataAccessException {
		prodDetailsDAO.delete(prodDetail);
		return prodDetail;
	}

	public List<WarehouseProductOrder> getOrdersByStatus(int pageNo, String status) throws MappingException, DataAccessException {
		return dsl
				.select(WAREHOUSE_PRODUCT_ORDER.ID, 
						WAREHOUSE_PRODUCT_ORDER.STATUS, 
						WAREHOUSE_PRODUCT_ORDER.TIMESTAMP,
						WAREHOUSE_PRODUCT_ORDER.PRODUCT_ID)
				.from(WAREHOUSE_PRODUCT_ORDER)
				.where(WAREHOUSE_PRODUCT_ORDER.STATUS.equalIgnoreCase(status))
				.offset(pageNo) // for pagination
				.limit(displayRecordLimit).fetchInto(WarehouseProductOrder.class);
	}

	public List<WarehouseProductOrder> getOrders(int pageNo) throws MappingException, DataAccessException {
		return dsl
				.select(WAREHOUSE_PRODUCT_ORDER.ID, 
						WAREHOUSE_PRODUCT_ORDER.TIMESTAMP, 
						WAREHOUSE_PRODUCT_ORDER.STATUS,
						WAREHOUSE_PRODUCT_ORDER.PRODUCT_ID)
				.from(WAREHOUSE_PRODUCT_ORDER)
				.offset(pageNo) // for pagination
				.limit(displayRecordLimit).fetchInto(WarehouseProductOrder.class);
	}

	public List<WarehouseProductOrder> getOrdersByStatus(String status) throws MappingException, DataAccessException {
		return dsl
				.select(WAREHOUSE_PRODUCT_ORDER.ID, 
						WAREHOUSE_PRODUCT_ORDER.STATUS, 
						WAREHOUSE_PRODUCT_ORDER.TIMESTAMP,
						WAREHOUSE_PRODUCT_ORDER.PRODUCT_ID)
				.from(WAREHOUSE_PRODUCT_ORDER)
				.where(WAREHOUSE_PRODUCT_ORDER.STATUS.equalIgnoreCase(status))
				.limit(displayRecordLimit).fetchInto(WarehouseProductOrder.class);
	}

	public List<WarehouseProductOrder> getOrders() throws MappingException, DataAccessException {
		return dsl
				.select(WAREHOUSE_PRODUCT_ORDER.ID, 
						WAREHOUSE_PRODUCT_ORDER.STATUS, 
						WAREHOUSE_PRODUCT_ORDER.TIMESTAMP,
						WAREHOUSE_PRODUCT_ORDER.PRODUCT_ID)
				.from(WAREHOUSE_PRODUCT_ORDER).limit(displayRecordLimit).fetchInto(WarehouseProductOrder.class);
	}

	public WarehouseProductOrder getOrderById(int id) throws DataAccessException {
		return orderDAO.fetchOneById(id);
	}
	
	public int createOrder(WarehouseProductOrder newOrder) throws DataAccessException {
		orderDAO.insert(newOrder);
		return orderDAO.fetchOne(WAREHOUSE_PRODUCT_ORDER.TIMESTAMP, newOrder.getTimestamp()).getId();
	}
	public String cancelOrder(WarehouseProductOrder updateOrder) throws DataAccessException {
		if (updateOrder != null) {
			Long currentTimeStamp = Calendar.getInstance().getTimeInMillis();
			Long diff = currentTimeStamp - updateOrder.getTimestamp().getTime();

			if (diff.compareTo(orderCancelLimit) <= 0) {
				updateOrder.setStatus("CANCELED");
				orderDAO.update(updateOrder);
				return "Order Canceled.";
			} else {
				// limit to cancel order reached
				return "Unable to cancel order. Time Limit Reached.";
			}
		} else {
			// order not found
			return "Order Not Found!";
		}
	}

}
