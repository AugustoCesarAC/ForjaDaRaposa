package com.fdr.forjadaraposa.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fdr.forjadaraposa.model.Categoria;
import com.fdr.forjadaraposa.repository.CategoriaRepository;

@Service
public class CategoriaService
{

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Optional<Categoria> cadastrarCategoria(Categoria categoria)
	{
		if (categoriaRepository.findBySubCategoria(categoria.getSubCategoria()).isPresent())
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Subcategoria já existe!!!", null);

		return Optional.of(categoriaRepository.save(categoria));
	}

	public Optional<Categoria> atualizarCategoria(Categoria categoria)
	{
		if (categoria.getId() == null)
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id não pode ser nula", null);

		if (categoriaRepository.findById(categoria.getId()).isPresent())
			return Optional.ofNullable(categoriaRepository.save(categoria));

		return Optional.empty();
	}

}
