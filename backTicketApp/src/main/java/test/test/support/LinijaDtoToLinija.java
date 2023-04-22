package test.test.support;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import test.test.model.Linija;
import test.test.service.LinijaService;
import test.test.service.PrevoznikService;
import test.test.web.dto.LinijaDTO;

@Component
public class LinijaDtoToLinija implements Converter<LinijaDTO, Linija>{

	@Autowired
	private LinijaService linijaService;
	
	@Autowired
	private PrevoznikService prevoznikService;
	
	
	@Override
	public Linija convert(LinijaDTO dto) {
		Linija l;
		
		if(dto.getId() == null){
			l = new Linija();
        }else{
            l = linijaService.findOne(dto.getId());
        }
		
		if (l != null) {
			l.setId(dto.getId());
			l.setBrojMesta(dto.getBrojMesta());
			l.setCenaKarte(dto.getCenaKarte());
			l.setVremePolaska(getLocalDateTime(dto.getVremePolaska()));		
			l.setDestinacija(dto.getDestinacija());		
			l.setPrevoznik(prevoznikService.findOne(dto.getPrevoznikId()));		
		}
		
		return l;
	}
	
	private LocalDateTime getLocalDateTime(String dateTime) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

}