package com.plugin.demo.plugindemo2.loadingview;

import android.app.Activity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import com.mingle.widget.LoadingView;
import com.plugin.demo.plugindemo2.R;

/**
 * @author liupeidong
 * Created on 2019/9/7 17:45
 */
public class LoadingViewUtils {

    private PopupWindow mPopupWindow;
    private Activity mActivity;

    public LoadingViewUtils(Activity activity, String msg) {
        mActivity = activity;
        if (mActivity != null) {
            View view =
                    LayoutInflater.from(mActivity).inflate(R.layout.layout_popwindow_loading_view,
                    null);
            ((LoadingView) view.findViewById(R.id.loadView)).setLoadingText(msg);
            mPopupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT, true);
        }
    }

    public boolean isShowing() {
        if (mPopupWindow != null) {
            return mPopupWindow.isShowing();
        } else {
            return false;
        }
    }

    public void show() {
        if (mPopupWindow != null && mActivity != null) {
            mPopupWindow.showAtLocation(mActivity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);
        }
    }

    public void dismiss() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }
}
