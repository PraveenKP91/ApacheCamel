package ch11.apache.camel;

import java.util.List;

public class ResultMapper {
public void printResult(List<? extends Object> list) {
  for(int i=0;i<list.size();i++)
  {
    System.out.println(list.get(i));
  }
}
}
