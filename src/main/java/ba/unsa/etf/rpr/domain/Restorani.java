package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean for restorani
 * @author Amar Grizovic
 */
public class Restorani {
    private int idRestorana;
    private String naziv, vlasnik, lokacija;

    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(String vlasnik) {
        this.vlasnik = vlasnik;
    }

    public String getLokacija() {
        return lokacija;
    }

    public void setLokacija(String lokacija) {
        this.lokacija = lokacija;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Restorani restorani = (Restorani) o;
        return restorani.idRestorana==idRestorana;
    }
}
