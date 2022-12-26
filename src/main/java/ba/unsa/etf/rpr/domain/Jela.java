package ba.unsa.etf.rpr.domain;

import java.util.Objects;

public class Jela {
    private int idJela, idRestorana, cijena;
    private String jelo;

    public int getCijena() {
        return cijena;
    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
    }

    public int getIdJela() {
        return idJela;
    }


    public void setIdJela(int idJela) {
        this.idJela = idJela;
    }

    public int getIdRestorana() {
        return idRestorana;
    }

    public void setIdRestorana(int idRestorana) {
        this.idRestorana = idRestorana;
    }

    public String getJelo() {
        return jelo;
    }

    public void setJelo(String jelo) {
        this.jelo = jelo;
    }
}
