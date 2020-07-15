package br.com.arturtcs.primeiroprojetojsf.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.arturtcs.primeiroprojetojsf.entidades.Lancamento;
import br.com.arturtcs.primeiroprojetojsf.repositories.InterfaceDaoLancamento;
import br.com.arturtcs.primeiroprojetojsf.utils.JPAUtil;

public class DaoLancamentoImpl implements InterfaceDaoLancamento{

	@Override
	public List<Lancamento> consultarPorUsuarioLogado(Long codUser) {
		List<Lancamento> lista = new ArrayList<Lancamento>();
		
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		
		transaction.begin();
		
		lista = em.createQuery("from Lancamento where usuario.id = " + codUser).getResultList();
		
		transaction.commit();
		em.close();
		
		
		return lista;
	}

	

}
