package com.buaa.forum.bean;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class Topic {
    private int id;
    private String title;
    private String content;
    private int comment_count = 0;
    private int status = 0;
    private int topic_user_id;
    private User topic_user;
    private String topic_category;
    private int browse_count = 0;
    private int thumbs_up = 0;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date topic_time;

    public Date getTopic_time() {
        return topic_time;
    }

    public void setTopic_time(Date topic_time) {
        this.topic_time = topic_time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getComment_count() {
        return comment_count;
    }

    public void setComment_count(int comment_count) {
        this.comment_count = comment_count;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTopic_user_id() {
        return topic_user_id;
    }

    public void setTopic_user_id(int topic_user_id) {
        this.topic_user_id = topic_user_id;
    }

    public String getTopic_category() {
        return topic_category;
    }

    public void setTopic_category(String topic_category) {
        this.topic_category = topic_category;
    }

    public int getBrowse_count() {
        return browse_count;
    }

    public void setBrowse_count(int browse_count) {
        this.browse_count = browse_count;
    }

    public int getThumbs_up() {
        return thumbs_up;
    }

    public void setThumbs_up(int thumbs_up) {
        this.thumbs_up = thumbs_up;
    }

    public User getTopic_user() {
        return topic_user;
    }

    public void setTopic_user(User topic_user) {
        this.topic_user = topic_user;
    }

    @Override
    public String toString() {
        return "Topic{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", comment_count=" + comment_count +
                ", status=" + status +
                ", topic_user_id=" + topic_user_id +
                ", topic_user=" + topic_user +
                ", topic_category='" + topic_category + '\'' +
                ", browse_count=" + browse_count +
                ", thumbs_up=" + thumbs_up +
                ", topic_time=" + topic_time +
                '}';
    }
}
