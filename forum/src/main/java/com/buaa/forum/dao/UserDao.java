package com.buaa.forum.dao;
import com.buaa.forum.bean.User;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Mapper
public interface UserDao {
    /**
     * 新增数据
     */
    @Insert("insert into user(id,email,password,username) values (#{id},#{email},#{password},#{username})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addUser(User user);

    /**
     * 修改数据
     */
    @Update("update user set username=#{username},password=#{password} where id=#{id}")
    void updateUser(User user);
    @Update("update user set avatar=#{avatar} where id=#{id}")
    void updateUserAvatar(@Param("id") int id, @Param("avatar") String avatar);

    /**
     * 删除数据
     */
    @Delete("delete from user where id=#{id}")
    void deleteUser(@Param("id") int id);

    /**
     * 根据查询数据
     */
    @Select("select * from user where username like CONCAT('%',#{userName},'%')")
    List<User> findByName(@Param("userName") String userName);
    @Select("select * from user where id=#{id}")
    User findById(@Param("id") int id);
    @Select("select * from user where email=#{email}")
    User findByEmail(@Param("email") String email);

    /**
     * 查询所有数据
     */
    @Select("select * FROM user")
    List<User> findAll();
}
