package br.com.bercalini.util;

import java.io.Serializable;

@SuppressWarnings("serial")
public class RedirecionadorPaginas implements Serializable{
	
	
	
	public String direcionadorPaginas(String direcao) {
		return direcao + "?faces-redirect=true";
	}

	
	
	
}
