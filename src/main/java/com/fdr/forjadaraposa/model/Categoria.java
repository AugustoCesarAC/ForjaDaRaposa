package com.fdr.forjadaraposa.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table (name = "tb_categorias")
public class Categoria
{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Schema(example = "Anime")
	@NotBlank(message = "Categoria precisa ser preenchida")
	private String prinCategoria;
	
	@Schema(example = "One Piece")
	private String subCategoria;
	
	@OneToMany(mappedBy = "categoria",cascade = CascadeType.REMOVE)
	@JsonIgnoreProperties("categoria")
	private List<Produto> produto;

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public String getPrinCategoria()
	{
		return prinCategoria;
	}

	public void setPrinCategoria(String prinCategoria)
	{
		this.prinCategoria = prinCategoria;
	}

	public String getSubCategoria()
	{
		return subCategoria;
	}

	public void setSubCategoria(String subCategoria)
	{
		this.subCategoria = subCategoria;
	}

	public List<Produto> getProduto()
	{
		return produto;
	}

	public void setProduto(List<Produto> produto)
	{
		this.produto = produto;
	}
	
	
}
