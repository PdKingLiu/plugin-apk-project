package com.plugin.demo.plugindemo2.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.plugin.demo.plugindemo2.R;

/**
 * @author liupeidong
 * Created on 2019/9/8 16:11
 */
public class ToastUtils {

    public static void showToast(Context context, String msg) {
        if (context != null) {
            Toast toast = new Toast(context);
            View view = LayoutInflater.from(context).inflate(R.layout.layout_my_toast, null);
            ((TextView) view.findViewById(R.id.tv_toast)).setText(msg);
            toast.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM, 0, 80);
            toast.setDuration(Toast.LENGTH_SHORT);
            toast.setView(view);
            toast.show();
        }
    }

}
