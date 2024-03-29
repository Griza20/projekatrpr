package ba.unsa.etf.rpr.domain;

import java.util.Objects;

/**
 * Bean for narudzbe
 * @author Amar Grizovic
 */
public class Narudzbe implements Idable{
    private int idNarudzbe, idRestorana, idDostavljaca;
    private String narudzba, vrijemeNarucivanja;

    @Override
    public int getId() {
        return idNarudzbe;
    }

    @Override
    public void setId(int id) {
        this.idNarudzbe = id;
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

    public String getVrijemeNarucivanja() {
        return vrijemeNarucivanja;
    }

    public void setVrijemeNarucivanja(String vrijemeNarucivanja) {
        this.vrijemeNarucivanja = vrijemeNarucivanja;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Narudzbe narudzbe = (Narudzbe) o;
        return idNarudzbe == narudzbe.idNarudzbe;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idNarudzbe, idRestorana, idDostavljaca, narudzba, vrijemeNarucivanja);
    }

    @Override
    public String toString() {
        return "Narudzbe{" +
                "idNarudzbe=" + idNarudzbe +
                ", idRestorana=" + idRestorana +
                ", idDostavljaca=" + idDostavljaca +
                ", narudzba='" + narudzba + '\'' +
                ", vrijemeNarucivanja='" + vrijemeNarucivanja + '\'' +
                '}';
    }
}
