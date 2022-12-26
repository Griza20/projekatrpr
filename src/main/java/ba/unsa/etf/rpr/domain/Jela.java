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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jela jela = (Jela) o;
        return idJela == jela.idJela && idRestorana == jela.idRestorana && Objects.equals(jelo, jela.jelo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idJela, idRestorana, jelo);
    }
}
