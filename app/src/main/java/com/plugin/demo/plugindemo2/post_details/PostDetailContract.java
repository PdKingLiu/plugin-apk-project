package com.plugin.demo.plugindemo2.post_details;

import android.app.Activity;

import com.plugin.demo.plugindemo2.bean.Post;

import java.util.List;

import com.plugin.demo.plugindemo2.post_details.bean.Comment;

/**
 * @author liupeidong
 * Created on 2019/10/5 14:59
 */
public interface PostDetailContract {

    interface View extends BaseView {

        void loadDataSucceed(Post post, boolean isAuthor);

        void loadDataFailure(String msg);

        void loadContentText(CharSequence text);

        void sendCommentSucceed(Comment comment);

        void sendCommentFailure(String msg);

        void loadCommentSucceed(List<Comment> commentList);

        void loadCommentFailure(String msg);

        void loadPraiseSumAndIsPraise(int sum, boolean isPraise);

        void loadCollectSumAndIsCollect(int sum, boolean isCollect);

    }

    interface Presenter extends BasePresenter {

        void loadPostData(String postId, Activity activity, int width);

        void sendComment(String content, String postId);

        void loadComment(String postId);

        void getIsPraiseAndSum(String postId);

        void sendPraiseMessage(String postId);

        void sendCancelPraise(String postId);

        void getIsCollectAndSum(String postId);

        void sendCollectMessage(String postId);

        void sendCancelCollect(String postId);


    }

}
