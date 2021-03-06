package www.juyun.net.gsjcommon.commonutils;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import www.juyun.net.gsjcommon.R;
import www.juyun.net.gsjcommon.baseapp.BaseApplication;


/**
 * Toast统一管理类
 */
public class ToastUitl {


    private static Toast toast;
    private static Toast toast2;

    private static Toast initToast(CharSequence message, int duration) {
        if (toast == null) {
            toast = Toast.makeText(BaseApplication.getAppContext(), message, duration);
        } else {
            toast.setText(message);
            toast.setDuration(duration);
        }
        return toast;
    }

    /**
     * 短时间显示Toast
     *
     * @param message
     */
    public static void showShort(CharSequence message) {
        initToast(message, Toast.LENGTH_SHORT).show();
    }


    /**
     * 短时间显示Toast
     *
     * @param strResId
     */
    public static void showShort(int strResId) {
//		Toast.makeText(context, strResId, Toast.LENGTH_SHORT).show();
        initToast(BaseApplication.getAppContext().getResources().getText(strResId), Toast.LENGTH_SHORT).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param message
     */
    public static void showLong(CharSequence message) {
        initToast(message, Toast.LENGTH_LONG).show();
    }

    /**
     * 长时间显示Toast
     *
     * @param strResId
     */
    public static void showLong(int strResId) {
        initToast(BaseApplication.getAppContext().getResources().getText(strResId), Toast.LENGTH_LONG).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param message
     * @param duration
     */
    public static void show(CharSequence message, int duration) {
        initToast(message, duration).show();
    }

    /**
     * 自定义显示Toast时间
     *
     * @param context
     * @param strResId
     * @param duration
     */
    public static void show(Context context, int strResId, int duration) {
        initToast(context.getResources().getText(strResId), duration).show();
    }

    /**
     * 显示有image的toast
     *
     * @param tvStr
     * @param imageResource
     * @return
     */
    public static Toast showToastWithImg(final String tvStr, final int imageResource) {
        if (toast2 == null) {
            toast2 = new Toast(BaseApplication.getAppContext());
        }
        View view = LayoutInflater.from(BaseApplication.getAppContext()).inflate(R.layout.toast_custom, null);
        TextView tv = (TextView) view.findViewById(R.id.toast_custom_tv);
        tv.setText(TextUtils.isEmpty(tvStr) ? "" : tvStr);
        ImageView iv = (ImageView) view.findViewById(R.id.toast_custom_iv);
        if (imageResource > 0) {
            iv.setVisibility(View.VISIBLE);
            iv.setImageResource(imageResource);
        } else {
            iv.setVisibility(View.GONE);
        }
        toast2.setView(view);
        toast2.setGravity(Gravity.CENTER, 0, 0);
        toast2.show();
        return toast2;

    }





    private static final int textLengthSign = 6;

    /**
     * 居中带图片的toast
     *
     * @param context     上下文
     * @param msg         消息
     * @param drawableRes 图片id
     */
    public static void showToastByPic(Context context, String msg, @DrawableRes int drawableRes) {
        Toast toast = makeText(context, msg);
        toast.setGravity(Gravity.CENTER, 0, 0);
        LinearLayout toastView = (LinearLayout) toast.getView();
        ImageView imageCodeProject = new ImageView(context);
        imageCodeProject.setImageResource(drawableRes);
        toastView.addView(imageCodeProject, 0);
        toast.show();
    }

    /**
     * 默认
     * @param context
     * @param msg
     */
    public static void showToastDefault(Context context, String msg) {
        Toast toast = makeText(context, msg);
        toast.show();
    }

    private static long lastShowTime = 0;
    private static String lastShowMessage = null;

    /**
     * 显示Toast，多个同样消息的Toast只显示一次
     *
     * @param context 上下文
     * @param msg     消息
     */
    public static void showToastOnce(Context context, String msg) {
        Toast toast;
        if (msg.equals(lastShowMessage)) {
            if (System.currentTimeMillis() - lastShowTime > 3000) {
                toast = makeText(context, msg);
                toast.show();
                lastShowTime = System.currentTimeMillis();
            }
        } else {
            showToastDefault(context, msg);
            lastShowMessage = msg;
            lastShowTime = System.currentTimeMillis();
        }
    }

    /**
     * 显示Toast，多个同样消息的Toast只显示一次
     *
     * @param context   上下文
     * @param stringRes 消息id
     */
    public static void showToastOnce(Context context, @StringRes int stringRes) {
        showToastOnce(context, context.getString(stringRes));
    }

    private static Toast makeText(Context context, String msg) {
        if(TextUtils.isEmpty(msg)) {
            return Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        } else if (msg.length() > textLengthSign) {
            return Toast.makeText(context, msg, Toast.LENGTH_LONG);
        } else {
            return Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        }
    }
}
