package br.com.arturtcs.primeiroprojetojsf.repositories;

import java.util.List;

import javax.faces.model.SelectItem;

import br.com.arturtcs.primeiroprojetojsf.entidades.Pessoa;

public interface InterfaceDaoPessoa {

	Pessoa consultarUsuario(String login, String senha);
	List<SelectItem> listaEstados();
	
}
