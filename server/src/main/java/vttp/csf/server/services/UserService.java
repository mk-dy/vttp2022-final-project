package vttp.csf.server.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import vttp.csf.server.exceptions.UserException;
import vttp.csf.server.models.User;
import vttp.csf.server.repository.UserRepository;

@Service
public class UserService {
    
    // @Autowired
    // private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepo;

    public Optional<User> getUserByEmail(String email) {
        return userRepo.getUserByEmail(email);
    }

    public void createUser(User user) throws UserException {

        Optional<User> optUser = userRepo.getUserByEmail(user.getEmail());
        
        if (optUser.isPresent()) {
            throw new UserException("There is already a user registered with the email: %s.".formatted(user.getEmail()));
        }

        // encrypt password here?
        // String password = user.getPassword();
        // user.setPassword(passwordEncoder.encode(password));

        if (!userRepo.createUser(user)) {
            throw new UserException("Unable to create user.");
        }

    }


}
