package br.com.bercalini.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.inject.Inject;

import br.com.bercalini.dao.FornecedorDao;
import br.com.bercalini.modelo.Fornecedor;

@FacesConverter("fornecedorConverter")
public class ConverterFornecedor implements Converter {

	@Inject
	private FornecedorDao fornecedorDao;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String valor) {
		try {
			Long codigo = Long.parseLong(valor);
			Fornecedor buscaPorCodigo = fornecedorDao.buscaPorCodigo(codigo);
			return buscaPorCodigo;
		} catch (RuntimeException e) {
			return null;
		}

	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object objeto) {
		try {
			Fornecedor fornecedor = (Fornecedor) objeto;
			Long codigo = fornecedor.getCodigo();
			return codigo.toString();
		} catch (RuntimeException e) {
			return null;
		}
	}

}
