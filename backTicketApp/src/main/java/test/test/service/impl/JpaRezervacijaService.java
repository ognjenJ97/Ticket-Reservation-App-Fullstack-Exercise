package test.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import test.test.model.Rezervacija;
import test.test.repository.RezervacijaRepository;
import test.test.service.RezervacijaService;

@Service
public class JpaRezervacijaService implements RezervacijaService{

	@Autowired
    private RezervacijaRepository rr;
	
	@Override
	public Rezervacija findOne(Long id) {
		return rr.findOneById(id);
	}

	@Override
	public List<Rezervacija> findAll() {
		return rr.findAll();
	}

	@Override
	public Rezervacija update(Rezervacija rezervacija) {
		return rr.save(rezervacija);
	}

	@Override
	public Rezervacija save(Rezervacija rezervacija) {
		return rr.save(rezervacija);
	}

}
