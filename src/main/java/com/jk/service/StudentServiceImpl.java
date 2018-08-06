package com.jk.service;

import com.jk.mapper.IStudentMapper;
import com.jk.model.Student;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@Service
public class StudentServiceImpl implements IStudentService{
    @Resource
    private IStudentMapper studentMapper;

    @Resource
    private MongoTemplate mongoTemplate;



    @Override
    public List<Student> querymyb() {
        return studentMapper.querymyb();
    }

    @Override
    public void deletestu(String id) {
        mongoTemplate.remove(new Query(Criteria.where("stuid").is(id)),Student.class);
        studentMapper.deletestu(id);
    }

    @Override
    public String savestu(Student student) {
        student.setStuid("22");
        student.setStuname("22");
        mongoTemplate.save(student);
        return studentMapper.savestu(student);
    }

    @Override
    public List<Student> querymongo() {
        Query query=new Query();
       List<Student> stulist =  mongoTemplate.find(query,Student.class);
        return stulist;
    }

    @Override
    public void updatestu(Student student) {
        studentMapper.updatestu(student);
    }

}
