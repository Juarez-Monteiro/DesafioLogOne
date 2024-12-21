package com.teste.pratico.controller;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class AbstractBean {

	public void infoMensagem(String mensagem) {
		addMensagem(FacesMessage.SEVERITY_INFO, "", mensagem);
	}

	public void erroMensagem(String mensagem) {
		addMensagem(FacesMessage.SEVERITY_ERROR, "", mensagem);
	}
	
	private void addMensagem(FacesMessage.Severity severity, String summary, String detail) {
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, summary, detail));
	}
	
}
