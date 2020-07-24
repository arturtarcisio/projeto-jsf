package br.com.arturtcs.primeiroprojetojsf.converter;

import java.io.Serializable;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import br.com.arturtcs.primeiroprojetojsf.entidades.Estados;
import br.com.arturtcs.primeiroprojetojsf.utils.JPAUtil;

@FacesConverter(forClass = Estados.class)
public class EstadoConverter implements Converter, Serializable {

	private static final long serialVersionUID = 1L;

	//Retorna objeto inteiro
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String codigoEstado) {
		EntityManager em = JPAUtil.getEntityManager();
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Estados estados = (Estados) em.find(Estados.class, Long.parseLong(codigoEstado));
		
		return estados;
	}

	//Retorna apenas o código em String
	@Override
	public String getAsString(FacesContext context, UIComponent component, Object estado) {
		return ((Estados) estado).getId().toString();
	}
	
	

}
