package test.test.service;

import java.util.List;

import test.test.model.Linija;
import test.test.model.Prevoznik;

public interface PrevoznikService {

	Prevoznik findOne(Long id);
	
	List<Prevoznik> findAll();
	
    Prevoznik save(Prevoznik prevoznik);
}
