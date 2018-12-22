package danielconnolly.assignment1.service;

import danielconnolly.assignment1.domain.User;
import danielconnolly.assignment1.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }
}
