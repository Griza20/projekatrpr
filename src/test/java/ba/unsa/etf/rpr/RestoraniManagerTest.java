package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.RestoraniManager;
import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class RestoraniManagerTest {
    private RestoraniManager rManager = new RestoraniManager();
    @Test
    void testTrazenjaPoId(){
        try {
            assertEquals("Happy",rManager.getById(1).getNaziv());
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void testDodavanjaBezParametara(){
        assertThrows(OrderException.class, () -> {
            rManager.add(new Restorani());
        });
    }

    @Test
    void testDodavanja(){
        Restorani r = new Restorani();
        r.setNaziv("Test");
        r.setVlasnik("Test");
        r.setLokacija("Test");
        boolean dodao=false;
        try {
            rManager.add(r);
            List<Restorani> lista = rManager.getAll();
            for(Restorani x: lista){
                if(x.getNaziv().equals("Test")){
                    dodao=true;
                    rManager.delete(x.getId());
                    break;
                }
            }
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
        assertTrue(dodao);
    }

    @Test
    void traziRestoranPoImenuVlasnika(){
        try {
            assertEquals("Happy", rManager.searchByManagerName("Zerina Osmic").get(0).getNaziv());
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void traziRestoranPoLokaciji(){
        try {
            assertEquals("Kimono",rManager.searchByLocation("SCC").get(0).getNaziv());
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
    }
}
