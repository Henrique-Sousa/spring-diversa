package br.com.henriquesousa.diversa21.service;

import java.sql.Timestamp;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.henriquesousa.diversa21.entity.User;
import br.com.henriquesousa.diversa21.repository.UserRepository;
import br.com.henriquesousa.diversa21.service.exception.UserNotCreatedException;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository = null;

    public void create(User newUser) throws UserNotCreatedException {
        if (userRepository.findByUsername(newUser.getUsername()).isEmpty()) {
            LOGGER.debug("user " + newUser.getUsername() + " is not on database");
            // TODO: testar se tem name
            // TODO: colocar esse teste no controller com @Valid
            if (newUser.getUsername() != null) {
                newUser.setUid(UUID.randomUUID());
                newUser.setCreatedAt(new Timestamp(System.currentTimeMillis()));
                userRepository.saveAndFlush(newUser);
                return;
            }
            // TODO: no momento, se o json nao tiver os campos corretos,
            // retorna empty o que faz com que o controller retorne CONFLICT
            // mas sera que eh o melhor status code pra isso?
            LOGGER.debug("user field is empty");
            throw new UserNotCreatedException();
        }
        LOGGER.debug("user " + newUser.getUsername() + "already exists");
        throw new UserNotCreatedException();
    }
}
