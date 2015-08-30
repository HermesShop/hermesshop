package br.edu.unitri.pshop.controller;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class LogoutController extends AbstractController  implements Serializable {
	private static final long serialVersionUID = 6920835789315490285L;

	
	public void logout() throws IOException {
		
		getFacesContext().getExternalContext().invalidateSession();
		
		setRedirectPage("/admin/admin.jsf");
	
	}

}