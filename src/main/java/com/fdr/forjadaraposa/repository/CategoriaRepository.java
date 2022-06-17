package com.fdr.forjadaraposa.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdr.forjadaraposa.model.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long>
{

	public List<Categoria> findAllByPrinCategoriaContainingIgnoreCase(@Param("prinCategoria") String prinCategoria);
	
	public List<Categoria> findAllBySubCategoriaContainingIgnoreCase(@Param("subCategoria") String subCategoria);
	
	public Optional<Categoria> findBySubCategoria(String subCategoria);
	
}
