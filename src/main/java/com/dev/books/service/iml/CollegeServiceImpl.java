package com.dev.books.service.iml;

import com.dev.books.dao.CollegeMapper;
import com.dev.books.pojo.College;
import com.dev.books.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CollegeServiceImpl implements CollegeService {
    @Autowired
    CollegeMapper collegeMapper;
    @Override
    public List<College> findAllCollege() {
        return collegeMapper.findAllCollege();
    }
}
