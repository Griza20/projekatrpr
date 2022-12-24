package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean for restorani
 * @author Amar Grizovic
 */
public class Restorani implements Idable{
    private int idRestorana;
    private String naziv, vlasnik, lokacija;

    @Override
    public int getId() {
        return idRestorana;
    }

    @Override
    public void setId(int id) {
        this.idRestorana = id;
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

    @Override
    public int hashCode() {
        return Objects.hash(idRestorana, naziv, vlasnik, lokacija);
    }

    @Override
    public String toString() {
        return naziv;
    }
}
