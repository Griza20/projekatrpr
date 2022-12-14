package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean for narudzbe
 * @author Amar Grizovic
 */
public class Narudzbe {
    private int idNarudzbe, idRestorana, idDostavljaca;
    private String narudzba, vrijemeNarucivanja;

    public int getIdNarudzbe() {
        return idNarudzbe;
    }

    public void setIdNarudzbe(int idNarudzbe) {
        this.idNarudzbe = idNarudzbe;
    }

    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    public int getIdDostavljaca() {
        return idDostavljaca;
    }

    public void setIdDostavljaca(int idDostavljaca) {
        this.idDostavljaca = idDostavljaca;
    }

    public String getNarudzba() {
        return narudzba;
    }

    public void setNarudzba(String narudzba) {
        this.narudzba = narudzba;
    }
}
