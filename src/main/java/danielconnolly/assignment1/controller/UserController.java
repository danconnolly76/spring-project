package danielconnolly.assignment1.controller;

import danielconnolly.assignment1.domain.LoginUser;
import danielconnolly.assignment1.domain.User;
import danielconnolly.assignment1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
public class UserController {

    private List<User> users;
    private static final String INDEXPAGE = "index";
    private static final String INDEXPAGEREDIRECT = "redirect:/";
    private static final String LOGINPAGEREDIRECT = "redirect:/login";
    private static final String LOGINPAGE = "/login";

    @Autowired
    UserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(Model model, HttpSession httpSession)
    {
        if(httpSession.getAttribute("login")==null)
        {
            return LOGINPAGEREDIRECT;
        }
        users = userService.findUsers();
        model.addAttribute("users", users);

        return INDEXPAGE;
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(Model model){

        User user = new User();
        model.addAttribute("user", user);
        return "/register";

    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(Model model, HttpSession httpSession)
    {
        httpSession.removeAttribute("login");
        return LOGINPAGEREDIRECT;

    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("error", "Enter information into all text boxes");
            return "/register";
        }
        userService.saveUser(user);
        return INDEXPAGEREDIRECT;

    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUser(Model model){

        model.addAttribute("user", new LoginUser());
        return LOGINPAGE;

    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginValdation(Model model, @Valid @ModelAttribute("user") LoginUser user, BindingResult bindingResult,
                                 HttpSession httpSession){

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("error", "Please enter login details");
            return LOGINPAGE;
        }

        if(userService.validateUser(user)==null || userService.validateUser(user).size()==0)
        {
            model.addAttribute("user", user);
            model.addAttribute("error", "Enter in correct username and password");
            return LOGINPAGE;
        }

        httpSession.setAttribute("login", true);
        return INDEXPAGEREDIRECT;
    }

    @RequestMapping(value = "/delete/{user}", method = RequestMethod.GET)
    //@ResponseBody
    public String deleteUser(@PathVariable User user)
    {
        userService.delete(user);

        return INDEXPAGEREDIRECT;
    }

}
