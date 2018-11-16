package com.stone.mapper;

import org.apache.ibatis.annotations.Mapper;  
import org.apache.ibatis.annotations.Select;  
  
import com.stone.bean.User;  
  
@Mapper  
public interface UserMapper {   
    @Select("select * from user where age = #{age}")  
    User Select(int age);  
}
