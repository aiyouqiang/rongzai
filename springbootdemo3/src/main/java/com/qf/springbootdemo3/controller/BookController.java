package com.qf.springbootdemo3.controller;

import com.qf.springbootdemo3.pojo.Book;
import com.qf.springbootdemo3.service.BookService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.sql.Date;
import java.util.List;
import java.util.Map;


@Controller
public class BookController {

    Logger logger = Logger.getLogger(BookController.class);


    @Resource(name = "bookServiceImpl")
    private BookService bookService;
    private BookService bookService1;

    @RequestMapping("/findAll")
  //  @ResponseBody
    public String findAll(Model model){
        List<Book> list = bookService.findAll();
        model.addAttribute("books",list);

        return "bookList";

    }

    @RequestMapping("/add")
    @ResponseBody
    public Book add(){
        Book book = new Book();
        book.setId(28);
        book.setTitle("流浪地球");
        book.setAuthor("刘慈欣");
        book.setPublishDate(Date.valueOf("2019-02-05"));
        book.setTypeId(102);
        bookService.add(book);
        return book;
    }

    @ResponseBody
    @RequestMapping("/delete")
    public String del(){
        try {
            bookService.delete(17);
            return "success";
        } catch (Exception e) {
            logger.error("删除失败"+e.getMessage());
        }
        return "falid";
    }

    @ResponseBody
    @RequestMapping("/findByPage")
    public Map<String,Object> findByPage(@RequestParam(defaultValue = "1")int nowPage,@RequestParam(defaultValue = "3") int pageSize){
        Map<String,Object> map = bookService.findByPage(nowPage,pageSize);
        return map;
    }

    @ResponseBody
    @RequestMapping("/update")
    public String update(){
        Book book = new Book();
        book.setTitle("实习生");
        book.setAuthor("安妮海瑟薇");
        book.setPublishDate(Date.valueOf("2015-05-16"));
        book.setTypeId(102);
        book.setId(16);

        bookService.update(book);
        try {
            logger.info("修改成功，修改编号为"+book.getId());
        } catch (Exception e) {
           logger.error("修改失败"+e.getMessage());
        }
        return "success";
    }


}
