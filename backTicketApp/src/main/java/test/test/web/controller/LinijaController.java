package test.test.web.controller;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import test.test.model.Linija;
import test.test.service.LinijaService;
import test.test.support.LinijaDtoToLinija;
import test.test.support.LinijaToLinijaDto;
import test.test.web.dto.LinijaDTO;


@RestController
@RequestMapping(value = "/api/linije", produces = MediaType.APPLICATION_JSON_VALUE)
public class LinijaController {

	
	@Autowired
    private LinijaService linijaService;

    @Autowired
    private LinijaDtoToLinija toLinija;

    @Autowired
    private LinijaToLinijaDto toLinijaDto;
    
//  @PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<LinijaDTO> getOne(@PathVariable Long id){
      Linija linija = linijaService.findOne(id);

      if(linija != null) {
          return new ResponseEntity<>(toLinijaDto.convert(linija), HttpStatus.OK);
      }else {
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
  }    
	
	
//@PreAuthorize("hasAnyRole('KORISNIK', 'ADMIN')")
@GetMapping
public ResponseEntity<List<LinijaDTO>> getAll(
		@RequestParam(required=false) Long prevoznikId,
		@RequestParam(required=false) String destinacija,
		@RequestParam(required=false) Integer maksCena,
        @RequestParam(value = "pageNo", defaultValue = "0") int pageNo){

    Page<Linija> page;

    try{
		 page = linijaService.find(prevoznikId, destinacija, maksCena, pageNo);
    }catch (Exception e){
   	 page = linijaService.findAll(pageNo);
    }
	    HttpHeaders headers = new HttpHeaders();
    headers.add("Total-Pages", Integer.toString(page.getTotalPages()));

   return new ResponseEntity<>(toLinijaDto.convert(page.getContent()), headers, HttpStatus.OK);
}	

//@PreAuthorize("hasRole('ADMIN')")
@DeleteMapping("/{id}")
public ResponseEntity<Void> delete(@PathVariable Long id){
  Linija linija = linijaService.delete(id);

  if(linija != null) {
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
  } else {
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
  }
}

//@PreAuthorize("hasRole('ADMIN')")
@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<LinijaDTO> create(@Valid @RequestBody LinijaDTO linijaDTO){
  Linija linija = toLinija.convert(linijaDTO);
  Linija sacuvaj = linijaService.save(linija);

  return new ResponseEntity<>(toLinijaDto.convert(sacuvaj), HttpStatus.CREATED);
}
	
//@PreAuthorize("hasRole('ADMIN')")
@PutMapping(value= "/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
public ResponseEntity<LinijaDTO> update(@PathVariable Long id, @Valid @RequestBody LinijaDTO linijaDTO){

  if(!id.equals(linijaDTO.getId())) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
  }

  Linija linija = toLinija.convert(linijaDTO);
  Linija sacuvaj = linijaService.update(linija);

  return new ResponseEntity<>(toLinijaDto.convert(sacuvaj),HttpStatus.OK);
}	
	
	
	
	
	
	
	
	
}
