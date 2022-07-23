package com.fdr.forjadaraposa.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "tb_produtos")
public class Produto
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Schema(example = "Link, Zelda, Luffy")
	@NotBlank(message = "Nome precisa ser preenchida")
	private String nome;
	
	@Schema(example = "10cm")
	@NotNull(message = "tamanho não pode ser preenchida")
	private BigDecimal tamanho;
	
	@Schema(example = "R$150,99")
	@NotNull(message = "valor não pode ser nulo")
	@Positive(message = "valor precisa ser positivo")
	@Digits(fraction = 2, integer = 8, message = "Somente até duas casas decimais")
	private BigDecimal valor;
	
	@Schema(example = "0,350Kg")
	@NotNull(message = "valor não pode ser nulo")
	@Positive(message = "valor precisa ser positivo")
	@Digits(fraction = 3, integer = 8, message = "Somente até três casas decimais")
	private BigDecimal peso;
	
	private String foto;
	
	@Column(columnDefinition = "integer default 0")
	private int curtir;
	
	@Schema(example = "Produto é personalizável")
	private boolean personalizavel = true;
	
	@ManyToOne
	@JsonIgnoreProperties("produto")
	private Categoria categoria;
	
	@ManyToOne
	@JsonIgnoreProperties("produtos")
	private Usuario usuario;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	

	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public String getFoto()
	{
		return foto;
	}

	public void setFoto(String foto)
	{
		this.foto = foto;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public BigDecimal getTamanho()
	{
		return tamanho;
	}

	public void setTamanho(BigDecimal tamanho)
	{
		this.tamanho = tamanho;
	}

	public BigDecimal getValor()
	{
		return valor;
	}

	public void setValor(BigDecimal valor)
	{
		this.valor = valor;
	}

	public BigDecimal getPeso()
	{
		return peso;
	}

	public void setPeso(BigDecimal peso)
	{
		this.peso = peso;
	}

	public boolean isPersonalizavel()
	{
		return personalizavel;
	}

	public void setPersonalizavel(boolean personalizavel)
	{
		this.personalizavel = personalizavel;
	}

	public Categoria getCategoria()
	{
		return categoria;
	}

	public void setCategoria(Categoria categoria)
	{
		this.categoria = categoria;
	}

	public int getCurtir()
	{
		return curtir;
	}

	public void setCurtir(int curtir)
	{
		this.curtir = curtir;
	}
	
	
	
}
