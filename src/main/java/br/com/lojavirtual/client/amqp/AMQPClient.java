package br.com.lojavirtual.client.amqp;

import org.codehaus.jettison.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;

import br.com.lojavirtual.rest.model.Carrinho;

public class AMQPClient {

	static final String URL_MESSAGE_QUEUE_MANAGER = "http://localhost:8080/orders";
    
	public AMQPClient() {
	}

	public void send(Carrinho carrinho) {
		
		try {

			Client client = Client.create();
			
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("id", carrinho.getId());
			
			WebResource webResource = client.resource(URL_MESSAGE_QUEUE_MANAGER);

			webResource.type("application/json").post(String.class, jsonObject);
			
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
	
}