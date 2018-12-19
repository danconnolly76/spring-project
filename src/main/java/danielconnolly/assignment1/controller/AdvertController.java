package danielconnolly.assignment1.controller;


import com.sun.org.apache.regexp.internal.RE;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class AdvertController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    @ResponseBody
    public String index()
    {
        return "index";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String createAdvert()
    {
        return "create";
    }
}
