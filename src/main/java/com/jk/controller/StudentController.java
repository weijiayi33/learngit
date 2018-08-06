package com.jk.controller;

import com.jk.model.Student;
import com.jk.service.IStudentService;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("student")
public class StudentController {
    @Resource
    private IStudentService studentService;

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Resource
    private JdbcTemplate jdbcTemplate;



    //mybatis查询
    @RequestMapping("querymyb")
    @ResponseBody
    public List<Student> querymyb(){
        List<Student> stulist =  studentService.querymyb();
        return stulist;
    }

    //删除  和mongodb删除
    @RequestMapping("deletestu")
    @ResponseBody
    public void deletestu(String id){
        studentService.deletestu(id);
    }

    //新增 和mongodb新增
    @RequestMapping("savestu")
    @ResponseBody
    public void   savestu(Student student){
        studentService.savestu(student);
    }

    //修改
    @RequestMapping("updatestu")
    @ResponseBody
    public void updatestu(){
        Student student = new Student();
        student.setStuid("");
        student.setStuname("张三");
        studentService.updatestu(student);
    }


    //存 查 redis
    @RequestMapping("saveredis")
    @ResponseBody
    public void saveredis(){
        redisTemplate.opsForValue().set("33","33");
        String redisstring = (String)redisTemplate.opsForValue().get("33");
        System.out.println(redisstring);
    }

    //mongodb查询(新增删除和mybatis在一块)
    @RequestMapping("querymongo")
    @ResponseBody
    public List<Student>  querymongo(Student student){
        List<Student> stulist =  studentService.querymongo();
        return stulist;
    }


    //jdbc  查询
    @RequestMapping("queryjdbc")
    @ResponseBody
    public List<Map<String, Object>> queryjdbc(){
        String sql = "select * from student ";
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    //jdbc  新增
    @RequestMapping("savejdbc")
    @ResponseBody
    public void savejdbc(String stuid,String stuname){
        String sql1="insert into USER values(?,?)";
        Object arr[] = {stuid,stuname};
        jdbcTemplate.update(sql1,arr);
    }

    //jdbc  删除
    @RequestMapping("/deletejdbc")
    @ResponseBody
    public void deleteStu(Student stu) {
        String sql = "delete from Student where stuid = ?";
        Object args[] = {stu.getStuid()};
        jdbcTemplate.update(sql,args);
        System.out.println("删除成功");

    }

    //jdbc  修改
    @RequestMapping("/updatejdbs")
    @ResponseBody
    public void updateStu(Student stu) {
        String sql = "update Student set age = ?,name = ? where id = ?";
        Object args[] = {stu.getStuid(),stu.getStuname()};
        jdbcTemplate.update(sql,args);
        System.out.println("修改成功");
    }


    //跳转到 freemarker.ftl
    @RequestMapping("/freemarker")
    public String freemarker(Map<String, Object> map){
        map.put("name", "Joe");
        map.put("sex", 1);    //sex:性别，1：男；0：女；
        // 模拟数据
        List<Map<String, Object>> friends = new ArrayList<Map<String, Object>>();
        Map<String, Object> friend = new HashMap<String, Object>();
        friend.put("name", "xbq");
        friend.put("age", 22);
        friends.add(friend);
        friend = new HashMap<String, Object>();
        friend.put("name", "July");
        friend.put("age", 18);
        friends.add(friend);
        map.put("friends", friends);
        return "freemarker";
    }

}
