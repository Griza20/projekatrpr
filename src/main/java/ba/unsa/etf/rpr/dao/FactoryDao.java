package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 * @author Amar Grizovic
 */
public class DaoFactory {
    private static final DostavljaciDao dostavljaciDao = new DostavljaciDaoSQLImpl();
    private static final RestoraniDao restoraniDao = new RestoraniDaoSQLImpl();
    private static final NarudzbeDao narudzbeDao = new NarudzbeDaoSQLImpl();

    private DaoFactory(){
    }

    public static DostavljaciDao dostavljaciDao(){
        return dostavljaciDao;
    }

    public static RestoraniDao restoraniDao(){
        return restoraniDao;
    }

    public static NarudzbeDao narudzbeDao(){
        return narudzbeDao;
    }

}
