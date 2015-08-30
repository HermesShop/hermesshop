package br.edu.unitri.pshop.controller;

import java.io.IOException;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpServletRequest;

public class AbstractController {

	// bundle com as mensagens internacionalizaveis
	protected ResourceBundle bundle;

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

	/**
	 * Recebe como parametro uma chave, faz a traducao da chave para a mensagem
	 * olhando o bundle e a adiciona no escopo {@link Flash} da aplicacao.
	 * 
	 * @param chave
	 * @param severidade
	 */
	public void addMessageFromBundleInFlash(Severity severidade, String chave) {
		Flash flash = getFacesContext().getExternalContext().getFlash();
		flash.setKeepMessages(true);
		getFacesContext().addMessage(null,
				new FacesMessage(severidade, null, bundle.getString(chave)));
	}

}
