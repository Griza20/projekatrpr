package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.JelaManager;
import ba.unsa.etf.rpr.domain.Jela;
import ba.unsa.etf.rpr.exceptions.OrderException;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class JelaManagerTest {
    private JelaManager jManager = new JelaManager();
    @Test
    void testDodavanjaSaId(){
        Jela j = new Jela();
        j.setId(1);
        j.setJelo("Nesto");
        j.setCijena(1.);
        j.setOpis("Nesto");
        j.setIdRestorana(1);
        assertThrows(OrderException.class, () -> {
            jManager.add(j);
        });
    }

    @Test
    void testDodavanjaBezParametara(){
        assertThrows(OrderException.class, () -> {
            jManager.add(new Jela());
        });
    }

    @Test
    void testDodavanja(){
        Jela j = new Jela();
        j.setJelo("Test");
        j.setCijena(1.);
        j.setOpis("Test");
        j.setIdRestorana(1);
        boolean dodao=false;
        try {
            jManager.add(j);
            List<Jela> lista = jManager.getAllMealsFromRestaurant(1);
            for(Jela x: lista){
                if(x.getJelo().equals("Test")){
                    dodao=true;
                    jManager.delete(x.getId());
                    break;
                }
            }
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
        assertTrue(dodao);
    }

    @Test
    void testiranjeUspjesnogGetAll(){
        assertDoesNotThrow(() -> { jManager.getAll(); });
    }

    @Test
    void testiranjeUspjesnogBrisanja(){
        Jela j = new Jela();
        j.setJelo("Test");
        j.setCijena(1.);
        j.setOpis("Test");
        j.setIdRestorana(1);
        boolean obrisao=true;
        try {
            jManager.add(j);
            List<Jela> lista = jManager.getAllMealsFromRestaurant(1);
            for(Jela x: lista){
                if(x.getJelo().equals("Test")){
                    jManager.delete(x.getId());
                    break;
                }
            }
            lista = jManager.getAllMealsFromRestaurant(1);
            for(Jela x: lista){
                if(x.getJelo().equals("Test")){
                    obrisao=false;
                    break;
                }
            }
        } catch (OrderException e) {
            throw new RuntimeException(e);
        }
        assertTrue(obrisao);
    }
}
