package test.test.web.dto;


public class RezervacijaDTO {

	private Long id;
	
	private int brojKarata;
	 
	private Long linijaId;

	public RezervacijaDTO() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojKarata() {
		return brojKarata;
	}

	public void setBrojKarata(int brojKarata) {
		this.brojKarata = brojKarata;
	}

	public Long getLinijaId() {
		return linijaId;
	}

	public void setLinijaId(Long linijaId) {
		this.linijaId = linijaId;
	}
	
	
	 
}
