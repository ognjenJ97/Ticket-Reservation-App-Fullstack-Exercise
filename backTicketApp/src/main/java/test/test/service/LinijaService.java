package test.test.service;

import org.springframework.data.domain.Page;

import test.test.model.Linija;

public interface LinijaService {

	Linija findOne(Long id);

    Page<Linija> findAll(int pageNo);

    Linija save(Linija linija);

    Linija update(Linija linija);

    Linija delete(Long id);
    
    Page<Linija> find(Long prevoznikId, String destinacija, Integer maksCena, int pageNo);
}
