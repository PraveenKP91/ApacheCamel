package ch10.apache.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

public class CallMethodUsingBeanCamel {
  public static void main(String[] args) throws Exception {
    
    MyClass myClass = new MyClass();
    
    SimpleRegistry simpleRegistry = new SimpleRegistry();
    simpleRegistry.put("myClass",myClass);
    
    CamelContext camelContext = new DefaultCamelContext(simpleRegistry);
    
    camelContext.addRoutes(new RouteBuilder() {
      
      @Override
      public void configure() throws Exception {
        from("direct:start").to("bean:myClass?method=doSomething");
      }
    });
    
    camelContext.start();
    
    ProducerTemplate producerTemplate = camelContext.createProducerTemplate();
    producerTemplate.sendBody("direct:start","Hello Bean");
    
  }
}
