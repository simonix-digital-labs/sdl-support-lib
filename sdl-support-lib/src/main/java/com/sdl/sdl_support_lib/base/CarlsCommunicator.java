package com.sdl.sdl_support_lib.base;


import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.util.Pair;

import java.util.List;

public interface CarlsCommunicator {

    /*activity*/
    void frgStartActivity(Class<? extends CarlsActivity> activityClass);

    void frgStartActivity(Class<? extends CarlsActivity> activityClass,
                          Bundle bundle);

    void frgStartActivity(Class<? extends CarlsActivity> activityClass,
                          Bundle bundle,
                          int flags);

    void frgStartActivityForResult(Class<? extends CarlsActivity> activityClass,
                                   Bundle bundle,
                                   int flags, int requestCode);

    void frgStartActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, Pair<View, String> pair);
    void frgStartActivity(Class<? extends CarlsActivity> activityClass, Pair<View, String> pair);
    void frgStartActivityForResult(Class<? extends CarlsActivity> activityClass, Bundle bundle, Pair<View, String> pair, int requestCode);
    void frgStartActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, Pair<View, String>[] pair);
    void frgStartActivity(Class<? extends CarlsActivity> activityClass, Pair<View, String>[] pair);
    void frgStartActivityForResult(Class<? extends CarlsActivity> activityClass, Bundle bundle, Pair<View, String>[] pair, int requestCode);

    /*toast*/
    void frgShowToast(String msg);

    void frgShowToast(int msg);

    /*list dialog*/
    void frgShowListDialog(int items, DialogInterface.OnClickListener listener);

    void frgShowListDialog(int style, int items, DialogInterface.OnClickListener listener);

    void frgShowListDialog(int style, boolean isCancelable, int title, int items, DialogInterface.OnClickListener listener);

    <T> void frgShowListDialog(List<T> items, DialogInterface.OnClickListener listener);

    <T> void frgShowListDialog(int style, List<T> items, DialogInterface.OnClickListener listener);

    <T> void frgShowListDialog(int style, int resource, boolean isCancelable, int title, List<T> items, DialogInterface.OnClickListener listener);

    /*dialog*/
    void frgShowDialog(boolean isCancelable,
                       int title,
                       int message,
                       int postiveTextId,
                       DialogInterface.OnClickListener positiveListener,
                       int negativeTextId,
                       DialogInterface.OnClickListener negativeListener);

    void frgShowDialog(int style,
                       boolean isCancelable,
                       int title,
                       int message,
                       int postiveTextId,
                       DialogInterface.OnClickListener positiveListener,
                       int negativeTextId,
                       DialogInterface.OnClickListener negativeListener);

    /*confirm*/
    void frgShowConfirmDialog(int message, int textId, DialogInterface.OnClickListener listener);

    void frgShowConfirmDialog(int message, DialogInterface.OnClickListener listener);

    void frgShowConfirmDialog(int style, int message, int textId, DialogInterface.OnClickListener listener);

    void frgShowConfirmDialog_(int style, int message, DialogInterface.OnClickListener listener);

    void frgShowConfirmDialog(String message, int textId, DialogInterface.OnClickListener listener);

    void frgShowConfirmDialog(String message, DialogInterface.OnClickListener listener);

    void frgShowConfirmDialog(int style, String message, int textId, DialogInterface.OnClickListener listener);

    void frgShowConfirmDialog_(int style, String message, DialogInterface.OnClickListener listener);

    /*alert*/
    void frgShowAlert(int title, int message);

    void frgShowAlert(int message);

    void frgShowAlert(int style, int title, int message);

    void frgShowAlert_(int style, int message);

    void frgShowAlert(int title, String message);

    void frgShowAlert(String message);

    void frgShowAlert(int style, int title, String message);

    void frgShowAlert_(int style, String message);

    /*fragment*/
    void frgShowDialogFragment(@NonNull CarlsDialogFragment fragment, String tag);

    void frgShowDialogFragment(@NonNull CarlsDialogFragment fragment);

    void setFragmentTitle(String title);

    void setFragmentTitle(int title);
}