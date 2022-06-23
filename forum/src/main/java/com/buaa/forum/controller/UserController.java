package com.buaa.forum.controller;
import com.buaa.forum.bean.User;
import com.buaa.forum.bean.Result;
import com.buaa.forum.service.UserService;
import com.buaa.forum.uitl.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Value("${web.upload-path}")
    private String path;
    @Value("${web.image-path}")
    private String imagePath;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addUser(@RequestBody User user) {
        try {
            user = userService.addUser(user);
        }catch (DuplicateKeyException e) {
            //e.printStackTrace();
            return new Result(-1,"邮箱重复", user);
        }catch (DataIntegrityViolationException e) {
            //e.printStackTrace();
            return new Result(-2,"未检测到用户名", user);
        }
        return new Result(1,"添加用户成功", user);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Result updateUser(@RequestBody User user) {
        if (user.getId() == 0)
            return new Result(-2,"未包含用户id");
        if (userService.updateUser(user))
            return new Result(1,"更新用户成功");
        return new Result(-1,"更新用户失败");
    }

    @RequestMapping(value = "/changeAvatar", method = RequestMethod.POST)
    public Result updateUserAvatar(@RequestParam(value = "file") MultipartFile file, @RequestParam(value = "id") int id) {
        String filename = FileUtil.upload(file, path, file.getOriginalFilename());
        if (!userService.updateUserAvatar(id, imagePath + filename))
            return new Result(-1,"更新头像失败");
        return new Result(1,"更新头像成功", imagePath + filename);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result delete(@RequestParam(value = "id", required = true) int Id) {
        if (userService.deleteUser(Id))
            return new Result(1,"删除用户成功");
        return new Result(-1,"删除用户失败");
    }


    @RequestMapping(value = "/getByName", method = RequestMethod.GET)
    public Result findByUserName(@RequestParam(value = "userName", required = true) String userName) {
        List<User> user = userService.findUserByName(userName);
        if (user != null)
            return new Result<>(1, "成功找到用户", user);
        return new Result<>(-1, "没有找到用户");
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public Result findByUserId(@RequestParam(value = "id", required = true) int Id) {
        User user = userService.findUserById(Id);
        if (user != null)
            return new Result<>(1, "成功找到用户", user);
        return new Result<>(-1, "没有找到用户");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody User user) {
        User temp = userService.findUserByEmail(user.getEmail());
        if (temp == null) {
            return new Result<>(-1, "没有找到该邮箱对应的用户");
        }
        if (!temp.getPassword().equals(user.getPassword())) {
            return  new Result<>(-2, "密码错误");
        }
        return new Result<>(1, "登录成功", temp);
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public Result findByUserAge() {
        return new Result<>(1,"查询所有用户", userService.findAll());
    }
}
