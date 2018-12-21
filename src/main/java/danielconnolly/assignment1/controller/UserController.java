package danielconnolly.assignment1.controller;

import danielconnolly.assignment1.domain.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user")
public class UserController {


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(Model model){

        User user = new User();
        model.addAttribute("user", user);
        return "register";

    }
}
