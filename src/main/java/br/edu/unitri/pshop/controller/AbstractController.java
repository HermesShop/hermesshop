package br.edu.unitri.pshop.controller;

import java.io.IOException;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class AbstractController {

	/** The ext context. */
	ExternalContext extContext = FacesContext.getCurrentInstance()
			.getExternalContext();

	/**
	 * Sets the redirect page.
	 *
	 * @param page
	 *            the new redirect page
	 * @throws IOException
	 *             Signals that an I/O exception has occurred.
	 */
	protected void setRedirectPage(String page) throws IOException {
		extContext.redirect(extContext.getRequestContextPath() + page);
	}

	protected FacesContext getFacesContext() {
		return FacesContext.getCurrentInstance();
	}

}
