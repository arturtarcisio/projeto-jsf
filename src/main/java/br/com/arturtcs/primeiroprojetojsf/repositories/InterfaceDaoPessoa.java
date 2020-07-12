package br.com.arturtcs.primeiroprojetojsf.repositories;

import br.com.arturtcs.primeiroprojetojsf.entidades.Pessoa;

public interface InterfaceDaoPessoa {

	Pessoa consultarUsuario(String login, String senha);
	
}
