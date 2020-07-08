package br.com.arturtcs.primeiroprojetojsf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.arturtcs.primeiroprojetojsf.dao.DaoGeneric;
import br.com.arturtcs.primeiroprojetojsf.entidades.Pessoa;

//Controller
@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> dao = new DaoGeneric<Pessoa>();
	List<Pessoa> listaDePessoas = new ArrayList<Pessoa>();

	public String salvar() {
		pessoa = dao.merge(pessoa);
		pessoa = new Pessoa();
		carregarListaDePessoas();
		return "";
	}

	public String novo() {
		pessoa = new Pessoa();
		return "";
	}

	public String remove() {
		dao.deletarPorId(pessoa.getId(), Pessoa.class);
		pessoa = new Pessoa();
		carregarListaDePessoas();
		return "";
	}

	/*
	 * Sempre quando abrir a tela que o managed bean for
	 * instanciado, após ser criado em memória ele vai carregar
	 * o metodo que está com esta anotação @PostConstruct
	*/
	@PostConstruct
	public void carregarListaDePessoas() {
		listaDePessoas = dao.listarTodos(Pessoa.class);
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<Pessoa> getListaDePessoas() {
		return listaDePessoas;
	}

	public void setListaDePessoas(List<Pessoa> listaDePessoas) {
		this.listaDePessoas = listaDePessoas;
	}

}
