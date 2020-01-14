package ApacheCamel.ApacheCamel;

import org.apache.camel.builder.RouteBuilder;

public class FileRouteBuilder extends RouteBuilder {
  @Override
  public void configure() throws Exception {
    // for copy file noop=true
    // for move file delete=true
    from("file:/home/billion/C/Files/ApacheCamel/SourceFolder?delete=true")
        .to("file:/home/billion/C/Files/ApacheCamel/DestinationFolder");
  }
}
