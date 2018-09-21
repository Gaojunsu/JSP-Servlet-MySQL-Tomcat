package dao;

import domain.PageModel;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List findProducrs(int currentNum, int pageSize) throws SQLException;

    int findToralRecords()  throws SQLException;
}
