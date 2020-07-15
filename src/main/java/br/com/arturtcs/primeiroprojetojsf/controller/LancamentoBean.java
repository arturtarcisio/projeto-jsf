package br.com.arturtcs.primeiroprojetojsf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.arturtcs.primeiroprojetojsf.dao.DaoGeneric;
import br.com.arturtcs.primeiroprojetojsf.entidades.Lancamento;
import br.com.arturtcs.primeiroprojetojsf.entidades.Pessoa;
import br.com.arturtcs.primeiroprojetojsf.repositories.InterfaceDaoLancamento;
import br.com.arturtcs.primeiroprojetojsf.repositories.impl.DaoLancamentoImpl;

@ViewScoped
@ManagedBean(name = "lancamentoBean")
public class LancamentoBean {

	private Lancamento lancamento = new Lancamento();
	private DaoGeneric<Lancamento> dao = new DaoGeneric<Lancamento>();
	private List<Lancamento> listaLancamento = new ArrayList<Lancamento>();
	private InterfaceDaoLancamento daoLancamento = new DaoLancamentoImpl();

	public String salvar() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");

		lancamento.setUsuario(pessoaUser);
		lancamento = dao.merge(lancamento);
		lancamento = new Lancamento();
		
		carregarListaDeLancamentos();

		return "";
	}
	
	@PostConstruct
	public void carregarListaDeLancamentos() {		
		//criar um metodo private pra pegar o usuario logado na sessao para nao ter que ficar repetindo codigo
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		Pessoa pessoaUser = (Pessoa) externalContext.getSessionMap().get("usuarioLogado");
		listaLancamento = daoLancamento.consultarPorUsuarioLogado(pessoaUser.getId());
	}
	
	public String novo() {
		lancamento = new Lancamento();
		return "";
	}
	
	public String remover() {
		dao.deletarPorId(lancamento.getId(), Lancamento.class);
		lancamento = new Lancamento();
		carregarListaDeLancamentos();
		return "";
	}
	
	

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public DaoGeneric<Lancamento> getDao() {
		return dao;
	}

	public void setDao(DaoGeneric<Lancamento> dao) {
		this.dao = dao;
	}

	public List<Lancamento> getListaLancamento() {
		return listaLancamento;
	}

	public void setListaLancamento(List<Lancamento> listaLancamento) {
		this.listaLancamento = listaLancamento;
	}

}
