package test.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Linija;
import test.test.web.dto.LinijaDTO;

@Component
public class LinijaToLinijaDto implements Converter<Linija, LinijaDTO> {

	@Override
	public LinijaDTO convert(Linija l) {
		LinijaDTO dto = new LinijaDTO();
		   
		   dto.setId(l.getId());
		   dto.setBrojMesta(l.getBrojMesta());
		   dto.setCenaKarte(l.getCenaKarte());
		   dto.setVremePolaska(l.getVremePolaska().toString().replace("T", " "));
		   dto.setDestinacija(l.getDestinacija());
		   dto.setPrevoznikId(l.getPrevoznik().getId());
		   dto.setPrevoznikNaziv(l.getPrevoznik().getNaziv());

	        return dto;
	    }

	    public List<LinijaDTO> convert(List<Linija> linije){
	        List<LinijaDTO> dto = new ArrayList<>();

	        for(Linija linija : linije) {
	            dto.add(convert(linija));
	        }

	        return dto;
	    }
	}