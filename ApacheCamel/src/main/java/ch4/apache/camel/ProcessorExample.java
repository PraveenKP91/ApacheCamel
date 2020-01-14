package ch4.apache.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class ProcessorExample {
	public static void main(String[] args) throws Exception {
		CamelContext camelContext = new DefaultCamelContext();
		camelContext.addRoutes(new RouteBuilder() {

			@Override
			public void configure() throws Exception {
				from("direct:produce").process(new Processor() {

					public void process(Exchange exchange) throws Exception {
						System.out.println("Hello i am processor");

					}
				}).to("seda:consume");
			}
		});
		
		camelContext.start();
		
		ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
		producerTemplate.sendBody("direct:produce", "Hello dear");
		
		ConsumerTemplate consumerTemplate = camelContext.createConsumerTemplate();
		String message = consumerTemplate.receiveBody("seda:consume",String.class);
		
		System.out.println(message);
	}
}
