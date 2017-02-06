package br.com.bercalini.util;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
public class MensagemUtil implements Serializable{
	
	public void mensagem(String texto) {
		FacesContext faces = FacesContext.getCurrentInstance();
		FacesMessage mensagem = new FacesMessage(FacesMessage.SEVERITY_INFO, texto, texto);
		faces.addMessage(null, mensagem);
	}
}
