package com.buaa.forum.service;
import com.buaa.forum.bean.User;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;

import java.util.List;

public interface UserService {
    /**
     * 新增用户
     * @param user
     * @return
     */
    User addUser(User user) throws DataIntegrityViolationException, DuplicateKeyException;

    /**
     * 修改用户
     * @param user
     * @return
     */
    boolean updateUser(User user);

    boolean updateUserAvatar(int id, String avatar);


    /**
     * 删除用户
     * @param id
     * @return
     */
    boolean deleteUser(int id);

    /**
     * 根据名字查询用户信息
     * @param userName
     */
    List<User> findUserByName(String userName);

    /**
     * 根据ID查询用户信息
     * @param id
     */
    User findUserById(int id);

    /**
     * 根据ID查询用户信息
     * @param email
     */
    User findUserByEmail(String email);


    /**
     * 查询所有数据
     * @return
     */
    List<User> findAll();
}
