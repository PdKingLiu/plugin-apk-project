package com.plugin.demo.plugindemo2.post_details;

/**
 * @author liupeidong
 * Created on 2019/9/8 10:11
 */
public interface BaseView<T> {

    public void setPresenter(T t);

    public void showLoading(String msg);

    public void hideLoading();

    public void showToast(String msg);

}
