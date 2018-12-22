package danielconnolly.assignment1.controller;

import danielconnolly.assignment1.domain.User;
import danielconnolly.assignment1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model)
    {

        return "index";
    }


    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(Model model){

        User user = new User();
        model.addAttribute("user", user);
        return "register";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //@ResponseBody
    public String register(Model model, @ModelAttribute("user") User user){

        userService.saveUser(user);
        return "redirect:/";

    }


}
