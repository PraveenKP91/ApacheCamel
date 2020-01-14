package ch1.apache.camel;

import org.apache.camel.builder.RouteBuilder;

public class HelloWorldRoute extends RouteBuilder {

	@Override
	public void configure() throws Exception {
		System.out.println("Hello World it's first program using Camel");
	}

}
