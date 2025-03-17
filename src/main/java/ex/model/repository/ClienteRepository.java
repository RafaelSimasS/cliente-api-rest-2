package ex.model.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ex.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
	 List<Cliente> findByNomeContainingIgnoreCase(String nome);
	 
	 Page<Cliente> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}
