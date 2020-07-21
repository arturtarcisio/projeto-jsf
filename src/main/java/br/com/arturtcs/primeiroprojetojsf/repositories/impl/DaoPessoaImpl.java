package br.com.arturtcs.primeiroprojetojsf.repositories.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.arturtcs.primeiroprojetojsf.entidades.Estados;
import br.com.arturtcs.primeiroprojetojsf.entidades.Pessoa;
import br.com.arturtcs.primeiroprojetojsf.repositories.InterfaceDaoPessoa;
import br.com.arturtcs.primeiroprojetojsf.utils.JPAUtil;

public class DaoPessoaImpl implements InterfaceDaoPessoa {

	public Pessoa consultarUsuario(String login, String senha) {

		Pessoa pessoa = null;

		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		pessoa = (Pessoa) em
				.createQuery("select p from Pessoa p where p.login = '" + login + "' and p.senha = '" + senha + "'")
				.getSingleResult();

		transaction.commit();
		em.close();

		return pessoa;
	}

	@Override
	public List<SelectItem> listaEstados() {

		List<SelectItem> selectItems = new ArrayList<SelectItem>();

		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();

		List<Estados> estados = em.createQuery("from Estados").getResultList();

		for (Estados estado : estados) {
			selectItems.add(new SelectItem(estado, estado.getNome()));
		}

		return selectItems;
	}

}
