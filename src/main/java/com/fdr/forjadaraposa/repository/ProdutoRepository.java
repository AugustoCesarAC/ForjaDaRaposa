package com.fdr.forjadaraposa.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdr.forjadaraposa.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>
{

	public List<Produto> findAllByNomeContainingIgnoreCase(@Param("nome") String nome);

	// public List<Produto> findByValorOrderByValorDesc(BigDecimal valor);

	// Ordem crescente
	public List<Produto> findByValorGreaterThanEqualOrderByValor(BigDecimal valor);

	public List<Produto> findByValorLessThanEqualOrderByValor(BigDecimal valor);

	public List<Produto> findByCurtirGreaterThanEqualOrderByCurtir(int curtir);
	
	// Ordem decrescente
	public List<Produto> findByValorLessThanEqualOrderByValorDesc(BigDecimal valor);
	
	public List<Produto> findByValorGreaterThanEqualOrderByValorDesc(BigDecimal valor);

	
	//findByNomeOrderByDataValidade

}
