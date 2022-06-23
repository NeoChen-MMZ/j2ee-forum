package com.buaa.forum.controller;
import com.buaa.forum.bean.Comment;
import com.buaa.forum.bean.Result;
import com.buaa.forum.bean.Topic;
import com.buaa.forum.service.TopicService;
import com.buaa.forum.service.CommentService;
import com.buaa.forum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.sql.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private TopicService topicService;
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public Result addComment(@RequestBody Comment comment){
        comment.setComment_time(new Date(System.currentTimeMillis()));
        if (userService.findUserById(comment.getComment_user_id()) == null)
            return new Result<>(-2, "没有找到用户", comment);
        if (topicService.findById(comment.getComment_topic_id()) == null)
            return new Result<>(-3, "没有回复的贴子", comment);
        int replyId = comment.getComment_reply_id();
        if (replyId > 0 && commentService.findById(replyId) == null)
            return new Result<>(-4, "没有找到reply的回复", comment);
        comment.setFloor(topicService.findById(comment.getComment_topic_id()).getComment_count() + 1);
        try {
            comment = commentService.addComment(comment);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result<>(-1, "添加贴子失败", comment);
        }
        comment.setComment_user(userService.findUserById(comment.getComment_user_id()));
        if (replyId > 0)
            comment.setComment_reply_content(commentService.findById(replyId).getContent());
        topicService.commentUp(comment.getComment_topic_id());
        return new Result<>(1, "成功添加贴子", comment);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public Result deleteComment(@RequestParam(value = "id", required = true) int id) {
        if (!commentService.deleteComment(id))
            return new Result(-1, "没有找到回复");
        return new Result(1, "成功删除回复");
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public Result findById(@RequestParam(value = "id", required = true) int id) {
        Comment comment = commentService.findById(id);
        if (comment == null)
            return new Result(-1, "没有找到回复");
        comment.setComment_user(userService.findUserById(comment.getComment_user_id()));
        int replyId = comment.getComment_reply_id();
        if (replyId > 0)
            comment.setComment_reply_content(commentService.findById(replyId).getContent());
        return new Result(1, "查找以下回复", comment);
    }

    @RequestMapping(value = "/getByTopicId", method = RequestMethod.GET)
    public Result findByTopicId(@RequestParam(value = "id", required = true) int id) {
        List<Comment> comments = commentService.findByTopicId(id);
        if (comments.isEmpty())
            return new Result(-1, "没有找到回复");
        comments.forEach(comment -> {
            comment.setComment_user(userService.findUserById(comment.getComment_user_id()));
            int replyId = comment.getComment_reply_id();
            if (replyId > 0)
                comment.setComment_reply_content(commentService.findById(replyId).getContent());
        });
        return new Result(1, "查找以下回复", comments);
    }

    @RequestMapping(value = "/getByUserId", method = RequestMethod.GET)
    public Result findByUserId(@RequestParam(value = "id", required = true) int id) {
        List<Comment> comments = commentService.findByUserId(id);
        if (comments.isEmpty())
            return new Result(-1, "没有找到回复");
        comments.forEach(comment -> {
            comment.setComment_user(userService.findUserById(comment.getComment_user_id()));
            int replyId = comment.getComment_reply_id();
            if (replyId > 0)
                comment.setComment_reply_content(commentService.findById(replyId).getContent());
        });
        return new Result(1, "查找以下回复", comments);
    }
}
