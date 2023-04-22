package test.test.web.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import test.test.model.Linija;
import test.test.model.Prevoznik;
import test.test.model.Rezervacija;
import test.test.service.LinijaService;
import test.test.service.RezervacijaService;
import test.test.support.RezervacijaDtoToRezervacija;
import test.test.support.RezervacijaToRezervacijaDto;
import test.test.web.dto.PrevoznikDTO;
import test.test.web.dto.RezervacijaDTO;

@RestController
@RequestMapping(value = "/api/rezervacije", produces = MediaType.APPLICATION_JSON_VALUE)
public class RezervacijaController {

	@Autowired
    private RezervacijaService rezervacijaService;
	
	@Autowired
    private LinijaService linijaService;

    @Autowired
    private RezervacijaDtoToRezervacija toRezervacija;
    
    @Autowired
    private RezervacijaToRezervacijaDto toRezervacijaDto;
    
    
//  @PreAuthorize("hasRole('ADMIN')")
  @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<RezervacijaDTO> create(@Valid @RequestBody RezervacijaDTO rezervacijaDTO){
	  int brojKarataRez = rezervacijaDTO.getBrojKarata();
	  Linija linija = linijaService.findOne(rezervacijaDTO.getLinijaId());
	  int brojMesta = linija.getBrojMesta();

	  
      Rezervacija rezervacija = toRezervacija.convert(rezervacijaDTO);
      
	  linija.setBrojMesta(brojMesta - brojKarataRez);
	  linijaService.update(linija);
	  
      Rezervacija sacuvaj = rezervacijaService.save(rezervacija);

      return new ResponseEntity<>(toRezervacijaDto.convert(sacuvaj), HttpStatus.CREATED);
  }   
  
//@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
@GetMapping
public ResponseEntity<List<RezervacijaDTO>> getAll(){

    List<Rezervacija> rezervacije = rezervacijaService.findAll();

    return new ResponseEntity<>(toRezervacijaDto.convert(rezervacije), HttpStatus.OK);
}

	
	
}
