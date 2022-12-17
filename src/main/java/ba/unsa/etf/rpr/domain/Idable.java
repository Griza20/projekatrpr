package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces bean classes to have ID field
 * @author Amar Grizovic
 */
public interface Idable {
    int getId();
    void setId(int id);
}
