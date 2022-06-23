package com.buaa.forum.service;
import com.buaa.forum.bean.Comment;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import java.util.List;

public interface CommentService {
    Comment addComment(Comment comment) throws Exception;

    boolean deleteComment(int id);

    Comment findById(int id);
    List<Comment> findByUserId(int comment_user_id);
    List<Comment> findByTopicId(int comment_topic_id);
}
