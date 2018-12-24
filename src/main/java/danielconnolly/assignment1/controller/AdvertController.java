package danielconnolly.assignment1.controller;

import danielconnolly.assignment1.domain.Advert;
import danielconnolly.assignment1.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping(value = "/advert")
public class AdvertController {

    private static final String READPAGE = "advert/read";
    private static final String REDIRECTREADPAGE = "redirect:/advert/read";
    private static final String CREATEPAGE = "advert/create";
    private static final String UPDATEPAGE = "advert/update";
    private static final String LOGINPAGEREDIRECT = "redirect:/login";
    private List<Advert> adverts;

    @Autowired
    AdvertService advertService;


    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String viewAdvert(Model model, HttpSession httpSession)
    {
        if(httpSession.getAttribute("login")==null)
        {
            return LOGINPAGEREDIRECT;
        }
        adverts = advertService.findAllAdverts();

        model.addAttribute("adverts", adverts);
        return READPAGE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String viewCreate(Model model, HttpSession httpSession)
    {
        if(httpSession.getAttribute("login")==null)
        {
            return LOGINPAGEREDIRECT;
        }
        model.addAttribute("advert",new Advert());
        return CREATEPAGE;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("advert") Advert advert,
                         BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("advert", advert);
            model.addAttribute("message", "Must enter text in all text boxes");
            return CREATEPAGE;
        }
            this.advertService.saveAdverts(advert);
            return REDIRECTREADPAGE;

    }

    @RequestMapping(value = "/update/{advert}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Advert advert)
    {

        model.addAttribute("advert", advert);
        return UPDATEPAGE;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateSave(@ModelAttribute("advert") Advert advert)
    {
        this.advertService.saveAdverts(advert);
        return REDIRECTREADPAGE;
    }

    @RequestMapping(value = "/delete/{advert}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Advert advert)
    {
        this.advertService.deleteAllAdverts(advert);
        return REDIRECTREADPAGE;
    }





}
