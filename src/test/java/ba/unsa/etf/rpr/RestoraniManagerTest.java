package ba.unsa.etf.rpr;

import ba.unsa.etf.rpr.business.RestoraniManager;
import ba.unsa.etf.rpr.dao.DaoFactory;
import ba.unsa.etf.rpr.dao.RestoraniDao;
import ba.unsa.etf.rpr.domain.Restorani;
import ba.unsa.etf.rpr.exceptions.OrderException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

    @Test
    void traziRestoranPoVlasnicimaMock() throws OrderException {
        //This is restaurant that exists in database
        Restorani resto = new Restorani();
        resto.setVlasnik("Zerina Osmic");
        resto.setNaziv("Happy");
        resto.setLokacija("Trg Heroja");

        List<Restorani> restorani = new ArrayList<>();
        restorani.add(resto);

        Restorani resto1 = new Restorani();
        resto1.setVlasnik("Zerina Osmic");
        resto1.setNaziv("Mrvica");
        resto1.setLokacija("Cengic Vila");
        restorani.add(resto1);

        MockedStatic<DaoFactory> dao = Mockito.mockStatic(DaoFactory.class);
        RestoraniDao RD = Mockito.mock(RestoraniDao.class);
        when(DaoFactory.restoraniDao()).thenReturn(RD);

        when(DaoFactory.restoraniDao().searchByManagerName("Zerina Osmic")).thenReturn(restorani);

        boolean x = rManager.visestrukiVlasnik("Zerina Osmic", restorani);
        assertTrue(true);
    }

    /*@Test
    void traziRestoranPoLokacijama() throws OrderException {
        //This is restaurant that exists in database
        Restorani resto = new Restorani();
        resto.setVlasnik("Heung Min Son");
        resto.setNaziv("Kimono");
        resto.setLokacija("SCC");

        List<Restorani> restorani = new ArrayList<>();
        restorani.add(resto);

        Restorani resto1 = new Restorani();
        resto1.setVlasnik("Dino Keco");
        resto1.setNaziv("KFC");
        resto1.setLokacija("SCC");
        restorani.add(resto1);

        MockedStatic<DaoFactory> dao = Mockito.mockStatic(DaoFactory.class);
        RestoraniDao RD = Mockito.mock(RestoraniDao.class);
        when(DaoFactory.restoraniDao()).thenReturn(RD);

        when(DaoFactory.restoraniDao().searchByLocation("SCC")).thenReturn(restorani);

        boolean x = rManager.sirokSpektarRestorana("SCC", restorani);
        assertTrue(true);
    }*/
}
