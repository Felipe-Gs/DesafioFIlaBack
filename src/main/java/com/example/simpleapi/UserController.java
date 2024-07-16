package com.example.simpleapi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
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

}
