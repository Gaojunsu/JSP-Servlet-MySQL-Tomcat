package dao;

import domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> getAllCategorys() throws SQLException;

    void addCategory(Category category)throws SQLException;

    Category findByCid(String cid)throws SQLException;

    void deleteByCid(String cid)throws SQLException;
}
