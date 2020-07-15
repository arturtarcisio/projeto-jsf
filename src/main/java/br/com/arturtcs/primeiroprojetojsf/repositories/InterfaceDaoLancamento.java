package br.com.arturtcs.primeiroprojetojsf.repositories;

import java.util.List;

import br.com.arturtcs.primeiroprojetojsf.entidades.Lancamento;

public interface InterfaceDaoLancamento {
	
	List<Lancamento> consultarPorUsuarioLogado(Long codUser);

}
