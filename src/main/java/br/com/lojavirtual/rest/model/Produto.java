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
@Table(name = "TB_PRODUTO")
@NamedQuery(name = "Produto.findAll", query = "SELECT t FROM Produto t")
public class Produto implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ProdutoSeq")
	@SequenceGenerator(name = "ProdutoSeq", sequenceName = "PRODUTO_SEQ")
	@Column(name = "ID_PRODUTO")
	private int id;

	private String nome;

	private double preco;

	private int quantidade;

	// bi-directional many-to-one association to Carrinho
	@JsonIgnore
    @OneToMany(mappedBy = "produto")   
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)   
	private List<Carrinho> carrinhos;

	public Produto() {
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

	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return this.quantidade;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public List<Carrinho> getCarrinhos() {
		return this.carrinhos;
	}

	public void setCarrinhos(List<Carrinho> carrinhos) {
		this.carrinhos = carrinhos;
	}

	public Carrinho addCarrinho(Carrinho carrinho) {
		getCarrinhos().add(carrinho);
		carrinho.setProduto(this);

		return carrinho;
	}

	public Carrinho removeCarrinho(Carrinho carrinho) {
		getCarrinhos().remove(carrinho);
		carrinho.setProduto(null);

		return carrinho;
	}

	public void setIncrementaEstoque(int qtd) {
		quantidade = quantidade + qtd;
		setQuantidade(quantidade);
	}

	public void setDecrementaEstoque(int qtd) throws Exception {
		if (quantidade < qtd) {
			throw new Exception("Quantidade de produto maior do que estoque.");
		} else {
			quantidade = quantidade - qtd;
		}
		setQuantidade(quantidade);
	}

}