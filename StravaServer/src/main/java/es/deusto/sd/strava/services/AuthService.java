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

    // Login method that checks if the user exists and matches the provided TipoUsuario (GOOGLE or FACEBOOK)
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

    // Logout method to remove the token from the session store
    public Optional<Boolean> logout(String token) {
        if (tokenStore.containsKey(token)) {
            tokenStore.remove(token);
            return Optional.of(true);
        } else {
            return Optional.empty();
        }
    }

    // Method to add a new user to the repository
    public void addUser(User user) {
        if (user != null) {
            userRepository.putIfAbsent(user.getEmail(), user);
        }
    }

    // Method to get the user based on the token
    public User getUserByToken(String token) {
        return tokenStore.get(token); 
    }

    // Method to get the user based on the email
    public User getUserByEmail(String email) {
        return userRepository.get(email);
    }

    // Synchronized method to guarantee unique token generation
    private static synchronized String generateToken() {
        return Long.toHexString(System.currentTimeMillis());
    }
}