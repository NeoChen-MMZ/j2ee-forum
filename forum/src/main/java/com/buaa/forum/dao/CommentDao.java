package com.buaa.forum.dao;

import com.buaa.forum.bean.Comment;
import org.apache.ibatis.annotations.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Mapper
public interface CommentDao {
    /**
     * 新增数据
     * @param comment
     */
    @Insert("insert into comment(content, comment_user_id, comment_topic_id, floor, comment_reply_id) values (#{content}, #{comment_user_id}, #{comment_topic_id}, #{floor}, #{comment_reply_id})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void addComment(Comment comment);

    /**
     * 删除数据
     * @param id
     */
    @Delete("delete from comment where id=#{id}")
    void deleteComment(@Param("id") int id);

    /**
     *查询数据
     */
    @Select("select * from comment where comment_topic_id=#{comment_topic_id}")
    List<Comment> findByTopicId(@Param("comment_topic_id") int comment_topic_id);
    @Select("select * from comment where id=#{id}")
    Comment findById(@Param("id") int id);
    @Select("select * from comment where comment_user_id=#{comment_user_id}")
    List<Comment> findByUserId(@Param("comment_user_id") int comment_user_id);
}
