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
}
