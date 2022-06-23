package com.buaa.forum.service;
import com.buaa.forum.bean.Topic;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

public interface TopicService {
    /**
     * 新增贴子
     * @param topic
     * @return
     */
    Topic addTopic(Topic topic) throws Exception;

    boolean deleteTopic(int id);

    List<Topic> findByTitle(String title);
    Topic findById(int id);
    List<Topic> findByCategory(String topic_category);
    List<Topic> findByUserId(int topic_user_id);
    List<Topic> findTopBrowsed(int num, String topic_category);
    List<Topic> findTopThumbUp(int num, String topic_category);

    boolean commentUp(int id);
    boolean thumbUp(int id);
    boolean browseUp(int id);
    boolean changeStatus(int id, int status);
}
