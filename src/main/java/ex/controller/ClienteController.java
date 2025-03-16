package ex.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
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
	public List<ClienteFormRequest> getLista(){
		return repository.findAll()
				.stream()
				.map(ClienteFormRequest::fromModel)
				.collect(Collectors.toList());
		
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
}
