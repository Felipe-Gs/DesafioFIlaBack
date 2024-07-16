package com.example.simpleapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @GetMapping("/test")
    public String test() {
        return "API is up and running!";
    }

    @PostMapping("/add")
    public String addMockData() {
        // Criando dados mockados
        
        User user1 = new User("Felipe Gomes", "contatofelipegomes.dev@gmail.com", "Idosos", false);
        User user2 = new User("Joao", "joao.dev@gmail.com", "PCD", true);

        // User user2 = new User("Maria", "maria@email.com");
        
        // Salvando no banco de dados
        userRepository.save(user1);
        userRepository.save(user2);
        
        return "Dados mockados adicionados com sucesso!";
    }

    @PostMapping("/addDados")
    public String addUserData(@RequestBody User user) {
        // Salvando no banco de dados
        userRepository.save(user);
        
        return "Dados adicionados com sucesso!";
    }

    //voltar apenas os que tem o atendido true

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/atendidos")
    public List<User> getUsuariosAtendidos() {
        String sql = "SELECT u FROM User u WHERE u.atendido = true";
        Query query = entityManager.createQuery(sql, User.class);
        return query.getResultList();
    }


    // fazer funcao para mudar o statuS aqui
    @PutMapping("/MudarAtendido/{id}")
    public ResponseEntity<String> toggleAtendido(@PathVariable Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setAtendido(!user.isAtendido());
            userRepository.save(user);
            return ResponseEntity.ok("Status de atendimento atualizado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }
    }

}
