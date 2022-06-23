package com.buaa.forum.service;
import com.buaa.forum.bean.Topic;
import com.buaa.forum.dao.TopicDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TopicServiceImpI implements TopicService {
    @Autowired
    private TopicDao topicDao;

    @Override
    public Topic addTopic(Topic topic) throws Exception {
        topicDao.addTopic(topic);
        return topic;
    }

    @Override
    public boolean deleteTopic(int id) {
        Topic topic = topicDao.findById(id);
        if (topic == null) return false;
        topicDao.deleteTopic(id);
        return true;
    }

    @Override
    public List<Topic> findByTitle(String title) {
        return topicDao.findByTitle(title);
    }

    @Override
    public Topic findById(int id) {
        return topicDao.findById(id);
    }

    @Override
    public List<Topic> findByCategory(String topic_category) {
        return topicDao.findByCategory(topic_category);
    }

    @Override
    public List<Topic> findByUserId(int topic_user_id) {
        return topicDao.findByUserId(topic_user_id);
    }

    @Override
    public List<Topic> findTopBrowsed(int num, String topic_category) {
        return topicDao.findTopBrowsed(num, topic_category);
    }

    @Override
    public List<Topic> findTopThumbUp(int num, String topic_category) {
        return topicDao.findTopThumbUp(num, topic_category);
    }

    @Override
    public boolean commentUp(int id) {
        Topic topic = topicDao.findById(id);
        if (topic == null) return false;
        topicDao.commentUp(id);
        return true;
    }

    @Override
    public boolean thumbUp(int id) {
        Topic topic = topicDao.findById(id);
        if (topic == null) return false;
        topicDao.thumbUp(id);
        return true;
    }

    @Override
    public boolean browseUp(int id) {
        Topic topic = topicDao.findById(id);
        if (topic == null) return false;
        topicDao.browseUp(id);
        return true;
    }

    @Override
    public boolean changeStatus(int id, int status) {
        Topic topic = topicDao.findById(id);
        if (topic == null) return false;
        topicDao.changeStatus(id, status);
        return true;
    }
}
