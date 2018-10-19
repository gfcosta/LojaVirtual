package br.com.lojavirtual.rest.service;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import br.com.lojavirtual.client.amqp.AMQPClient;
import br.com.lojavirtual.rest.model.Carrinho;

@Path("/compra")
public class CompraService {

	private ProdutoService serviceProduto = new ProdutoService();
	private CarrinhoService serviceCarrinho = new CarrinhoService();

	@POST
	@Path("/efetuar")
	@Consumes("application/json")
	public Response efetuar(Carrinho carrinho) {
		try {
			
			if (serviceCarrinho.cadastrar(carrinho).getStatus() == 200) {

				carrinho.getProduto().setDecrementaEstoque(carrinho.getQuantidade()); //Decrementa o estoque

				if (serviceProduto.alterar(carrinho.getProduto()).getStatus() == 200) {

					// Envia para fila de mensagens
					AMQPClient amqp = new AMQPClient();
					amqp.send(carrinho);

					return Response.status(200).entity("compra efetuada.").build();
				}
			}

			return Response.status(400).entity("compra não efetuada.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}

	@DELETE
	@Path("/estornar/{id}")
	@Produces("application/json")
	public Response estornar(@PathParam("id") int id) {
		try {

			if (serviceCarrinho.excluir(id).getStatus() == 200) {
				
				Carrinho carrinho = serviceCarrinho.getCarrinho();

				carrinho.getProduto().setIncrementaEstoque(carrinho.getQuantidade()); //Incrementa o estoque

				if (serviceProduto.alterar(carrinho.getProduto()).getStatus() == 200) {
					
					return Response.status(200).entity("compra estornada.").build();
				}
			}

			return Response.status(400).entity("compra não estornada.").build();
		} catch (Exception e) {
			throw new WebApplicationException(500);
		}
	}
	
}
