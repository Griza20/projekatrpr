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
}
