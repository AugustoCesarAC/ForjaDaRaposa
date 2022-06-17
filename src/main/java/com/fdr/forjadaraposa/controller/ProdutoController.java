package com.fdr.forjadaraposa.controller;

import java.math.BigDecimal;
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

import com.fdr.forjadaraposa.model.Produto;
import com.fdr.forjadaraposa.repository.ProdutoRepository;
import com.fdr.forjadaraposa.service.ProdutoService;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class ProdutoController
{

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private ProdutoService produtoService;

	@GetMapping
	public ResponseEntity<List<Produto>> getAll()
	{
		return ResponseEntity.ok(produtoRepository.findAll());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Produto> getById(@PathVariable Long id)
	{
		return produtoRepository.findById(id)
				.map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.badRequest().build());
	}

	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> getByNome(@PathVariable String nome)
	{
		return ResponseEntity.ok(produtoRepository.findAllByNomeContainingIgnoreCase(nome));
	}
	// Ordem crescente
	@GetMapping("/maiorc/{valor}")
	public ResponseEntity<List<Produto>> getByMaior(@PathVariable BigDecimal valor)
	{
		return ResponseEntity.ok(produtoRepository.findByValorGreaterThanEqualOrderByValor(valor));
	}
	
	@GetMapping("/menorc/{valor}")
	public ResponseEntity<List<Produto>> getByMenor(@PathVariable BigDecimal valor)
	{
		return ResponseEntity.ok(produtoRepository.findByValorLessThanEqualOrderByValor(valor));
	}
	
	@GetMapping("/curtidas/{curtir}")
	public ResponseEntity<List<Produto>> getByCurtir(@PathVariable int curtir)
	{
		return ResponseEntity.ok(produtoRepository.findByCurtirGreaterThanEqualOrderByCurtir(curtir));
	}
	
	// Ordem decrescente
	@GetMapping("/maiord/{valor}")
	public ResponseEntity<List<Produto>> getByMaiorDesc(@PathVariable BigDecimal valor)
	{
		return ResponseEntity.ok(produtoRepository.findByValorGreaterThanEqualOrderByValorDesc(valor));
	}
	
	@GetMapping("/menord/{valor}")
	public ResponseEntity<List<Produto>> getByMenorDesc(@PathVariable BigDecimal valor)
	{
		return ResponseEntity.ok(produtoRepository.findByValorLessThanEqualOrderByValorDesc(valor));
	}

	@PostMapping
	public ResponseEntity<Produto> postProduto(@Valid @RequestBody Produto produto)
	{
		return produtoService.cadastrarProduto(produto).map(resposta -> ResponseEntity.ok(resposta))
				.orElse(ResponseEntity.badRequest().build());
	}

	@PutMapping
	public ResponseEntity<Produto> putProduto(@Valid @RequestBody Produto produto)
	{
		return produtoService.atualizarProduto(produto)
				.map(resposta -> ResponseEntity.status(HttpStatus.OK).body(produtoRepository.save(produto)))
				.orElse(ResponseEntity.badRequest().build());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProduto(@PathVariable Long id)
	{
		if (produtoRepository.existsById(id))
		{
			produtoRepository.deleteById(id);
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

}
