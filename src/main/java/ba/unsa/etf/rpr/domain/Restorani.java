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
}
