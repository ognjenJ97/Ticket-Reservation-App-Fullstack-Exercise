package test.test.repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import test.test.model.Linija;

@Repository
public interface LinijaRepository extends JpaRepository<Linija,Long> {

	Linija findOneById(Long id);
	
	@Query("SELECT f FROM Linija f WHERE " +
	        "(:destinacija IS NULL OR f.destinacija LIKE %:destinacija%) AND " +
	        "(f.cenaKarte < COALESCE(:maksCena, f.cenaKarte+1)) AND " +
	        "(:prevoznikId IS NULL OR f.prevoznik.id = :prevoznikId)")
	Page<Linija> search(@Param("prevoznikId") Long prevoznikId, @Param("destinacija") String destinacija, @Param("maksCena") Integer maksCena, Pageable pageable);

}
