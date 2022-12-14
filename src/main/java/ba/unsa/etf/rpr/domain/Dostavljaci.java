package ba.unsa.etf.rpr.domain;


import java.sql.Date;
import java.util.Objects;

/**
 * Bean for dostavljaci
 * @author Amar Grizovic
 */
public class Dostavljaci {
    private int idDostavljaca;
    private String ime, prezime, broj;
    private Date datumRodjenja;


    public int getIdDostavljaca() {
        return idDostavljaca;
    }

    public void setIdDostavljaca(int idDostavljaca) {
        this.idDostavljaca = idDostavljaca;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getBroj() {
        return broj;
    }

    public void setBroj(String broj) {
        this.broj = broj;
    }

    public Date getDatumRodjenja() {
        return datumRodjenja;
    }

    public void setDatumRodjenja(Date datumRodjenja) {
        this.datumRodjenja = datumRodjenja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dostavljaci dostavljaci = (Dostavljaci) o;
        return dostavljaci.idDostavljaca==idDostavljaca;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idDostavljaca, ime, prezime, broj, datumRodjenja);
    }
}
