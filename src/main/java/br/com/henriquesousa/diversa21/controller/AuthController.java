package br.com.henriquesousa.diversa21.controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.henriquesousa.diversa21.dto.UserLoginDto;
import br.com.henriquesousa.diversa21.dto.UserRegisterDto;
import br.com.henriquesousa.diversa21.entity.City;
import br.com.henriquesousa.diversa21.entity.Institution;
import br.com.henriquesousa.diversa21.entity.Role;
import br.com.henriquesousa.diversa21.entity.User;
import br.com.henriquesousa.diversa21.repository.CityRepository;
import br.com.henriquesousa.diversa21.repository.InstitutionRepository;
import br.com.henriquesousa.diversa21.repository.RoleRepository;
import br.com.henriquesousa.diversa21.security.JWTUtil;
import br.com.henriquesousa.diversa21.service.UserService;
import br.com.henriquesousa.diversa21.service.exception.UserNotCreatedException;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private InstitutionRepository institutionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public Map<String, Object> register(@Valid @RequestBody UserRegisterDto userRegisterDto) {
        String encodedPass = passwordEncoder.encode(userRegisterDto.getPassword());
        userRegisterDto.setPassword(encodedPass);
        User user = new User();
 
        // TODO: verificar se a city e a institution existem
        UUID cityUid = userRegisterDto.getCity();
        LOGGER.debug("city uid: {}", cityUid);
        City city = cityRepository.findByUid(cityUid).get();
        Institution institution = institutionRepository.findByUid(userRegisterDto.getInstitution()).get();
        user.setCity(city);
        user.setInstitution(institution);

        user.setName(userRegisterDto.getName());
        user.setUsername(userRegisterDto.getUsername());
        user.setEmail(userRegisterDto.getEmail());
        user.setPassword(userRegisterDto.getPassword());
        user.setDescription(userRegisterDto.getDescription());
        user.setBirthDate(userRegisterDto.getBirthDate());

        user.setRoles(userRegisterDto.getRoles().stream()
            .map(name -> roleRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Role not found: " + name)))
            .collect(Collectors.toList()));

        try {
            userService.create(user);
            String token = jwtUtil.generateToken(user.getUsername());
            return Collections.singletonMap("jwt-token", token);
        } catch (UserNotCreatedException e) {
            return new HashMap<>();
        }
        // TODO: devo colocar isso aqui?
        // String token = jwtUtil.generateToken(user.getUserName());
        // return Collections.singletonMap("jwt-token", token);

    }

    @PostMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody UserLoginDto userLoginDTO) {
        try {
            UsernamePasswordAuthenticationToken authInputToken = new UsernamePasswordAuthenticationToken(
                    userLoginDTO.getUsername(), userLoginDTO.getPassword());
            authenticationManager.authenticate(authInputToken);

            String token = jwtUtil.generateToken(userLoginDTO.getUsername());
            return Collections.singletonMap("jwt-token", token);
        } catch (AuthenticationException authExc) {
            throw new RuntimeException("Invalid username/password.");
        }
        // TODO: devo colocar isso aqui?
        // String token = jwtUtil.generateToken(userLoginDTO.getUserName());
        // return Collections.singletonMap("jwt-token", token);
    }
}
