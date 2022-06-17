package com.fdr.forjadaraposa.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fdr.forjadaraposa.model.Categoria;
import com.fdr.forjadaraposa.repository.CategoriaRepository;
import com.fdr.forjadaraposa.service.CategoriaService;

@RestController
@RequestMapping("/categorias")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoriaController
{

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private CategoriaService categoriaService;

	@GetMapping
	public ResponseEntity<List<Categoria>> getAll()
	{
		return ResponseEntity.ok(categoriaRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Categoria> getById(@PathVariable Long id)
	{
		return categoriaRepository.findById(id).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.badRequest().build());
	}

//	@GetMapping("/nome/{prinCategoria}")
//	public ResponseEntity<List<Categoria>> getByPrinCategoria(@PathVariable String prinCategoria)
//	{
//		return ResponseEntity.ok(categoriaRepository.findAllByPrinCategoriaContainingIgnoreCase(prinCategoria));
//	}

	@GetMapping("/nome/{subCategoria}")
	public ResponseEntity<List<Categoria>> getBySubCategoria(@PathVariable String subCategoria)
	{
		return ResponseEntity.ok(categoriaRepository.findAllBySubCategoriaContainingIgnoreCase(subCategoria));
	}

	@PostMapping
	public ResponseEntity<Categoria> postCategoria(@Valid @RequestBody Categoria categoria)
	{
		// if (categoriaRepository.findBySubCategoria(categoria.getSubCategoria().is));

		return categoriaService.cadastrarCategoria(categoria)
				.map(resposta -> ResponseEntity.status(HttpStatus.CREATED).body(resposta))
				.orElse(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());
	}

	@PutMapping
	public ResponseEntity<Categoria> putCategoria(@Valid @RequestBody Categoria categoria)
	{
		return categoriaService.atualizarCategoria(categoria)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(resposta))
				.orElse(ResponseEntity.notFound().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteCategoria(@PathVariable Long id)
	{
		if (categoriaRepository.existsById(id))
		{
			categoriaRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
