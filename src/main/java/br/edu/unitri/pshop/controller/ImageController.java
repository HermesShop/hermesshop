/*package br.edu.unitri.pshop.controller;

import java.io.FileInputStream;
import java.io.IOException;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

@Named
@ApplicationScoped
public class ImageController {
	 private static final String PATH_IMAGES = "/Users/rogeriofontes/images";

	  public StreamedContent getImage() throws IOException {
	    FacesContext context = FacesContext.getCurrentInstance();
	    DefaultStreamedContent content = new DefaultStreamedContent();
	    content.setContentType("image/jpg");
	    if (context.getRenderResponse()) {
	      return content;
	    }
	    else {
	      String name = context.getExternalContext().getRequestParameterMap()
	          .get("name");
	      content.setStream(new FileInputStream(PATH_IMAGES + name));
	      return content;
	    }
	  }
}
*/