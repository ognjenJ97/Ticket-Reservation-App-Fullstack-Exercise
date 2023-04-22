package test.test.support;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Prevoznik;
import test.test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikToPrevoznikDto implements Converter<Prevoznik, PrevoznikDTO>{

	@Override
	public PrevoznikDTO convert(Prevoznik p) {
		PrevoznikDTO dto = new PrevoznikDTO();
		
		dto.setId(p.getId());
		dto.setNaziv(p.getNaziv());
		dto.setAdresa(p.getAdresa());
		dto.setPib(p.getPib());
		
		return dto;
	}

	
	public List<PrevoznikDTO> convert(List<Prevoznik> prevoznici){
        List<PrevoznikDTO> dto = new ArrayList<>();

        for(Prevoznik m : prevoznici) {
            dto.add(convert(m));
        }

        return dto;
    }
	
	
	
	
}