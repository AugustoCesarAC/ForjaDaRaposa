package com.fdr.forjadaraposa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdr.forjadaraposa.model.Produto;
import com.fdr.forjadaraposa.repository.CategoriaRepository;
import com.fdr.forjadaraposa.repository.ProdutoRepository;

@Service
public class ProdutoService
{

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Produto> cadastrarProduto(Produto produto)
	{
		if (produto.getCategoria().getId() == null 
				|| !categoriaRepository.existsById(produto.getCategoria().getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "É preciso ID da categoria", null);

		return Optional.of(produtoRepository.save(produto));
	}

	public Optional<Produto> atualizarProduto(Produto produto)
	{
		if (produto.getCategoria().getId() == null 
				|| produto.getId() == null
				|| !categoriaRepository.existsById(produto.getCategoria().getId())
				|| !produtoRepository.existsById(produto.getId()))
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não pode ser nula", null);

		if (produtoRepository.findById(produto.getId()).isPresent())
			return Optional.ofNullable(produtoRepository.save(produto));

		return Optional.empty();
	}

	public Optional<Produto> curtir(Long id)
	{
		if (produtoRepository.existsById(id))
		{
			Produto produto = produtoRepository.findById(id).get();
			produto.setCurtir(produto.getCurtir() + 1);
			return Optional.of(produtoRepository.save(produto));
		}
		return Optional.empty();
	}

}
