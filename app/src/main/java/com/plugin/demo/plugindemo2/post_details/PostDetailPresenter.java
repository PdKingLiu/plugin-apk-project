package com.plugin.demo.plugindemo2.post_details;

import android.app.Activity;


import com.plugin.demo.plugindemo2.bean.Post;

import java.util.List;

import com.plugin.demo.plugindemo2.post_details.bean.Comment;

/**
 * @author liupeidong
 * Created on 2019/10/5 15:03
 */
public class PostDetailPresenter implements PostDetailContract.Presenter {

    private PostDetailTasks tasks;

    private PostDetailContract.View view;

    public PostDetailPresenter(PostDetailContract.View view) {
        this.view = view;
        tasks = new PostDetailTasks();
    }

    @Override
    public void loadPostData(String postId, Activity activity, int width) {
        tasks.loadPostData(postId, activity, width, new PostDetailTasks.CallBack() {
            @Override
            public void contentText(CharSequence text) {
                view.loadContentText(text);
            }

            @Override
            public void succeed(Post post, boolean isAuthor) {
                view.loadDataSucceed(post, isAuthor);
            }

            @Override
            public void failure(String msg) {
                view.loadDataFailure(msg);
            }
        });
    }

    @Override
    public void sendComment(String content, String postId) {
        tasks.sendComment(content, postId, new PostDetailTasks.SendCommentCallBack() {
            @Override
            public void succeed(Comment comment) {
                view.sendCommentSucceed(comment);
            }

            @Override
            public void failure(String msg) {
                view.sendCommentFailure(msg);
            }
        });
    }

    @Override
    public void loadComment(String postId) {
        tasks.loadComment(postId, new PostDetailTasks.LoadCommentCallBack() {
            @Override
            public void succeed(List<Comment> commentList) {
                view.loadCommentSucceed(commentList);
            }

            @Override
            public void failure(String msg) {
                view.loadCommentFailure(msg);
            }
        });
    }

    @Override
    public void getIsPraiseAndSum(String postId) {
        tasks.getPraiseSumAndIsPraise(postId, (sum, is) -> view.loadPraiseSumAndIsPraise(sum, is));
    }


    @Override
    public void getIsCollectAndSum(String postId) {
        tasks.getCollectSumAndIsCollect(postId, (sum, is) -> view.loadCollectSumAndIsCollect(sum,
                is));
    }

    @Override
    public void sendPraiseMessage(String postId) {
        tasks.sendPraiseMessage(postId);
    }

    @Override
    public void sendCancelPraise(String postId) {
        tasks.sendCancelPraise(postId);
    }


    @Override
    public void sendCollectMessage(String postId) {
        tasks.sendCollectMessage(postId);
    }

    @Override
    public void sendCancelCollect(String postId) {
        tasks.sendCancelCollect(postId);
    }

}
