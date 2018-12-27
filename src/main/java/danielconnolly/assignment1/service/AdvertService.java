package danielconnolly.assignment1.service;

import danielconnolly.assignment1.domain.Advert;
import danielconnolly.assignment1.domain.AdvertRepository;
import danielconnolly.assignment1.domain.SearchAd;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Daniel Connolly U1457227
 *
 * A service class to perform crud operations and a search method
 */

@Service
public class AdvertService {

    @Autowired
    AdvertRepository advertRepository;

    /**
     * Saves adverts
     * @returns instance of advertRepository to save
     */
    public Advert saveAdverts(Advert advert)
    {

        return advertRepository.save(advert);
    }

    /**
     * Adverts stored into list
     * @return instance of advertRepository to display all adverts
     */
    public List<Advert> findAllAdverts()
    {

        return advertRepository.findAll();
    }

    /**
     * Deletes all adverts
     * @param advert
     */
    public void deleteAllAdverts(Advert advert)
    {
        advertRepository.delete(advert);
    }

    /**
     * Places all adverts into list then accessed by a search query
     * @param ads
     * @return instance of advertRepository to search adverts
     */
    public List<Advert> searchAdverts(SearchAd ads)
    {
        return advertRepository.searchAdverts(ads.getDescription());
    }
}




