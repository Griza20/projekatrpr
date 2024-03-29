package ba.unsa.etf.rpr.dao;

/**
 * Factory method for singleton implementation of DAOs
 * @author Amar Grizovic
 */
public class DaoFactory {
    private static final DostavljaciDao dostavljaciDao = DostavljaciDaoSQLImpl.getInstance();
    private static final RestoraniDao restoraniDao = RestoraniDaoSQLImpl.getInstance();
    private static final NarudzbeDao narudzbeDao = NarudzbeDaoSQLImpl.getInstance();
    private static final JelaDao jelaDao = JelaDaoSQLImpl.getInstance();

    private DaoFactory(){
    }

    public static DostavljaciDao dostavljaciDao(){
        return dostavljaciDao;
    }

    public static RestoraniDao restoraniDao(){ return restoraniDao; }

    public static NarudzbeDao narudzbeDao(){
        return narudzbeDao;
    }

    public static JelaDao jelaDao() { return jelaDao; }
}
