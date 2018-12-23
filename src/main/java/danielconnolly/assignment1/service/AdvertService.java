package danielconnolly.assignment1.service;

import danielconnolly.assignment1.domain.Advert;
import danielconnolly.assignment1.domain.AdvertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdvertService {

    @Autowired
    AdvertRepository advertRepository;

    public Advert saveAdverts(Advert advert)
    {

        return advertRepository.save(advert);
    }

    public List<Advert> findAllAdverts()
    {

        return advertRepository.findAll();
    }

    public void deleteAllAdverts(Advert advert)
    {
        advertRepository.delete(advert);
    }
}
