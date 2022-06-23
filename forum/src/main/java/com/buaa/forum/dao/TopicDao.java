package com.buaa.forum.dao;
import com.buaa.forum.bean.Topic;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;
import java.util.List;

@Mapper
public interface TopicDao {
    @Insert("insert into topic(title, content, topic_user_id, topic_category) values (#{title},#{content},#{topic_user_id},#{topic_category})")
    @Options(useGeneratedKeys = true,keyProperty = "id",keyColumn = "id")
    void addTopic(Topic topic);

    @Delete("delete from topic where id=#{id}")
    void deleteTopic(@Param("id") int id);

    /**
     * 根据查询数据
     */
    @Select("select * from topic where title like CONCAT('%',#{title},'%')")
    List<Topic> findByTitle(@Param("title") String title);
    @Select("select * from topic where id=#{id}")
    Topic findById(@Param("id") int id);
    @Select("select * from topic where topic_category=#{topic_category}")
    List<Topic> findByCategory(@Param("topic_category") String topic_category);
    @Select("select * from topic where topic_user_id=#{topic_user_id}")
    List<Topic> findByUserId(@Param("topic_user_id") int topic_user_id);
    @Select("SELECT * FROM topic WHERE topic_category like #{topic_category} ORDER BY browse_count DESC limit 0,#{num}")
    List<Topic> findTopBrowsed(@Param("num") int num, @Param("topic_category") String topic_category);
    @Select("SELECT * FROM topic WHERE topic_category like #{topic_category} ORDER BY thumbs_up DESC limit 0,#{num}")
    List<Topic> findTopThumbUp(@Param("num") int num, @Param("topic_category") String topic_category);

    /**
     * 更新数据
     */
    @Update("update topic set comment_count=comment_count+1 where id=#{id}")
    void commentUp(@Param("id") int id);
    @Update("update topic set thumbs_up=thumbs_up+1 where id=#{id}")
    void thumbUp(@Param("id") int id);
    @Update("update topic set browse_count=browse_count+1 where id=#{id}")
    void browseUp(@Param("id") int id);
    @Update("update topic set status=#{status} where id=#{id}")
    void changeStatus(@Param("id") int id, @Param("status") int status);
}
