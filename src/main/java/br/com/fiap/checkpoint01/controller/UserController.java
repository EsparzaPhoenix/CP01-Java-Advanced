package br.com.fiap.checkpoint01.controller;

import br.com.fiap.checkpoint01.dto.user.CadastroUserDto;
import br.com.fiap.checkpoint01.dto.user.DetalhesUserDto;
import br.com.fiap.checkpoint01.dto.user.LoginUserDto;
import br.com.fiap.checkpoint01.model.user.User;
import br.com.fiap.checkpoint01.repository.UserRepository;
import br.com.fiap.checkpoint01.service.AuthenticationService;
import br.com.fiap.checkpoint01.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("auth")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationService authenticationService;

    @Autowired
    private TokenService tokenService;

    @PostMapping("register")
    @Transactional
    public ResponseEntity<DetalhesUserDto> register(@RequestBody @Valid CadastroUserDto dto,
                                                    UriComponentsBuilder builder) {
        var user = new User(dto.username(), passwordEncoder.encode(dto.password()), dto.email());
        userRepository.save(user);
        var url = builder.path("auth/{id}").buildAndExpand(user.getId()).toUri();
        return ResponseEntity.created(url).body(new DetalhesUserDto(user));
    }

//    @PostMapping("/login")
//    public ResponseEntity<?> login(@RequestBody @Valid LoginUserDto dto) {
//        try {
//            String token = authenticationService.authenticate(dto.login(), dto.password());
//            return ResponseEntity.ok(token); // Retorne o token diretamente
//        } catch (RuntimeException e) {
//            return ResponseEntity.status(401).body("Unauthorized");
//        }
//    }

}