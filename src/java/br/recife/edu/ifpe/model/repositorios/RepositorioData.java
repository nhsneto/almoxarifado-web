package br.recife.edu.ifpe.model.repositorios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class RepositorioData {
  private static RepositorioData myself = null;
  private List<Date> datas = null;
  
  private RepositorioData() {
    this.datas = new ArrayList<>();
  }
  
  public static RepositorioData getInstance() {
    if (myself == null) myself = new RepositorioData();
    return myself;
  }
  
  public void create(Date date) {
    this.datas.add(date);
  }
  
  public List<Date> readAll() {
    return this.datas;
  }
}