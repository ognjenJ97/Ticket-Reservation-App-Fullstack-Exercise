package test.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import test.test.model.Linija;
import test.test.model.Prevoznik;
import test.test.repository.LinijaRepository;
import test.test.service.LinijaService;

@Service
public class JpaLinijaService implements LinijaService {

	
	@Autowired
    private LinijaRepository fr;
	
	
	@Override
	public Linija findOne(Long id) {
		return fr.findOneById(id);
	}

	@Override
	public Page<Linija> findAll(int pageNo) {
		return fr.findAll( PageRequest.of(pageNo, 4));
	}

	@Override
	public Linija save(Linija linija) {
		return fr.save(linija);
	}

	@Override
	public Linija update(Linija linija) {
		return fr.save(linija);
	}

	@Override
	public Linija delete(Long id) {
		Linija linija = findOne(id);
		if (linija != null) {
			Prevoznik prevoznik = linija.getPrevoznik();
			prevoznik.obrisiLiniju(linija.getId());
			
			fr.delete(linija);
			return linija;
		}
		return null;
	}

	@Override
	public Page<Linija> find(Long prevoznikId, String destinacija, Integer maksCena, int pageNo) {
		return fr.search(prevoznikId, destinacija, maksCena, PageRequest.of(pageNo, 4));
	}

}
