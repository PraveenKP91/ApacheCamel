package ch2.apache.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;

public class FileCopy {
	private static CamelContext ctx;
	public static void main(String[] args) throws Exception {
		ctx = new DefaultCamelContext();
		ctx.addRoutes(new RouteBuilder() {
			@Override
			public void configure() throws Exception {
				from("file:pickup_folder?noop=true").to("file:drop_folder");
			}
		});
		while (true)
			ctx.start();
	}
}
