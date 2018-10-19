package br.com.lojavirtual.rest.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.lojavirtual.rest.model.Carrinho;
import br.com.lojavirtual.util.JpaEntityManager;

@Path("/carrinho")
public class CarrinhoService {

	private JpaEntityManager JPAEM = new JpaEntityManager();
	private EntityManager em = JPAEM.getEntityManager();
	private Carrinho carrinho;

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Carrinho> listar() {

		try {
			String query = "select c from Carrinho c";
			List<Carrinho> carrinhos = em.createQuery(query, Carrinho.class).getResultList();
			em.close();
			return carrinhos;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/buscar/{id}")
	@Produces("application/json")
	public Carrinho buscar(@PathParam("id") int id) {
		try {
			Carrinho carrinho = em.find(Carrinho.class, id);
			em.close();
			return carrinho;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	public Response cadastrar(Carrinho carrinho) {
		try {
			em.getTransaction().begin();
			em.persist(carrinho);
			em.getTransaction().commit();
			em.close();
			return Response.status(200).entity("cadastro realizado.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@PUT
	@Path("/alterar")
	@Consumes("application/json")
	public Response alterar(Carrinho carrinho) {
		try {
			em.getTransaction().begin();
			em.merge(carrinho);
			em.getTransaction().commit();
			em.close();
			return Response.status(200).entity("cadastro alterado.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("/excluir/{id}")
	public Response excluir(@PathParam("id") int id) {
		try {
			Carrinho carrinho = em.find(Carrinho.class, id);

			em.getTransaction().begin();
			em.remove(carrinho);
			em.getTransaction().commit();
			em.close();
			
			this.setCarrinho(carrinho);

			return Response.status(200).entity("cadastro excluído.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho) {
		this.carrinho = carrinho;
	}
	
}
