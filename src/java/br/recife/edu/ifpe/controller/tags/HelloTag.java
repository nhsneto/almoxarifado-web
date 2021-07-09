package br.recife.edu.ifpe.controller.tags;

import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;

/**
 *
 * @author Nelson
 */
public class HelloTag extends SimpleTagSupport {

  @Override
  public void doTag() throws JspException, IOException {
    super.doTag();
    getJspContext().getOut().write("<h1>Hello World!</h1>");
  }
  
}
