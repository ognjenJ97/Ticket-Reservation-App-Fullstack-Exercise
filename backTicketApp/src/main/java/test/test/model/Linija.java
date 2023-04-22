package test.test.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Linija {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private Long id;
	 
	 @NotNull
	 private int brojMesta;
	 
	 private double cenaKarte;
	  
	 private LocalDateTime vremePolaska;
	 
	 private String destinacija;
	 
	 @ManyToOne
	 private Prevoznik prevoznik;
	 
	 @OneToMany(mappedBy = "linija", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	 private List<Rezervacija> rezervacije = new ArrayList<>();

	public Linija() {
		super();
	}
	
	public void obrisiRezervaciju(Long id) {
		for(Rezervacija r : this.rezervacije) {
			if (r.getId()==id) {
				this.rezervacije.remove(r);
				return;
			}
		}
	}
	
	public void dodajRezervaciju(Rezervacija r) {
		this.rezervacije.add(r);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getBrojMesta() {
		return brojMesta;
	}

	public void setBrojMesta(int brojMesta) {
		this.brojMesta = brojMesta;
	}

	public double getCenaKarte() {
		return cenaKarte;
	}

	public void setCenaKarte(double cenaKarte) {
		this.cenaKarte = cenaKarte;
	}

	public LocalDateTime getVremePolaska() {
		return vremePolaska;
	}

	public void setVremePolaska(LocalDateTime vremePolaska) {
		this.vremePolaska = vremePolaska;
	}

	public String getDestinacija() {
		return destinacija;
	}

	public void setDestinacija(String destinacija) {
		this.destinacija = destinacija;
	}

	public Prevoznik getPrevoznik() {
		return prevoznik;
	}

	public void setPrevoznik(Prevoznik prevoznik) {
		this.prevoznik = prevoznik;
	}

	public List<Rezervacija> getRezervacije() {
		return rezervacije;
	}

	public void setRezervacije(List<Rezervacija> rezervacije) {
		this.rezervacije = rezervacije;
	}

	@Override
	public String toString() {
		return "Linija [id=" + id + ", brojMesta=" + brojMesta + ", cenaKarte=" + cenaKarte + ", vremePolaska="
				+ vremePolaska + ", destinacija=" + destinacija + ", prevoznik=" + prevoznik.getNaziv() + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Linija other = (Linija) obj;
		return Objects.equals(id, other.id);
	}
	 
	 
}
