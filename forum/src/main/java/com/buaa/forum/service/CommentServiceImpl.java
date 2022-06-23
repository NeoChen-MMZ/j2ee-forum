package com.buaa.forum.service;
import com.buaa.forum.bean.Comment;
import com.buaa.forum.dao.CommentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentDao commentDao;

    @Override
    public Comment addComment(Comment comment) throws Exception {
        commentDao.addComment(comment);
        return comment;
    }

    @Override
    public boolean deleteComment(int id) {
        if (commentDao.findById(id) == null) return false;
        commentDao.deleteComment(id);
        return true;
    }

    @Override
    public Comment findById(int id) {
        return commentDao.findById(id);
    }

    @Override
    public List<Comment> findByUserId(int comment_user_id) {
        return commentDao.findByUserId(comment_user_id);
    }

    @Override
    public List<Comment> findByTopicId(int comment_topic_id) {
        return commentDao.findByTopicId(comment_topic_id);
    }
}
