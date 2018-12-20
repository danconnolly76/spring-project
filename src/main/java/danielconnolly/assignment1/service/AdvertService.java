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

    public Advert save(Advert advert)
    {

        return advertRepository.save(advert);
    }

    public List<Advert> findAll()
    {

        return advertRepository.findAll();
    }

    public void delete(Advert advert)
    {
        advertRepository.delete(advert);
    }
}