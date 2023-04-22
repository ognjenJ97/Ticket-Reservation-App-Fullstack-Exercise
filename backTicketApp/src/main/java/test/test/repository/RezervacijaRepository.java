package test.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import test.test.model.Rezervacija;

@Repository
public interface RezervacijaRepository extends JpaRepository<Rezervacija,Long> {

	Rezervacija findOneById(Long id);

}
