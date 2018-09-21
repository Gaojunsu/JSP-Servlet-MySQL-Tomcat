package service;

import domain.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCats() throws SQLException;

    void addCategory(Category category) throws SQLException;

    Category findByCid(String cid)throws SQLException;

    void deleteByCid(String cid)throws SQLException;
}
