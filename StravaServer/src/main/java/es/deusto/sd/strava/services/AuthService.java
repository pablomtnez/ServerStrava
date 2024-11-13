/**
 * This code is based on solutions provided by ChatGPT 4o and 
 * adapted using GitHub Copilot. It has been thoroughly reviewed 
 * and validated to ensure correctness and that it is free of errors.
 */

package es.deusto.sd.strava.services;

import org.springframework.stereotype.Service;
import es.deusto.sd.strava.entity.User;
import es.deusto.sd.strava.entity.TipoUsuario;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class AuthService {

    private static Map<String, User> userRepository = new HashMap<>();
    private static Map<String, User> tokenStore = new HashMap<>();

    public Optional<String> login(String email, TipoUsuario tipoUsuario) {
        User user = userRepository.get(email);

        if (user != null && user.getTipoUsuario() == tipoUsuario) {
            String token = generateToken();
            tokenStore.put(token, user);
            return Optional.of(token);
        } else {
            return Optional.empty();
        }
    }

    public Optional<Boolean> logout(String token) {
        if (tokenStore.containsKey(token)) {
            tokenStore.remove(token);
            return Optional.of(true);
        } else {
            return Optional.empty();
        }
    }

    public void register(User user) {
        if (user == null || user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new IllegalArgumentException("User and email must not be null or empty");
        }

        if (userRepository.containsKey(user.getEmail())) {
            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }

        userRepository.put(user.getEmail(), user);
    }

    public User getUserByToken(String token) {
        return tokenStore.get(token);
    }

    public User getUserByEmail(String email) {
        return userRepository.get(email);
    }

    private static synchronized String generateToken() {
        return Long.toHexString(System.currentTimeMillis()) + "-" + Math.random();
    }

    public boolean isTokenValid(String token) {
        return tokenStore.containsKey(token);
    }
}
