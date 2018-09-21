package service.serviceImp;

import dao.CategoryDao;
import dao.daoImp.CategoryDaoImp;
import domain.Category;
import service.CategoryService;
import web.tools.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImp implements CategoryService{
    @Override
    public List<Category> getAllCats() throws SQLException {
        CategoryDao categoryDao = (CategoryDao) BeanFactory.createObject("CategoryDao");
        return categoryDao.getAllCategorys();
    }
    @Override
    public void addCategory(Category category) throws SQLException {
        CategoryDao categoryDao =(CategoryDao) BeanFactory.createObject("CategoryDao");
        categoryDao.addCategory(category);
    }
    @Override
    public Category findByCid(String cid) throws SQLException {
        CategoryDao categoryDao = (CategoryDao) BeanFactory.createObject("CategoryDao");
        return categoryDao.findByCid(cid);
    }
    @Override
    public void deleteByCid(String cid) throws SQLException {
        CategoryDao categoryDao = (CategoryDao) BeanFactory.createObject("CategoryDao");
        categoryDao.deleteByCid(cid);
    }
}
