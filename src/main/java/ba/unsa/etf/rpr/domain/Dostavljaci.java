package ba.unsa.etf.rpr.domain;


import java.sql.Date;
import java.util.Objects;

/**
 * Bean for dostavljaci
 * @author Amar Grizovic
 */
public class Dostavljaci implements Idable{
    private int idDostavljaca;
    private String ime, prezime, broj, spol;
    private Date datumRodjenja;
    private int visina;
    private boolean vozacka;

    @Override
    public int getId() {
        return idDostavljaca;
    }

    @Override
    public void setId(int id) {
        this.idDostavljaca = id;
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

    public void setVisina(int visina){
        this.visina = visina;
    }

    public int getVisina(){
        return this.visina;
    }

    public void setSpol(String x){
        spol = x;
    }

    public String getSpol(){
        return spol;
    }

    public void setVozacka(boolean x){
        vozacka = x;
    }

    public boolean getVozacka(){
        return vozacka;
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

    @Override
    public String toString() {
        return ime + " " + prezime;
    }
}
