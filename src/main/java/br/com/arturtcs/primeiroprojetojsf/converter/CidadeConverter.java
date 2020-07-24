package br.com.arturtcs.primeiroprojetojsf.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.arturtcs.primeiroprojetojsf.entidades.Cidades;
import br.com.arturtcs.primeiroprojetojsf.utils.JPAUtil;

@FacesConverter(forClass = Cidades.class)
public class CidadeConverter implements Converter, Serializable{

	private static final long serialVersionUID = 1L;

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoCidade) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Cidades cidades = (Cidades) em.find(Cidades.class, Long.parseLong(codigoCidade));
		
		return cidades;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object cidade) {
		return ((Cidades) cidade).getId().toString();
	}

	
}
