package danielconnolly.assignment1.controller;

import danielconnolly.assignment1.domain.Advert;
import danielconnolly.assignment1.domain.SearchAd;
import danielconnolly.assignment1.service.AdvertService;
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
 * This is a controller class for adverts contained in the web app
 */

@Controller
@RequestMapping(value = "/advert")
public class AdvertController {

    private static final String READPAGE = "advert/read";
    private static final String REDIRECTREADPAGE = "redirect:/advert/read";
    private static final String CREATEPAGE = "advert/create";
    private static final String UPDATEPAGE = "advert/update";
    private static final String LOGINPAGEREDIRECT = "redirect:/login";
    private static final String SEARCHPAGE = "advert/search";
    private List<Advert> adverts;

    @Autowired
    AdvertService advertService;


    /**
     * This method gets all adverts by calling a method from the advertservice class
     * these are displayed to screen
     * @param model
     * @param httpSession
     * @return read page or login page
     */

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

    /**
     * This method retrieves an advert object to create an advert
     * @param model
     * @param httpSession
     * @return create page or login page
     */

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

    /**
     * This method creates an instance of the SearchAd class
     * to search for an advert via its description
     * @param model
     * @return search page
     */
    @RequestMapping(value ="/search", method = RequestMethod.GET)
    public String searchAd(Model model)
    {
        model.addAttribute("searchAd", new SearchAd());
        return SEARCHPAGE;
    }

    /**
     * Posts results of search query
     * @param model
     * @param searchAd
     * @return search page
     */
    @RequestMapping(value ="/search", method = RequestMethod.POST)
    public String search(Model model, @ModelAttribute("searchAd") SearchAd searchAd)
    {
        adverts = advertService.searchAdverts(searchAd);
        model.addAttribute("searchAd", searchAd);
        model.addAttribute("adverts", adverts);
        return SEARCHPAGE;
    }

    /**
     * If advert contains no errors advert is saved and displayed on screen
     * @param model
     * @param advert
     * @param bindingResult
     * @return create page or read page
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public String create(Model model, @Valid @ModelAttribute("advert") Advert advert,
                         BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            model.addAttribute("advert", advert);
            model.addAttribute("error", "Must enter text in all text boxes");
            return CREATEPAGE;
        }
        this.advertService.saveAdverts(advert);
        return REDIRECTREADPAGE;

    }

    /**
     * Allows for a advert to be updated
     * @param model
     * @param advert
     * @return update page
     */
    @RequestMapping(value = "/update/{advert}", method = RequestMethod.GET)
    public String update(Model model, @PathVariable Advert advert)
    {

        model.addAttribute("advert", advert);
        return UPDATEPAGE;
    }

    /**
     * Saves a advert
     * @param advert
     * @return redirect to read page
     */

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String updateSave(@ModelAttribute("advert") Advert advert)
    {
        this.advertService.saveAdverts(advert);
        return REDIRECTREADPAGE;
    }

    /**
     * Allows for adverts to be deleted
     * @param model
     * @param advert
     * @return redirect read page
     */
    @RequestMapping(value = "/delete/{advert}", method = RequestMethod.GET)
    public String delete(Model model, @PathVariable Advert advert)
    {
        this.advertService.deleteAllAdverts(advert);
        return REDIRECTREADPAGE;
    }


}
