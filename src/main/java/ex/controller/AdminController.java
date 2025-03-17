package ex.controller;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ex.model.Admin;
import ex.model.repository.AdminRepository;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin
public class AdminController {

    @Autowired
    private AdminRepository adminRepository;

    @PostMapping("/autenticacao")
    public ResponseEntity<?> login(@RequestBody AdminFormRequest loginRequest) {
        Admin admin = adminRepository.findByUsername(loginRequest.getUsername());
        if (admin == null) {
        	return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Admin Não Encontrado.");
        }
        
        
        if (!admin.getSenha().equals(loginRequest.getSenha())) {
        	 return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                     .body("Senha incorreta.");
        }
        
        if (admin.getAuthToken() == null) {
            String novoToken = UUID.randomUUID().toString();
            admin.setAuthToken(novoToken);
            adminRepository.save(admin);
        }
        
		return ResponseEntity.ok(AdminFormRequest.fromModel(admin));
    }
}
