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

/**
 * Created by Daniel Connolly U1457227
 *
 * This is a controller class so user can register, login and log out
 */

@Controller
public class UserController {

    private List<User> users;
    private static final String INDEXPAGE = "index";
    private static final String INDEXPAGEREDIRECT = "redirect:/";
    private static final String LOGINPAGEREDIRECT = "redirect:/login";
    private static final String LOGINPAGE = "/login";
    private static final String REGISTER = "/register";

    @Autowired
    UserService userService;

    /**
     * This method access the home page
     * @param model
     * @param httpSession
     * @return login page and index page
     */
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

    /**
     * This method creates a user
     * @param model
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerUser(Model model){
        model.addAttribute("user",new User());
        return REGISTER;
    }

    /**
     * This method logs out a user
     * @param model
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutUser(Model model, HttpSession httpSession)
    {
        httpSession.removeAttribute("login");
        return LOGINPAGEREDIRECT;

    }

    /**
     * This method allows for a user to register if any errors message is displayed
     * @param model
     * @param user
     * @param bindingResult
     * @return register page index page
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(Model model, @Valid @ModelAttribute("user") User user, BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("error", "Enter information into all text boxes");
            return REGISTER;
        }
        userService.saveUser(user);
        return INDEXPAGEREDIRECT;
    }

    /**
     * Creates an instance of the loginUser class
     * @param model
     * @return login page
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginUser(Model model){

        model.addAttribute("user", new LoginUser());
        return LOGINPAGE;

    }

    /**
     * This method checks to see if errors have been entered if some have return to login page
     * if login true returns login page else displays error message
     * @param model
     * @param user
     * @param bindingResult
     * @param httpSession
     * @return login page and index page
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginValdation(Model model, @Valid @ModelAttribute("user") LoginUser user, BindingResult bindingResult,
                                 HttpSession httpSession){

        if(bindingResult.hasErrors()){
            model.addAttribute("user", user);
            model.addAttribute("error", "Please enter login details");
            return LOGINPAGE;
        }

        if(userService.validateUser(user)==true)
        {
            httpSession.setAttribute("login", true);
            return INDEXPAGEREDIRECT;

        } else {
            model.addAttribute("user", user);
            model.addAttribute("error", "Enter in correct username and password");
        }
        return LOGINPAGE;
    }

}
