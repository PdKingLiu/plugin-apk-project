package com.plugin.demo.plugindemo2.post_details.bean;


import com.plugin.demo.plugindemo2.bean.Post;
import com.plugin.demo.plugindemo2.bean.User;

import cn.bmob.v3.BmobObject;

/**
 * @author liupeidong
 * Created on 2019/10/5 23:44
 */
public class Comment extends BmobObject {

    private String Content;

    private User user;

    private Post post;

    public void setContent(String content) {
        Content = content;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getContent() {
        return Content;
    }

    public User getUser() {
        return user;
    }

    public Post getPost() {
        return post;
    }
}
