package br.com.bercalini.gerenciamento;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import br.com.bercalini.anotacoes.Transacao;

@SuppressWarnings("serial")
@Interceptor
@Transacao
public class Gerenciamento implements Serializable{
	
	@Inject
	EntityManager manager;
	
	@AroundInvoke
	public Object gerenciarTransacoes(InvocationContext contexto) throws Exception {
		manager.getTransaction().begin();
		Object objeto = contexto.proceed();
		manager.getTransaction().commit();
		return objeto;
	}
}
