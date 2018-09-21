package service;

import domain.PageModel;

import java.sql.SQLException;

public interface ProductService {
    PageModel findProducts(int currentNum,int pageSize) throws SQLException;
}
