package ro.danielstn.stockalarms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.danielstn.stockalarms.datasource.User;
import ro.danielstn.stockalarms.datasource.UserRepository;

import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder encoder;

    public User createUser(User newUser) {
        if (userRepository.findByName(newUser.getName()).isPresent()) {
            // same username exists
            return null;
        }
        newUser.setPassword(encoder.encode(newUser.getPassword()));
        return userRepository.save(newUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByName(username);
        if (user.isPresent()) {
            return new UserPrincipal(user.get());

        }
        throw new UsernameNotFoundException(username);

    }

    public User getUserByUsername(String name) {
        return userRepository.findByName(name).get();
    }
}
