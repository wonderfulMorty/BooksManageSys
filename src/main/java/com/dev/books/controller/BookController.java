package com.dev.books.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import com.alibaba.fastjson.JSON;
import com.dev.books.pojo.Book;
import com.dev.books.pojo.Supplier;
import com.dev.books.service.BookService;
import com.dev.books.service.SupplierService;
import com.dev.books.util.Layui;
import com.dev.books.util.POIUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class BookController {
    @Autowired
    BookService bookService;

    /*
    将表格中的数据插入到
     */
    @ResponseBody
    @RequestMapping("/book/insertData")
    public Object supplierImportExcel(@RequestParam("file") MultipartFile file){
        int n= 0;
        //long s = file.getSize();
        Map<String,Object> map = new HashMap<>();
        Map<String, Object> result = new HashMap<String, Object>();
        List<Book> book = POIUtil.importExcel(file,Book.class);
        System.out.println(book);
        //List <Supplier> supplier2 = supplierService.findAllSupplier();
        //supplier1.addAll(supplier2);
        for(int i=0;i<book.size();i++){
            map.put("id",book.get(i).getId());
            map.put("book_name",book.get(i).getBook_name());
            map.put("book_kind",book.get(i).getBook_kind());
            map.put("book_price",book.get(i).getBook_price());
            map.put("qs_name",book.get(i).getQs_name());
            n = bookService.addBook(map);
        }
        System.out.println("map:"+map);
        if(n>0){
            result.put("code", 0);
            result.put("message", "success");
            result.put("data", file.getOriginalFilename());
        }else{
            result.put("code", -1);
            result.put("message", "failure");
            result.put("data", file.getOriginalFilename());
        }
        return result;
    }

    @ResponseBody
    @RequestMapping(value = "/book/getAllBookByPage",produces="application/json;charset=UTF-8")
    public String getAllBookByPage(@RequestParam("limit") String limit, @RequestParam("page") String page){
        int start = (Integer.parseInt(page) - 1)*Integer.parseInt(limit);
        int pageSize = Integer.parseInt(limit);
        List <Supplier> books = bookService.findAllBookByPages(start,pageSize);
        List <Supplier> allData = bookService.findAllBook();
        Layui l = Layui.data(allData.size(), books);
        return JSON.toJSONString(l);
    }

    @ResponseBody
    @RequestMapping(value = "/book/getAllBook",produces="application/json;charset=UTF-8")
    public String getAllBook(){
        List <Supplier> allData = bookService.findAllBook();
        Layui l = Layui.data(allData.size(), allData);
        return JSON.toJSONString(l);
    }


    @ResponseBody
    @RequestMapping(value = "/book/findAllBookKind",produces="application/json;charset=UTF-8")
    public String findAllBookKind(){
        List <String> allKinds = bookService.findAllBookKind();
        return JSON.toJSONString(allKinds);
    }

    @RequestMapping("/book/exportData")
    public String exportData(HttpServletResponse response){
        List <Supplier> books = bookService.findAllBook();
        POIUtil.exportExcel(books,Book.class,"书籍基本信息","",response);
        return  NormalExcelConstants.EASYPOI_EXCEL_VIEW;//需要配置新的视图解析器并设置优先级和扫描
    }

    @ResponseBody
    @RequestMapping(value = "/updateBookById")
    public String updateBookById(@RequestBody Map map){
        int n = bookService.updateBookById(map);
        if(n>0){
            return "success";
        }
        return "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteBookById")
    public String deleteBookById(@RequestParam("book_id")String book_id){
        int n = bookService.deleteBookById(book_id);
        if(n>0){
            return "success";
        }
        return "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/findAllBookByBookName",produces="application/json;charset=UTF-8")
    public String findAllBookByBookName(@RequestParam("key[book_name]")String book_name){
        List<Supplier> books = bookService.findAllBookByBookName(book_name);
        Layui l = Layui.data(books.size(), books);
        return JSON.toJSONString(l);
    }

    @ResponseBody
    @RequestMapping(value = "/findBookNameByQsName",produces="application/json;charset=UTF-8")
    public String findBookNameByQsName(@RequestParam("qs_name")String qs_name){
        List<String> book_names = bookService.findBookNameByQsName(qs_name);
        return JSON.toJSONString(book_names);
    }
}
