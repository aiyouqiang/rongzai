package com.qf.springbootdemo3.service.impl;

import com.qf.springbootdemo3.mapper.BookMapper;
import com.qf.springbootdemo3.pojo.Book;
import com.qf.springbootdemo3.service.BookService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service(value = "bookServiceImpl")
public class BookServiceImpl implements BookService {
    @Resource
    BookMapper bookMapper;

    @Override
    public List<Book> findAll() {
        return bookMapper.findAll();
    }

    @Override
    public Map<String, Object> findByPage(int nowPage, int pageSize) {
        PageHelper.startPage(nowPage,pageSize);
        List<Book> list = bookMapper.findAll();
        PageInfo pageInfo = new PageInfo(list);

        Map<String,Object> map = new HashMap<>();
        map.put("rows",pageInfo.getList());
        map.put("total",pageInfo.getPages());

        return map;
    }

    @Override
    public void add(Book book) {

        bookMapper.insert(book);
    }

    @Override
    public void update(Book book) {
        int update = bookMapper.update(book);
        if (update==0){
            throw new RuntimeException("该记录不存在");
        }
    }

    @Override
    public void delete(int id) {
        int i = bookMapper.delete(id);
        if (i==0){
            throw new RuntimeException("该记录不存在");
        }
    }
}
