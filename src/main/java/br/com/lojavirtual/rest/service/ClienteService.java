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

import br.com.lojavirtual.rest.model.Cliente;
import br.com.lojavirtual.util.JpaEntityManager;

@Path("/cliente")
public class ClienteService {

	private JpaEntityManager JPAEM = new JpaEntityManager();
	private EntityManager em = JPAEM.getEntityManager();

	@GET
	@Path("/listar")
	@Produces("application/json")
	public List<Cliente> listar() {

		try {
			String query = "select c from Cliente c";
			List<Cliente> clientes = em.createQuery(query, Cliente.class).getResultList();
			em.close();
			return clientes;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@GET
	@Path("/buscar/{id}")
	@Produces("application/json")
	public Cliente buscar(@PathParam("id") int id) {
		try {
			Cliente cliente = em.find(Cliente.class, id);
			em.close();
			return cliente;
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@POST
	@Path("/cadastrar")
	@Consumes("application/json")
	public Response cadastrar(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.persist(cliente);
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
	public Response alterar(Cliente cliente) {
		try {
			em.getTransaction().begin();
			em.merge(cliente);
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
			Cliente cliente = em.find(Cliente.class, id);

			em.getTransaction().begin();
			em.remove(cliente);
			em.getTransaction().commit();
			em.close();

			return Response.status(200).entity("cadastro excluído.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

}
