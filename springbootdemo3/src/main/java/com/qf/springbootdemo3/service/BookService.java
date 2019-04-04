package com.qf.springbootdemo3.service;


import com.qf.springbootdemo3.pojo.Book;

import java.util.List;
import java.util.Map;

public interface BookService {
    public List<Book> findAll();

    public Map<String,Object> findByPage(int nowPage, int pageSize);

    public void add(Book book);

    public void update(Book book);

    public void delete(int id);
}
