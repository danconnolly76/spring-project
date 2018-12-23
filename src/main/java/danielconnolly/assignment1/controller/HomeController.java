package danielconnolly.assignment1.controller;


import danielconnolly.assignment1.domain.User;
import danielconnolly.assignment1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model)
    {
        List<User> users = userService.findAllUsers();
        model.addAttribute("users", users);

        return "index";
    }


}
