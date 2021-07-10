package br.recife.edu.ifpe.controller.tags;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Nelson
 */
public class Tag2 extends SimpleTagSupport {
  private String var;
  public List attr3;
  
  public void setVar(String var) {
    this.var = var;
  }
  
  public void setAttr3(List attr3) {
    this.attr3 = attr3;
  }
  
  @Override
  public void doTag() throws JspException, IOException {
    super.doTag();
    StringWriter tagBody = new StringWriter();
    getJspBody().invoke(tagBody);
    getJspContext().getOut().write("<h1>" + tagBody.toString() + " valor: " + this.var +"</h1>" + this.attr3.size());
  }
}
