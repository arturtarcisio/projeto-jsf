package br.com.arturtcs.primeiroprojetojsf.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import br.com.arturtcs.primeiroprojetojsf.dao.DaoGeneric;
import br.com.arturtcs.primeiroprojetojsf.entidades.Pessoa;
import br.com.arturtcs.primeiroprojetojsf.repositories.InterfaceDaoPessoa;
import br.com.arturtcs.primeiroprojetojsf.repositories.impl.DaoPessoaImpl;

//Controller
@ManagedBean(name = "pessoaBean")
@ViewScoped
public class PessoaBean {

	private Pessoa pessoa = new Pessoa();
	private DaoGeneric<Pessoa> dao = new DaoGeneric<Pessoa>();
	private List<Pessoa> listaDePessoas = new ArrayList<Pessoa>();
	private InterfaceDaoPessoa iDaoPessoa = new DaoPessoaImpl();

	public String salvar() {
		pessoa = dao.merge(pessoa);
		pessoa = new Pessoa();
		carregarListaDePessoas();
		mostrarMsg("Cadastrado com sucesso!");
		return "";
	}

	private void mostrarMsg(String msg) {
		FacesContext context = FacesContext.getCurrentInstance();
		FacesMessage message = new FacesMessage(msg);
		context.addMessage(null, message);		
	}

	public String novo() {
		//Executa algum processo antes de limpar
		pessoa = new Pessoa();
		return "";
	}
	
	public String limpar() {
		//Executa algum processo antes de limpar
		pessoa = new Pessoa();
		return "";
	}

	public String remove() {
		dao.deletarPorId(pessoa.getId(), Pessoa.class);
		pessoa = new Pessoa();
		carregarListaDePessoas();
		mostrarMsg("Removido com sucesso!");
		return "";
	}
	
	public String logar() {		
		Pessoa pessoaUser = iDaoPessoa.consultarUsuario(pessoa.getLogin(), pessoa.getSenha());
		
		if(pessoaUser != null) {
			//adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser);
			
			return "primeirapagina.jsf";
		}		
		
		return "index.jsf";
	}
	
	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		Pessoa pessoa = (Pessoa) external.getSessionMap().get("usuarioLogado");
		
		return pessoa.getPerfilUser().equals(acesso);
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
