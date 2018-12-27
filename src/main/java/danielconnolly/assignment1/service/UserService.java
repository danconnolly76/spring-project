package danielconnolly.assignment1.service;

import danielconnolly.assignment1.domain.LoginUser;
import danielconnolly.assignment1.domain.User;
import danielconnolly.assignment1.domain.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User saveUser(User user){
        return userRepository.save(user);
    }

    public List<User> findUsers(){
        return userRepository.findAll();
    }

    public void delete(User user){
        userRepository.delete(user);
    }

    public boolean validateUser(LoginUser user){

        List<User> u = userRepository.findByFNameAndPassword(user.getUsername(), user.getPassword());
        return u !=null && u.size()>0;
    }



}
