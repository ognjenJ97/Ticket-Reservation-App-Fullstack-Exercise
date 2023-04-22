package test.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Rezervacija;
import test.test.service.LinijaService;
import test.test.service.RezervacijaService;
import test.test.web.dto.RezervacijaDTO;

@Component
public class RezervacijaDtoToRezervacija implements Converter<RezervacijaDTO, Rezervacija>{

	@Autowired
	private RezervacijaService rezervacijaService;
	
	@Autowired
	private LinijaService linijaService;
	
	
	@Override
	public Rezervacija convert(RezervacijaDTO dto) {
		Rezervacija r;
		
		if(dto.getId() == null){
			r = new Rezervacija();
        }else{
            r = rezervacijaService.findOne(dto.getId());
        }
		
		if (r != null) {
			r.setId(dto.getId());
			r.setBrojKarata(dto.getBrojKarata());
			r.setLinija(linijaService.findOne(dto.getLinijaId()));	
		}
		
		return r;
	}

}
