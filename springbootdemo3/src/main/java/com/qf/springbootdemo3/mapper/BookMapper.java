package com.qf.springbootdemo3.mapper;

import com.qf.springbootdemo3.pojo.Book;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface BookMapper {

    @Select("select * from book")
    public List<Book> findAll();

    @Insert("insert into book value(#{id},#{title},#{author},#{publishDate},#{typeId})")
    public  int insert(Book book);

    @Update("update book set title=#{title},author=#{author},publishDate=#{publishDate},typeId=#{typeId} where id=#{id}")
    public int update(Book book);

    @Delete("delete from book where id=#{id}")
    public int delete(int id);
}
