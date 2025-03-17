package ex.controller;

import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ex.model.Cliente;
import ex.model.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
@CrossOrigin
public class ClienteController {
	
	@Autowired
	private ClienteRepository repository;
	
	@PostMapping
	public ResponseEntity<ClienteFormRequest> salvar(@RequestBody ClienteFormRequest request){
		Cliente cliente = request.toModel();
		
		repository.save(cliente);
		
		System.out.println(cliente);
		
		return ResponseEntity.ok(ClienteFormRequest.fromModel(cliente));
	}
	
	@GetMapping
	public ResponseEntity<Page<ClienteFormRequest>> getLista(
	        @RequestParam(value="page", defaultValue="0") int page) {
	    PageRequest pageable = PageRequest.of(page, 4);
	    Page<ClienteFormRequest> clientesPage = repository.findAll(pageable)
	            .map(ClienteFormRequest::fromModel);
	    return ResponseEntity.ok(clientesPage);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ClienteFormRequest> getClienteById(@PathVariable Long id) {
		Optional<Cliente> clienteOptional = repository.findById(id);
		if (clienteOptional.isPresent()) {
			return ResponseEntity.ok(ClienteFormRequest.fromModel(clienteOptional.get()));
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ClienteFormRequest> updateCliente(@PathVariable Long id, @RequestBody ClienteFormRequest request) {
		Optional<Cliente> clienteOptional = repository.findById(id);
		if (!clienteOptional.isPresent()){
			return ResponseEntity.notFound().build();
		}
		Cliente cliente = clienteOptional.get();
		cliente.setNome(request.getNome());
		cliente.setNascimento(request.getNascimento());
		cliente.setCpf(request.getCpf());
		cliente.setEndereco(request.getEndereco());
		cliente.setTelefone(request.getTelefone());
		cliente.setEmail(request.getEmail());
		
		repository.save(cliente);
		return ResponseEntity.ok(ClienteFormRequest.fromModel(cliente));
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCliente(@PathVariable Long id) {
	    if (!repository.existsById(id)) {
	        return ResponseEntity.notFound().build();
	    }
	    repository.deleteById(id);
	    return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/search")
	public ResponseEntity<Page<ClienteFormRequest>> searchClientes(
            @RequestParam("nome") String nome,
            @RequestParam(value="page", defaultValue="0") int page) {
	    PageRequest pageable = PageRequest.of(page, 4);
	    Page<ClienteFormRequest> clientesPage = repository.findByNomeContainingIgnoreCase(nome, pageable)
	            .map(ClienteFormRequest::fromModel);
	    return ResponseEntity.ok(clientesPage);
    }
}
