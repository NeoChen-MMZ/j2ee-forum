package com.buaa.forum.bean;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public class Comment {
    private int id;
    private String content;
    private int comment_user_id;
    private User comment_user;
    private int comment_topic_id;
    private int comment_reply_id = 0;
    private String comment_reply_content;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date comment_time;
    private int floor;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getComment_user_id() {
        return comment_user_id;
    }

    public void setComment_user_id(int comment_user_id) {
        this.comment_user_id = comment_user_id;
    }

    public User getComment_user() {
        return comment_user;
    }

    public void setComment_user(User comment_user) {
        this.comment_user = comment_user;
    }

    public int getComment_topic_id() {
        return comment_topic_id;
    }

    public void setComment_topic_id(int comment_topic_id) {
        this.comment_topic_id = comment_topic_id;
    }

    public int getComment_reply_id() {
        return comment_reply_id;
    }

    public void setComment_reply_id(int comment_reply_id) {
        this.comment_reply_id = comment_reply_id;
    }

    public Date getComment_time() {
        return comment_time;
    }

    public void setComment_time(Date comment_time) {
        this.comment_time = comment_time;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getComment_reply_content() {
        return comment_reply_content;
    }

    public void setComment_reply_content(String comment_reply_content) {
        this.comment_reply_content = comment_reply_content;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", comment_user_id=" + comment_user_id +
                ", comment_user=" + comment_user +
                ", comment_topic_id=" + comment_topic_id +
                ", comment_reply_id=" + comment_reply_id +
                ", comment_reply_content='" + comment_reply_content + '\'' +
                ", comment_time=" + comment_time +
                ", floor=" + floor +
                '}';
    }

}
