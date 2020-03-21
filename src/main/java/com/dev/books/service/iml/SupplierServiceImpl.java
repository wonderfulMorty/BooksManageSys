package com.dev.books.service.iml;

import com.dev.books.dao.SuppliersMapper;
import com.dev.books.pojo.Supplier;
import com.dev.books.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SupplierServiceImpl implements SupplierService {
    @Autowired
    SuppliersMapper suppliersMapper;


    @Override
    public List<Supplier> findAllSupplierByPages(int start, int pageSize) {
        return suppliersMapper.findAllSupplierByPages(start,pageSize);
    }

    @Override
    public List<Supplier> findAllSupplier() {
        return suppliersMapper.findAllSupplier();
    }

    @Override
    public int addSupplier(Map map) {
        return suppliersMapper.addSupplier(map);
    }

    @Override
    public int deleteQsById(String id) {
        return suppliersMapper.deleteQsById(id);
    }

    @Override
    public int updateQsById(Map map) {
        return suppliersMapper.updateQsById(map);
    }

    @Override
    public List<String> findAllQsName() {
        return suppliersMapper.findAllQsName();
    }
}
