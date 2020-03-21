package com.dev.books.service.iml;

import com.dev.books.dao.CclassMapper;
import com.dev.books.pojo.Cclass;
import com.dev.books.service.CclassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CclassServiceImpl implements CclassService {
    @Autowired
    CclassMapper cclassMapper;

    @Override
    public List<Cclass> findAllCclass() {
        return cclassMapper.findAllCclass();
    }
}
