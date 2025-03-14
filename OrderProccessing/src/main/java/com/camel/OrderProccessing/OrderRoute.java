package com.camel.OrderProccessing;

import org.apache.camel.Exchange;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.dataformat.JsonLibrary;
import org.springframework.stereotype.Component;


@Component
public class OrderRoute extends RouteBuilder{

	@Override
	public void configure() throws Exception {
		
		 onException(Exception.class)
         	.log("Error processing file: ${header.CamelFileName}")
         	.to("file:src/main/resources/error")
         	.handled(true);	 
		 
		 // обработка заказов json-> pojo
		 
	     from("file:src/main/resources/input?noop=true") 
         	.log("Processing file:\n ${header.CamelFileName}")
         	.log("Processing file:\n ${body}")
         	.unmarshal().json(JsonLibrary.Jackson, Order.class)
         	.log("Parsed JSON: ${body}") 
         
         // обработка ошибок
         	
         	.choice()
         	
         // Проверка на пустое имя покупателя
         	
         		.when(simple("${body.customer} == null || ${body.customer.trim()} == ''"))
         			.log("Invalid order: Customer is empty")
         			.marshal().json(JsonLibrary.Jackson) 
         			.to("file:src/main/resources/error") 
         		
         // Проверка на цену <= 0		
         			
         		.when().method("orderValidator", "hasInvalidPrices")
         	        .log("Invalid order: Wrong price")
         	        .marshal().json(JsonLibrary.Jackson) 
         	        .to("file:src/main/resources/error")
         			
         	        
         // Проверка на пустое имя товара
         			
         	    .when().method("orderValidator", "hasEmptyItemNames")
         	        .log("Invalid order: Item name is empty")
         	        .marshal().json(JsonLibrary.Jackson)
         	        .to("file:src/main/resources/error")
                    
         		.otherwise()
         			.to("direct:convertToXml");          	
         	

	     
	     // pojo -> xml
	     
	     from("direct:convertToXml")
            .marshal().jacksonXml()
            .log("Marshalled XML:\n ${body}")
            .to("xslt:order-to-xml.xsl") 
            .log("Converted XML:\n ${body}")
            .to("direct:sendToRestApi");
	     
	     // отправка на сервер
	     from("direct:sendToRestApi")
         	.setHeader(Exchange.CONTENT_TYPE, constant("application/xml"))
         	.log("Sending XML to API: ${body}") 
         	.to("http://localhost:8080/orders")
         	.log("Order sent successfully!")
         	.to("file:src/main/resources/processed");
		
	}

}
