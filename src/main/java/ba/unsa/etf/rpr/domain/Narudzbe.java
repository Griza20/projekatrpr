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
}
