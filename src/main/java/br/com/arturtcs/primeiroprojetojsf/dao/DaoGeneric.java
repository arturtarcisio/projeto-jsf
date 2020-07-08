package br.com.arturtcs.primeiroprojetojsf.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.arturtcs.primeiroprojetojsf.utils.JPAUtil;

public class DaoGeneric<T> {

	public void salvar(T entidade) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.persist(entidade);
		transaction.commit();
		em.close();
	}

	public T merge(T entidade) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		T entidadeRetornada = em.merge(entidade);
		transaction.commit();
		em.close();
		return entidadeRetornada;
	}

	public void delete(T entidade) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		em.remove(entidade);
		transaction.commit();
		em.close();
	}

	public void deletarPorId(Long id, Class<T> entidade) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		T entidadeParaDeletar = (T) em.find(entidade, id);
		em.remove(entidadeParaDeletar);
		transaction.commit();
		em.close();
	}

	public T pesquisar(Long id, Class<T> entidade) {
		EntityManager em = JPAUtil.getEntityManager();
		T entidadePesquisada = (T) em.find(entidade, id);
		return entidadePesquisada;
	}
	
	public List<T> listarTodos(Class<T> entidade){
		EntityManager em = JPAUtil.getEntityManager();
		List<T> lista = em.createQuery("from " + entidade.getName() + " order by id").getResultList();
		em.close();
		return lista;
	}

}
