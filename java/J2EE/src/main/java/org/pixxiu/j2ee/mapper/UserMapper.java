package org.pixxiu.j2ee.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.pixxiu.j2ee.pojo.User;

@Mapper
public interface UserMapper {

    /*用户注册*/
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    @Insert("insert into user(username,password,create_time,update_time) values(#{username},#{password},now(),now())")
    void add(String username, String password);

    /*更新数据*/
    @Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime} where id=#{id}")
    void update(User user);

    /*更新用户头像 和 更新时间*/
    @Update("update user set user_pic=#{avatarUrl},update_time=now() where id=#{id}")
    void updateAvatar(String avatarUrl, Integer id);

    /*更新用户的密码*/
    @Update("update user set password=#{newPwd},update_time=now() where id=#{id}")
    void updatePwd(String newPwd, Integer id);

}
