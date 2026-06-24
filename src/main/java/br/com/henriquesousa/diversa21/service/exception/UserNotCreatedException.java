package br.com.henriquesousa.diversa21.service.exception;

public class UserNotCreatedException extends Exception {
    public UserNotCreatedException() {
        super("user-not-created");
    }
}
