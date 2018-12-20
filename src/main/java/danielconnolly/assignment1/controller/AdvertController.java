package danielconnolly.assignment1.controller;

import danielconnolly.assignment1.domain.Advert;
import danielconnolly.assignment1.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "/ad")
public class AdvertController {

    @Autowired
    AdvertService advertService;


    @RequestMapping(value = "/", method = RequestMethod.GET)
    //@ResponseBody
    public String index()
    {

        return "index";
    }

    @RequestMapping(value = "/read", method = RequestMethod.GET)
    public String viewAdvert(Model model)
    {
        List<Advert> adverts = advertService.findAll();

        model.addAttribute("adverts", adverts);
        return "read";
    }

    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public String viewCreate(Model model)
    {
        Advert advert = new Advert();

        model.addAttribute("advert", advert);
        return "create";
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    //@ResponseBody
    public String create(Model model, @ModelAttribute("advert") Advert advert)
    {
        advertService.save(advert);
        return "redirect:/ad/read";
    }

    @RequestMapping(value = "/delete/{advert}", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Advert advert)
    {
        String value = advert.getName();

        advertService.delete(advert);

        return "Hello";
    }



}