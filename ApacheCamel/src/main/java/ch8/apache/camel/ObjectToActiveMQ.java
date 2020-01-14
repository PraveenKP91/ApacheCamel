package ch8.apache.camel;

import java.util.Date;

import javax.jms.ConnectionFactory;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.jms.JmsComponent;
import org.apache.camel.impl.DefaultCamelContext;

public class ObjectToActiveMQ {
  public static void main(String[] args) throws Exception {
    CamelContext ctx = new DefaultCamelContext();

    ConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
    ctx.addComponent("jms", JmsComponent.jmsComponentAutoAcknowledge(connectionFactory));

    ctx.addRoutes(new RouteBuilder() {

      @Override
      public void configure() throws Exception {
          from("direct:start").to("activemq:queue:my_queue");
      }
    });
    
    ctx.start();
    
    ProducerTemplate producerTemplate = ctx.createProducerTemplate();
    producerTemplate.sendBody("direct:start", new Date());

  }
}
