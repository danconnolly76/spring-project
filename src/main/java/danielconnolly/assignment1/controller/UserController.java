package danielconnolly.assignment1.controller;

import danielconnolly.assignment1.domain.User;
import danielconnolly.assignment1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(Model model){

        User user = new User();
        model.addAttribute("user", user);
        return "user/register";

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    //@ResponseBody
    public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("error", "Enter information into all text boxes");
            return "user/register";
        }
        userService.saveUser(user);
        return "redirect:/";

    }


}
