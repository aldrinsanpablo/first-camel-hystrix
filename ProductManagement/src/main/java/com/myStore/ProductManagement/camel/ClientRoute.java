package com.myStore.ProductManagement.camel;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.myStore.ProductManagement.exception.ServiceException;

/**
 * 
 * @author al-drin.g.san.pablo
 *
 */
@Component
public class ClientRoute extends RouteBuilder {

	@Value("${from.endpoint}")
	private String fromEndpoint;

	@Value("${warehouse.route.url}")
	private String warehouseRouteUrl;

	@Override
	public void configure() {
		from(fromEndpoint).hystrix().hystrixConfiguration().executionTimeoutInMilliseconds(5000)
				.circuitBreakerSleepWindowInMilliseconds(10000).end()
				.setHeader(Exchange.HTTP_METHOD, constant(HttpMethod.GET))
				.setHeader(Exchange.HTTP_QUERY, simple("productId=${header.productId}"))
				.toD(warehouseRouteUrl + "${header.productId}").onFallback()
				.throwException(new ServiceException(fromEndpoint, "hystrixFallback")).end();
	}

}
