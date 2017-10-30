package com.myStore.Warehouse;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;

import com.myStore.Warehouse.controller.WarehouseController;
import com.myStore.Warehouse.model.tables.pojos.WarehouseProductDetail;
import com.myStore.Warehouse.model.tables.pojos.WarehouseProductOrder;
import com.myStore.Warehouse.service.WarehouseService;
import com.myStore.Warehouse.validation.WarehouseProductDetailValidator;
import com.myStore.Warehouse.validation.WarehouseProductOrderValidator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WarehouseApplicationTests {

	@InjectMocks
	private WarehouseController controller;
		
	@Mock
	private WarehouseService service;
	
	@Mock
	private BindingResult mockBindingResult;
		
	@Mock
	private WarehouseProductOrderValidator orderVal;
	
	@Mock
	private WarehouseProductDetailValidator detailVal;
	
	@Before
	public void init() {
		MockitoAnnotations.initMocks(this);
		when(mockBindingResult.hasErrors()).thenReturn(false);	
	}
	
	@Test
	public void testGetOrder() {
		WarehouseProductOrder order = new WarehouseProductOrder();
		order.setId(1);
		when(service.getOrderById(1)).thenReturn(order);
		
		WarehouseProductOrder newOrder = (WarehouseProductOrder)controller.getOrder(1);
		assertThat(newOrder.getId(), is(1));
		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetOrders() {
		List<WarehouseProductOrder> list = new ArrayList<WarehouseProductOrder>();	
		WarehouseProductOrder order = new WarehouseProductOrder();
		order.setId(1);
		order.setStatus("OPEN");
		list.add(order);
		
		order = new WarehouseProductOrder();
		order.setId(2);
		order.setStatus("CANCELED");
		list.add(order);
		
		when(service.getOrders()).thenReturn(list);
		
		List<WarehouseProductOrder> result = (List<WarehouseProductOrder>) controller.getOrders();

		int size = result.size();
		assertEquals(2, size);
		assertThat(result.get(0).getId(), is(1));
		assertThat(result.get(1).getStatus(), is("CANCELED"));
		
	}
	
	@Test
	public void testCreateOrder() {
		WarehouseProductOrder order = new WarehouseProductOrder();
		order.setId(1);
		order.setProductId(1);
		order.setStatus("OPEN");
		order.setTimestamp(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		doNothing().when(orderVal).validate(any(WarehouseProductOrder.class), any(Errors.class));
		when(mockBindingResult.hasErrors()).thenReturn(false);
		when(service.createOrder(any(WarehouseProductOrder.class))).thenReturn(1);
		
		Object newOrder = controller.createOrder(order, mockBindingResult);
		
		assertThat(newOrder, is(1));
	}
	
	@Test
	public void testCancelOrder() {
		WarehouseProductOrder order = new WarehouseProductOrder();
		order.setTimestamp(new Timestamp(System.currentTimeMillis()));
		order.setStatus("OPEN");
		order.setId(1);
		order.setProductId(1);
		when(service.getOrderById(1)).thenReturn(order);		
		when(service.cancelOrder(order)).thenReturn("Order Canceled.");
				
		String result = (String) controller.cancelOrder(1);
		assertThat(result, is("Order Canceled."));
		
		
	}
	
	@Test
	public void testCancelOrderNegative() {
		WarehouseProductOrder order = new WarehouseProductOrder();
		Calendar now = Calendar.getInstance();
		now.add(Calendar.HOUR, 25);		
		order.setTimestamp(new Timestamp(now.getTimeInMillis()));
		order.setStatus("OPEN");
		order.setId(1);
		when(service.getOrderById(1)).thenReturn(order);		
		when(service.cancelOrder(order)).thenReturn("Unable to cancel order. Time Limit Reached.");
				
		String result = (String) controller.cancelOrder(1);
		assertThat(result, is("Unable to cancel order. Time Limit Reached."));
	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void testGetOrderByStatus() {
		List<WarehouseProductOrder> list = new ArrayList<WarehouseProductOrder>();	
		WarehouseProductOrder order = new WarehouseProductOrder();
		order.setId(1);
		order.setStatus("OPEN");
		list.add(order);
		
		when(service.getOrdersByStatus("OPEN")).thenReturn(list);
		
		List<WarehouseProductOrder> result = (List<WarehouseProductOrder>) controller.getOrdersByStatus("OPEN");

		assertThat(result.get(0).getStatus(), is("OPEN"));
	}
	
	@SuppressWarnings("unchecked")
	@Test 
	public void testGetOrderByStatusWithPage() {
		List<WarehouseProductOrder> list = new ArrayList<WarehouseProductOrder>();	
		WarehouseProductOrder order = new WarehouseProductOrder();
		order.setId(1);
		order.setStatus("OPEN");
		list.add(order);
		
		order = new WarehouseProductOrder();
		order.setId(2);
		order.setStatus("CANCELED");
		list.add(order);
		
		when(service.getOrdersByStatus(0, "OPEN")).thenReturn(list);
		
		List<WarehouseProductOrder> result = (List<WarehouseProductOrder>) controller.getOrdersByStatus(0, "OPEN");

		//check limit on property file, this should be set to 1 for this test to work
		assertThat(result.get(0).getId(), is(1));		
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testGetOrdersWithPage() {
		List<WarehouseProductOrder> list = new ArrayList<WarehouseProductOrder>();	
		WarehouseProductOrder order = new WarehouseProductOrder();
		order.setId(1);
		order.setStatus("CANCELED");
		list.add(order);
		
		order = new WarehouseProductOrder();
		order.setId(2);
		order.setStatus("OPEN");
		list.add(order);
		
		when(service.getOrders(0)).thenReturn(list);
		
		List<WarehouseProductOrder> result = (List<WarehouseProductOrder> ) controller.getOrders(0);

		//check limit on property file, this should be set to 1 for this test to work
		assertThat(result.get(0).getStatus(), is("CANCELED"));	
	}
	
	@Test
	public void testGetProductDetail() {
		WarehouseProductDetail stock = new WarehouseProductDetail();
		stock.setId(1);
		when(service.getProdDetail(1)).thenReturn(stock);
		
		WarehouseProductDetail detail = (WarehouseProductDetail) controller.getProductDetail(1);
		assertThat(detail.getId(), is(1));
	}
	
	@Test
	public void testCreateProductDetail() {
		WarehouseProductDetail stock = new WarehouseProductDetail();
		stock.setId(1);
		stock.setAvailableStocks(7);
		stock.setPrice(100.40);
		stock.setProductId(1);
				
		doNothing().when(detailVal).validate(any(WarehouseProductDetail.class), any(Errors.class));
		when(mockBindingResult.hasErrors()).thenReturn(false);
		when(service.createProdDetail(any(WarehouseProductDetail.class))).thenReturn(1);
		
		Object detail = controller.createProductDetail(stock, mockBindingResult);
		assertThat(detail, is(1));
	}
	
	@Test
	public void testUpdateProductDetail() {
		WarehouseProductDetail stock = new WarehouseProductDetail();
		stock.setPrice(100.40);
		stock.setId(1);
		stock.setAvailableStocks(10);
		stock.setProductId(1);
		
		WarehouseProductDetail expected = new WarehouseProductDetail();
		expected.setPrice(0.00); //from 100.40 
		expected.setId(1);
		expected.setAvailableStocks(10);
		expected.setProductId(1);		
						
		doNothing().when(detailVal).validate(any(WarehouseProductDetail.class), any(Errors.class));
		when(mockBindingResult.hasErrors()).thenReturn(false);
		when(service.getProdDetail(1)).thenReturn(stock);
		when(service.updateProdDetail(any(WarehouseProductDetail.class))).thenReturn(expected);
		
		
		WarehouseProductDetail toUpdate = new WarehouseProductDetail();
		toUpdate.setPrice(0.00); //from 100.40 
		toUpdate.setId(1);
		toUpdate.setAvailableStocks(10);
		toUpdate.setProductId(1);
		
		WarehouseProductDetail detail = (WarehouseProductDetail) controller.updateProductDetail(1, toUpdate, mockBindingResult);
		assertThat(detail.getPrice(), is(0.00));
		assertThat(detail.getId(), is(1));
	}
	
	@Test
	public void testDeleteProductDetail() {
		WarehouseProductDetail stock = new WarehouseProductDetail();
		stock.setId(11111);
		when(service.getProdDetail(anyInt())).thenReturn(stock);	
		when(service.deleteProdDetail(any(WarehouseProductDetail.class))).thenReturn(stock);
		
		WarehouseProductDetail detail = (WarehouseProductDetail) controller.deleteProductDetail(11111);
		assertThat(detail.getId(), is(11111));
	}

}
