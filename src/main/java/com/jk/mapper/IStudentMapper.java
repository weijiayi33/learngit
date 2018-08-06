package com.jk.mapper;

import com.jk.model.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IStudentMapper {

    @Select("select * from student")
    List<Student> querymyb();

    @Delete("delete from student where stuid in #(id)")
    void deletestu(@Param("id") String id);

    @Insert("insert into student values(#{stuid},#{stuname})")
    String savestu(Student student);

    @Update("update Student set stuname=#{stuname} where stuid=#{stuid}")
    void updatestu(Student student);
}
