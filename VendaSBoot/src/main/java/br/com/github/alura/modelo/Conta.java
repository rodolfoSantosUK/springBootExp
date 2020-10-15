package br.com.github.alura.modelo;

import java.util.List;

import javax.persistence.*;

@Entity
@Table( name = "conta")
public class Conta {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String titular;
	private Integer agencia;
	private Integer numero;
	private Double saldo;
	
	@OneToMany(mappedBy = "conta", fetch=FetchType.EAGER)
	private List<Movimentacao> movimentacoes;

	@Override
	public String toString() {
		return "Conta{" +
				"id=" + id +
				", titular='" + titular + '\'' +
				", agencia=" + agencia +
				", numero=" + numero +
				", saldo=" + saldo +
				", movimentacoes=" + movimentacoes +
				'}';
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public Integer getAgencia() {
		return agencia;
	}

	public void setAgencia(Integer agencia) {
		this.agencia = agencia;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}


	public List<Movimentacao> getMovimentacoes() {
		return this.movimentacoes;
	}
}
