package com.buaa.forum.controller;
import com.buaa.forum.bean.Topic;
import com.buaa.forum.bean.User;
import com.buaa.forum.bean.Result;
import com.buaa.forum.service.TopicService;
import com.buaa.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(value = "/topic")
public class TopicController {
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addTopic(@RequestBody Topic topic){
        User user = userService.findUserById(topic.getTopic_user_id());
        if (user == null)
            return new Result<>(-2, "没有找到用户", topic);
        try {
            topic = topicService.addTopic(topic);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(-1, "添加贴子失败", topic);
        }
        topic.setTopic_user(user);
        return new Result<>(1, "成功添加贴子", topic);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result deleteTopic(@RequestParam(value = "id", required = true) int id) {
        if (!topicService.deleteTopic(id))
            return new Result(-1, "没有找到贴子");
        return new Result(1, "成功删除贴子");
    }

    @RequestMapping(value = "/getByTitle", method = RequestMethod.GET)
    public Result findByTitle(@RequestParam(value = "title", required = true) String title) {
        List<Topic> topics = topicService.findByTitle(title);
        if (topics.isEmpty())
            return new Result(-1, "没有查找到贴子");
        topics.forEach(topic -> {topic.setTopic_user(userService.findUserById(topic.getTopic_user_id()));});
        return new Result(1, "查找以下贴子", topics);
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public Result findById(@RequestParam(value = "id", required = true) int id) {
        Topic topic = topicService.findById(id);
        if (topic == null)
            return new Result(-1, "没有查找到贴子");
        topic.setTopic_user(userService.findUserById(topic.getTopic_user_id()));
        return new Result(1, "查找以下贴子", topic);
    }

    @RequestMapping(value = "/getByUserId", method = RequestMethod.GET)
    public Result findByUserId(@RequestParam(value = "id", required = true) int id) {
        List<Topic> topics = topicService.findByUserId(id);
        if (topics.isEmpty())
            return new Result(-1, "没有查找到贴子");
        topics.forEach(topic -> {topic.setTopic_user(userService.findUserById(topic.getTopic_user_id()));});
        return new Result(1, "查找以下贴子", topics);
    }

    @RequestMapping(value = "/getByCategory", method = RequestMethod.GET)
    public Result findByCategory(@RequestParam(value = "category", required = true) String category) {
        List<Topic> topics = topicService.findByCategory(category);
        if (topics.isEmpty())
            return new Result(-1, "没有查找到贴子");
        topics.forEach(topic -> {topic.setTopic_user(userService.findUserById(topic.getTopic_user_id()));});
        return new Result(1, "查找以下贴子", topics);
    }

    @RequestMapping(value = "/getTopBrowsed", method = RequestMethod.GET)
    public Result findTopBrowsed(@RequestParam(required = true) int num, @RequestParam(value = "category", required = false) String category) {
        if (category == null) category = "%";
        List<Topic> topics = topicService.findTopBrowsed(num, category);
        if (topics.isEmpty())
            return new Result(-1, "没有查找到贴子");
        topics.forEach(topic -> {topic.setTopic_user(userService.findUserById(topic.getTopic_user_id()));});
        return new Result(1, "查找以下贴子", topics);
    }

    @RequestMapping(value = "/getTopThumbs", method = RequestMethod.GET)
    public Result findTopThumbUp(@RequestParam(required = true) int num, @RequestParam(value = "category", required = false) String category) {
        System.out.println(category);
        if (category == null) category = "%";
        List<Topic> topics = topicService.findTopThumbUp(num, category);
        if (topics.isEmpty())
            return new Result(-1, "没有查找到贴子");
        topics.forEach(topic -> {topic.setTopic_user(userService.findUserById(topic.getTopic_user_id()));});
        return new Result(1, "查找以下贴子", topics);
    }

    @RequestMapping(value = "/thumbUp", method = RequestMethod.GET)
    public Result thumbUp(@RequestParam(value = "id", required = true) int id) {
        if(!topicService.thumbUp(id))
            return new Result(-1, "没有找到贴子");
        return new Result(1, "点赞成功");
    }

    @RequestMapping(value = "/browseUp", method = RequestMethod.GET)
    public Result browseUp(@RequestParam(value = "id", required = true) int id) {
        if(!topicService.browseUp(id))
            return new Result(-1, "没有找到贴子");
        return new Result(1, "浏览量增加");
    }

    @RequestMapping(value = "/setStatus", method = RequestMethod.GET)
    public Result changeStatus(@RequestParam(value = "id") int id, @RequestParam(value = "status") int status) {
        if(!topicService.changeStatus(id, status))
            return new Result(-1, "没有找到贴子");
        return new Result(1, "改变状态为" + status);
    }
}
