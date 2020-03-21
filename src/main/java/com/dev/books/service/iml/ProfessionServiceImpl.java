package com.dev.books.service.iml;

import com.dev.books.dao.ProfessionMapper;
import com.dev.books.pojo.Profession;
import com.dev.books.service.ProfessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfessionServiceImpl implements ProfessionService {
    @Autowired
    ProfessionMapper professionMapper;
    @Override
    public List<Profession> findAllProfession() {
        return professionMapper.findAllProfession();
    }
}
