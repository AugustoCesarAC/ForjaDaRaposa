package com.fdr.forjadaraposa.model;

public class UsuarioLogin 
{

	private Long id;

	private String usuario;

	private String senha;
	
	private String token;
	
	private String tipo;


	public UsuarioLogin(Long id, String usuario, String senha, String token, String tipo)
	{
		this.id = id;
		this.usuario = usuario;
		this.senha = senha;
		this.token = token;
		this.tipo = tipo;
	}

	// Segundo método Construtor

	public UsuarioLogin() {	}
	
	// Terceiro método Construtor
	
	public UsuarioLogin(String usuario, String senha) 
	{
		this.usuario = usuario;
		this.senha = senha;
	}

	public Long getId()
	{
		return id;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	
	
	public String getTipo()
	{
		return tipo;
	}

	public void setTipo(String tipo)
	{
		this.tipo = tipo;
	}

	public String getUsuario()
	{
		return usuario;
	}

	public void setUsuario(String usuario)
	{
		this.usuario = usuario;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public String getToken()
	{
		return token;
	}

	public void setToken(String token)
	{
		this.token = token;
	}
	
	
	
}
