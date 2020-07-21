package br.com.arturtcs.primeiroprojetojsf.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

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
	private List<SelectItem> estados;

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
		// Executa algum processo antes de limpar
		pessoa = new Pessoa();
		return "";
	}

	public String limpar() {
		// Executa algum processo antes de limpar
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

		if (pessoaUser != null) {
			// adicionar o usuário na sessão usuarioLogado
			FacesContext context = FacesContext.getCurrentInstance();
			ExternalContext externalContext = context.getExternalContext();
			externalContext.getSessionMap().put("usuarioLogado", pessoaUser);

			return "primeirapagina.jsf";
		}

		return "index.jsf";
	}

	public String logout() {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext externalContext = context.getExternalContext();
		externalContext.getSessionMap().remove("usuarioLogado");

		HttpServletRequest httpServletRequest = (HttpServletRequest) context.getCurrentInstance().getExternalContext()
				.getRequest();
		
		httpServletRequest.getSession().invalidate();

		return "index.jsf";
	}

	public boolean permiteAcesso(String acesso) {
		FacesContext context = FacesContext.getCurrentInstance();
		ExternalContext external = context.getExternalContext();
		Pessoa pessoa = (Pessoa) external.getSessionMap().get("usuarioLogado");

		return pessoa.getPerfilUser().equals(acesso);
	}

	public void pesquisaCep(AjaxBehaviorEvent event) {
		try {
			URL url = new URL("https://viacep.com.br/ws/" + pessoa.getCep() + "/json/"); // monta url
			URLConnection connection = url.openConnection(); // abre conexao

			InputStream is = connection.getInputStream(); // executa lá do lado do servidor do ws
			BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

			String cep = "";
			StringBuilder jsonCep = new StringBuilder();

			while ((cep = br.readLine()) != null) {
				jsonCep.append(cep);
			}

			Pessoa gsonAux = new Gson().fromJson(jsonCep.toString(), Pessoa.class);

			pessoa.setCep(gsonAux.getCep());
			pessoa.setLogradouro(gsonAux.getLogradouro());
			pessoa.setComplemento(gsonAux.getComplemento());
			pessoa.setBairro(gsonAux.getBairro());
			pessoa.setLocalidade(gsonAux.getLocalidade());
			pessoa.setUf(gsonAux.getUf());
			pessoa.setUnidade(gsonAux.getUnidade());
			pessoa.setIbge(gsonAux.getIbge());
			pessoa.setGia(gsonAux.getGia());

		} catch (Exception e) {
			e.printStackTrace();
			mostrarMsg("Erro ao consultar o cep");
		}
	}
	
	public void carregaCidades(AjaxBehaviorEvent event) {
		String codigoEstado = (String) event.getComponent().getAttributes().get("submittedValue");
		if(codigoEstado != null) {
			System.out.println(codigoEstado);
		}
	}

	/*
	 * Sempre quando abrir a tela que o managed bean for instanciado, após ser
	 * criado em memória ele vai carregar o metodo que está com esta
	 * anotação @PostConstruct
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

	public List<SelectItem> getEstados() {
		estados = iDaoPessoa.listaEstados();
		return estados;
	}

	public void setEstados(List<SelectItem> estados) {
		this.estados = estados;
	}
	
	

}
