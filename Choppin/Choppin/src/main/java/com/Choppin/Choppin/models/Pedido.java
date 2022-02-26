package com.Choppin.Choppin.models;

import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;


@Entity
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String datapedido;
	private String dataentrega;
	private String endereco;
	private String produto1;
	private int qtdp1;
	private String produto2;
	private int qtdp2;
	
	@ManyToOne
	private Cliente cliente;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}


	public String getDatapedido() {
		return datapedido;
	}

	public void setDatapedido(String datapedido) {
		this.datapedido = datapedido;
	}

	public String getDataentrega() {
		return dataentrega;
	}

	public void setDataentrega(String dataentrega) {
		this.dataentrega = dataentrega;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getProduto1() {
		return produto1;
	}

	public void setProduto1(String produto1) {
		this.produto1 = produto1;
	}

	public int getQtdp1() {
		return qtdp1;
	}

	public void setQtdp1(int qtdp1) {
		this.qtdp1 = qtdp1;
	}

	
	public String getProduto2() {
		return produto2;
	}

	public void setProduto2(String produto2) {
		this.produto2 = produto2;
	}

	public int getQtdp2() {
		return qtdp2;
	}

	public void setQtdp2(int qtdp2) {
		this.qtdp2 = qtdp2;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}
