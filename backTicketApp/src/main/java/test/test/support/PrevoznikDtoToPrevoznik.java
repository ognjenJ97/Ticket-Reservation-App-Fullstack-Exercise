package test.test.support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Prevoznik;
import test.test.service.PrevoznikService;
import test.test.web.dto.PrevoznikDTO;

@Component
public class PrevoznikDtoToPrevoznik implements Converter<PrevoznikDTO, Prevoznik>{

	@Autowired
	private PrevoznikService prevoznikService;
	
	@Override
	public Prevoznik convert(PrevoznikDTO dto) {
		Prevoznik p;
		
		if(dto.getId() == null){
			p = new Prevoznik();
        }else{
            p = prevoznikService.findOne(dto.getId());
        }
		
		if (p != null) {
			p.setId(dto.getId());
			p.setNaziv(dto.getNaziv());
			p.setAdresa(dto.getAdresa());
			p.setPib(dto.getPib());	
		}
		
		return p;
	}

}
