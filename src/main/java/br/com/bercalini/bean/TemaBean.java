package br.com.bercalini.bean;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
@SuppressWarnings("serial")
public class TemaBean implements Serializable{
	
	private String tema = "afterdark";

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}
	
	
}
