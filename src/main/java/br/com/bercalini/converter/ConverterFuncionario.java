package br.com.bercalini.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.bercalini.dao.FuncionarioDao;
import br.com.bercalini.modelo.Funcionario;

@FacesConverter("funcionarioConverter")
public class ConverterFuncionario implements Converter{

	@Inject
	FuncionarioDao funcionarioDao;
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			Funcionario funcionarioCodigo = funcionarioDao.buscarPorId(codigo); 
			return funcionarioCodigo;
		} catch (RuntimeException e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object objeto) {
		try {
			Funcionario funcionario = (Funcionario) objeto;
			Long codigo = funcionario.getCodigo();
			return codigo.toString();
		} catch (RuntimeException e) {
			return null;
		}
	}

}
