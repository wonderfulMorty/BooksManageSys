package com.dev.books.controller;

import cn.afterturn.easypoi.entity.vo.NormalExcelConstants;
import com.alibaba.fastjson.JSON;
import com.dev.books.pojo.Supplier;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class SupplierController {
    @Autowired
    SupplierService supplierService;

    /*
    将表格中的数据插入到
     */
    @ResponseBody
    @RequestMapping("/supplier/insertData")
    public Object supplierImportExcel(@RequestParam("file") MultipartFile file){
        int n= 0;
        long s = file.getSize();
        Map<String,String> map = new HashMap<>();
        Map<String, Object> result = new HashMap<String, Object>();
        List<Supplier> supplier1 = POIUtil.importExcel(file,Supplier.class);
        System.out.println(supplier1);
        //List <Supplier> supplier2 = supplierService.findAllSupplier();
        //supplier1.addAll(supplier2);
        for(int i=0;i<supplier1.size();i++){
            map.put("id",supplier1.get(i).getId());
            map.put("qs_name",supplier1.get(i).getQs_name());
            map.put("qs_location",supplier1.get(i).getQs_location());
            map.put("qs_phone",supplier1.get(i).getQs_phone());
            n = supplierService.addSupplier(map);
        }
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
    @RequestMapping(value = "/supplier/getAllSupplierByPage",produces="application/json;charset=UTF-8")
    public String getAllSupplierByPage(@RequestParam("limit") String limit, @RequestParam("page") String page){
        int start = (Integer.parseInt(page) - 1)*Integer.parseInt(limit);
        int pageSize = Integer.parseInt(limit);
        List <Supplier> suppliers = supplierService.findAllSupplierByPages(start,pageSize);
        List <Supplier> allData = supplierService.findAllSupplier();
        Layui l = Layui.data(allData.size(), suppliers);
        return JSON.toJSONString(l);
    }

    @ResponseBody
    @RequestMapping(value = "/supplier/getAllSupplier",produces="application/json;charset=UTF-8")
    public String getAllSupplier(){
        List <Supplier> allData = supplierService.findAllSupplier();
        Layui l = Layui.data(allData.size(), allData);
        return JSON.toJSONString(l);
    }


    @RequestMapping("/supplier/exportData")
    public String exportData(HttpServletResponse response){
        List <Supplier> suppliers = supplierService.findAllSupplier();
        POIUtil.exportExcel(suppliers,Supplier.class,"供应商基本信息","",response);
        return  NormalExcelConstants.EASYPOI_EXCEL_VIEW;//需要配置新的视图解析器并设置优先级和扫描
    }


    @ResponseBody
    @RequestMapping(value = "/updateQsById")
    public String updateQsById(@RequestBody Map map){
        int n = supplierService.updateQsById(map);
        if(n>0){
            return "success";
        }
        return "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/deleteQsById")
    public String deleteQsById(@RequestParam("qs_id")String qs_id){
        int n = supplierService.deleteQsById(qs_id);
        if(n>0){
            return "success";
        }
        return "failure";
    }

    @ResponseBody
    @RequestMapping(value = "/findAllQsName",produces="application/json;charset=UTF-8")
    public String findAllQsName(){
        List<String> allQsName = supplierService.findAllQsName();
        return  JSON.toJSONString(allQsName);
    }

}
