package br.com.lojavirtual.rest.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.codehaus.jackson.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name="TB_CLIENTE")
@NamedQuery(name="Cliente.findAll", query="SELECT t FROM Cliente t")
public class Cliente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ClienteSeq")
	@SequenceGenerator(name="ClienteSeq", sequenceName="CLIENTE_SEQ")
	@Column(name="ID_CLIENTE")
	private int id;

	private String nome;

	private String documento;

	private String email;

	//bi-directional many-to-one association to Carrinho
	@JsonIgnore
    @OneToMany(mappedBy = "cliente")   
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)   
	private List<Carrinho> carrinhos;

	public Cliente() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDocumento() {
		return this.documento;
	}

	public void setDocumento(String documento) {
		this.documento = documento;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Carrinho> getCarrinhos() {
		return this.carrinhos;
	}

	public void setCarrinhos(List<Carrinho> carrinhos) {
		this.carrinhos = carrinhos;
	}

	public Carrinho addCarrinho(Carrinho carrinho) {
		getCarrinhos().add(carrinho);
		carrinho.setCliente(this);

		return carrinho;
	}

	public Carrinho removeCarrinho(Carrinho carrinho) {
		getCarrinhos().remove(carrinho);
		carrinho.setCliente(null);

		return carrinho;
	}

}