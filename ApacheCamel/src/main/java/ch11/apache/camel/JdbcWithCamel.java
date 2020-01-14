package ch11.apache.camel;

import org.apache.camel.CamelContext;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.impl.SimpleRegistry;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class JdbcWithCamel {
  public static void main(String[] args) throws Exception {

    MysqlDataSource dataSource = new MysqlDataSource();
    dataSource.setUrl("jdbc:mysql://localhost:3306/TestDB");
    dataSource.setUser("root");
    dataSource.setPassword("");
    
    SimpleRegistry registry = new SimpleRegistry();
    registry.put("dataSource", dataSource);
    
    CamelContext context = new DefaultCamelContext(registry);
    
    context.addRoutes(new RouteBuilder() {
      @Override
      public void configure() throws Exception {
        from("direct:start").to("jdbc:dataSource").bean(new ResultMapper(), "printResult");
      }
    });
    
    context.start();
    
    ProducerTemplate template = context.createProducerTemplate();
    template.sendBody("direct:start", "select * from customer");
  }
}
