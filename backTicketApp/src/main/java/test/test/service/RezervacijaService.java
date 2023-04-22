package test.test.service;

import java.util.List;

import test.test.model.Rezervacija;

public interface RezervacijaService {

	Rezervacija findOne(Long id);
	
	List<Rezervacija> findAll();
	
	Rezervacija update(Rezervacija rezervacija);

	Rezervacija save(Rezervacija rezervacija);
	
	
}
