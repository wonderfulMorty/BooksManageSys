package com.dev.books.service.iml;

import com.dev.books.dao.GradeMapper;
import com.dev.books.pojo.Grade;
import com.dev.books.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl implements GradeService {
    @Autowired
    GradeMapper gradeMapper;
    @Override
    public List<Grade> findAllGrade() {
        return gradeMapper.findAllGrade();
    }
}
