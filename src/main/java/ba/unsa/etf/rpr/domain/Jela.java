package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean class Jela
 * @author Amar Grizovic
 */
public class Jela implements Idable {
    private int idJela, idRestorana, cijena;
    private String jelo;

    public int getCijena() {
        return cijena;
    }

    public void setCijena(int cijena) {
        this.cijena = cijena;
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

    @Override
    public String toString() {
        return "Jela{" +
                "idJela=" + idJela +
                ", idRestorana=" + idRestorana +
                ", jelo='" + jelo + '\'' +
                '}';
    }

    @Override
    public int getId() {
        return idJela;
    }

    @Override
    public void setId(int id) {

    }
}
