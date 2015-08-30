package br.edu.unitri.pshop.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author Oscar Costa <oscarcosta@gmail.com>
 */
public class JsfUtils {
    
    public static String getRequestParameter(String name) {
        FacesContext fc = FacesContext.getCurrentInstance();
        return fc.getExternalContext().getRequestParameterMap().get(name);
    }
    
    public static boolean isBlank(String string) {
        return string == null || string.isEmpty();
    }
    
    protected static void showMessage(String message, FacesMessage.Severity severity) {
        FacesContext fc = FacesContext.getCurrentInstance();
        fc.addMessage(null, new FacesMessage(severity, message, message));
    }
    
    public static void showInfoMessage(String message) {
        showMessage(message, FacesMessage.SEVERITY_INFO);
    }
    
    public static void showWarnMessage(String message) {
        showMessage(message, FacesMessage.SEVERITY_WARN);
    }
    
    public static void showErrorMessage(String message) {
        showMessage(message, FacesMessage.SEVERITY_ERROR);
    }
}
