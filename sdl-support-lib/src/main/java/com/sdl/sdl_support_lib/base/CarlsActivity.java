package com.sdl.sdl_support_lib.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.util.Pair;
import androidx.fragment.app.FragmentTransaction;

import com.sdl.sdl_support_lib.R;
import com.sdl.sdl_support_lib.helper.ToastMessage;

import java.util.List;

import static com.sdl.sdl_support_lib.helper.CarlsConstant.CARLS_NONE;


public abstract class CarlsActivity extends AppCompatActivity implements CarlsCommunicator {
    protected SharedPreferences defaultPreference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carlsConfigure(savedInstanceState);
        carlsBindViews();
        defaultPreference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
    }



    protected void carlsConfigure(@Nullable Bundle savedInstanceState) {

    }

    protected abstract void carlsBindViews();

    /*activities*/
    protected Intent carlsBuildIntent(Class<? extends CarlsActivity> activityClass) {
        return new Intent(this, activityClass);
    }

    protected void carlsStartActivity(Class<? extends CarlsActivity> activityClass) {
        carlsStartActivity(activityClass, null, CARLS_NONE);
    }

    protected void carlsStartActivity(Class<? extends CarlsActivity> activityClass, @Nullable Bundle bundle) {
        carlsStartActivity(activityClass, bundle, CARLS_NONE);
    }

    protected void carlsStartActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags, Pair<View, String>[] pairs) {
        Intent intent = new Intent(this, activityClass);
        if (bundle != null)
            intent.putExtras(bundle);
        if (flags != CARLS_NONE)
            intent.setFlags(flags);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, pairs);
        startActivity(intent, options.toBundle());
    }

    protected void carlsStartActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags, Pair<View, String> pair) {
        Intent intent = new Intent(this, activityClass);
        if (flags != CARLS_NONE)
            intent.setFlags(flags);
        if (bundle != null)
            intent.putExtras(bundle);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, pair);
        startActivity(intent, options.toBundle());
    }

    protected void carlsStartActivityForResult(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags, Pair<View, String>[] pairs, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        if (bundle != null)
            intent.putExtras(bundle);
        if (flags != CARLS_NONE)
            intent.setFlags(flags);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, pairs);
        startActivityForResult(intent, requestCode, options.toBundle());
    }

    protected void carlsStartActivityForResult(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags, Pair<View, String> pair, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        if (bundle != null)
            intent.putExtras(bundle);
        if (flags != CARLS_NONE)
            intent.setFlags(flags);
        ActivityOptionsCompat options = ActivityOptionsCompat.
                makeSceneTransitionAnimation(this, pair);
        startActivityForResult(intent, requestCode, options.toBundle());
    }

    protected void carlsStartActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags) {
        Intent intent = new Intent(this, activityClass);
        if (flags != CARLS_NONE)
            intent.setFlags(flags);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivity(intent);
    }

    protected void carlsStartActivityForResult(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags, int requestCode) {
        Intent intent = new Intent(this, activityClass);
        if (flags != CARLS_NONE)
            intent.setFlags(flags);
        if (bundle != null)
            intent.putExtras(bundle);
        startActivityForResult(intent,requestCode);
    }

    /*fragments*/
    protected void carlsReplaceFragment(@IdRes int containerViewId, @NonNull CarlsFragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment).commit();
    }

    protected void carlsReplaceFragment(@IdRes int containerViewId, @NonNull CarlsFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().replace(containerViewId, fragment, tag).addToBackStack(tag).commit();
    }

    protected void carlsAddFragment(@IdRes int containerViewId, @NonNull CarlsFragment fragment, String tag) {
        getSupportFragmentManager().beginTransaction().add(containerViewId, fragment).addToBackStack(tag).commit();
    }

    protected void carlsAddFragment(@IdRes int containerViewId, @NonNull CarlsFragment fragment) {
        getSupportFragmentManager().beginTransaction().add(containerViewId, fragment).commit();
    }

    protected void carlsShowDialogFragment(@NonNull CarlsDialogFragment fragment, String tag) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(tag);
        fragment.show(ft, tag);
    }

    protected void carlsShowDialogFragment(@NonNull CarlsDialogFragment fragment) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.addToBackStack(fragment.getClass().getName());
        fragment.show(ft, fragment.getClass().getName());
    }

    /*without style*/
    protected void carlsShowDialog(boolean isCancelable, int title, int message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carlsShowDialog(CARLS_NONE, isCancelable, title, message, postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    protected void carlsShowDialog(boolean isCancelable, int title, String message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carlsShowDialog(CARLS_NONE, isCancelable, title, message, postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    /*with style*/
    protected void carlsShowDialog(int style, boolean isCancelable, int title, int message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carlsShowDialog(style, isCancelable, title, getString(message), postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    protected void carlsShowDialog(int style, boolean isCancelable, int title, String message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        new AlertDialog.Builder(this, style == CARLS_NONE ? R.style.Carls_Appearance_App_Dialog : style)
                .setCancelable(isCancelable)
                .setTitle(title == CARLS_NONE ? R.string.carls_alert : title)
                .setMessage(message)
                .setNegativeButton(negativeTextId, negativeListener)
                .setPositiveButton(postiveTextId, positiveListener)
                .create().show();
    }


    /*Confirm Dialog*/
    protected void carlsShowConfirmDialog(int message, int textId, DialogInterface.OnClickListener listener) {
        carlsShowDialog(false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    protected void carlsShowConfirmDialog(int message, DialogInterface.OnClickListener listener) {
        carlsShowDialog(false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    protected void carlsShowConfirmDialog(int style, int message, int textId, DialogInterface.OnClickListener listener) {
        carlsShowDialog(style, false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    protected void carlsShowConfirmDialog_(int style, int message, DialogInterface.OnClickListener listener) {
        carlsShowDialog(style, false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    protected void carlsShowConfirmDialog(String message, int textId, DialogInterface.OnClickListener listener) {
        carlsShowDialog(false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    protected void carlsShowConfirmDialog(String message, DialogInterface.OnClickListener listener) {
        carlsShowDialog(false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    protected void carlsShowConfirmDialog(int style, String message, int textId, DialogInterface.OnClickListener listener) {
        carlsShowDialog(style, false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    protected void carlsShowConfirmDialog_(int style, String message, DialogInterface.OnClickListener listener) {
        carlsShowDialog(style, false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    /*alerts*/
    protected void carlsShowAlert(int title, int message) {
        carlsShowDialog(false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carlsShowAlert(int message) {
        carlsShowDialog(false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carlsShowAlert(int style, int title, int message) {
        carlsShowDialog(style, false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carlsShowAlert_(int style, int message) {
        carlsShowDialog(style, false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carlsShowAlert(int title, String message) {
        carlsShowDialog(false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carlsShowAlert(String message) {
        carlsShowDialog(false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carlsShowAlert(int style, int title, String message) {
        carlsShowDialog(style, false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    protected void carlsShowAlert_(int style, String message) {
        carlsShowDialog(style, false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    /*list dialog*/
    protected void carlsShowListDialog(int items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(CARLS_NONE, true, CARLS_NONE, items, listener);
    }

    protected void carlsShowListDialog(int style, int items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(style, true, CARLS_NONE, items, listener);
    }

    protected <T> void carlsShowListDialog(List<T> items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(CARLS_NONE, CARLS_NONE, true, CARLS_NONE, items, listener);
    }

    protected <T> void carlsShowListDialog(int style, List<T> items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(style, CARLS_NONE, true, CARLS_NONE, items, listener);
    }

    protected <T> void carlsShowListDialog(int style, int resource, boolean isCancelable, int title, List<T> items, DialogInterface.OnClickListener listener) {
        final ArrayAdapter<T> arrayAdapter = new ArrayAdapter<>(this, resource == CARLS_NONE ? R.layout.carls_textview : resource, items);
        new AlertDialog.Builder(this, style == CARLS_NONE ? R.style.Carls_Appearance_App_Dialog : style)
                .setCancelable(isCancelable)
                .setTitle(title == CARLS_NONE ? R.string.carls_select : title)
                .setAdapter(arrayAdapter, listener)
                .create().show();
    }

    protected void carlsShowListDialog(int style, boolean isCancelable, int title, int items, DialogInterface.OnClickListener listener) {
        new AlertDialog.Builder(this, style == CARLS_NONE ? R.style.Carls_Appearance_App_Dialog : style)
                .setCancelable(isCancelable)
                .setTitle(title == CARLS_NONE ? R.string.carls_select : title)
                .setItems(items, listener)
                .create().show();
    }

    /*toast*/
    protected void carlsShowToast(String msg) {
        ToastMessage.makeSimpleToast(getApplicationContext(), msg);
    }

    protected void carlsShowToast(int msg) {
        carlsShowToast(getString(msg));
    }


    @Override
    public void frgStartActivity(Class<? extends CarlsActivity> activityClass) {
        carlsStartActivity(activityClass);
    }

    @Override
    public void frgStartActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle) {
        carlsStartActivity(activityClass, bundle);
    }

    @Override
    public void frgStartActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags) {
        carlsStartActivity(activityClass, bundle, flags);
    }

    @Override
    public void frgStartActivityForResult(Class<? extends CarlsActivity> activityClass, Bundle bundle, int flags, int requestCode) {
        carlsStartActivityForResult(activityClass, bundle,flags,requestCode);
    }

    @Override
    public void frgStartActivity(Class<? extends CarlsActivity> activityClass, Pair<View, String> pair) {
        carlsStartActivity(activityClass,null, CARLS_NONE, pair);
    }

    @Override
    public void frgStartActivity(Class<? extends CarlsActivity> activityClass, Pair<View, String>[] pair) {
        carlsStartActivity(activityClass,null, CARLS_NONE, pair);
    }

    @Override
    public void frgStartActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, Pair<View, String>[] pair) {
        carlsStartActivity(activityClass,bundle, CARLS_NONE, pair);
    }

    @Override
    public void frgStartActivity(Class<? extends CarlsActivity> activityClass, Bundle bundle, Pair<View, String> pair) {
        carlsStartActivity(activityClass,bundle, CARLS_NONE, pair);
    }

    @Override
    public void frgStartActivityForResult(Class<? extends CarlsActivity> activityClass, Bundle bundle, Pair<View, String> pair, int requestCode) {
        carlsStartActivityForResult(activityClass,bundle, CARLS_NONE, pair,requestCode);
    }

    @Override
    public void frgStartActivityForResult(Class<? extends CarlsActivity> activityClass, Bundle bundle, Pair<View, String>[] pair, int requestCode) {
        carlsStartActivityForResult(activityClass,bundle, CARLS_NONE, pair,requestCode);
    }

    @Override
    public void frgShowToast(String msg) {
        carlsShowToast(msg);
    }

    @Override
    public void frgShowToast(int msg) {
        carlsShowToast(msg);
    }

    @Override
    public void frgShowListDialog(int items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(items, listener);
    }

    @Override
    public void frgShowListDialog(int style, int items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(style, items, listener);
    }

    @Override
    public <T> void frgShowListDialog(List<T> items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(items, listener);
    }

    @Override
    public <T> void frgShowListDialog(int style, List<T> items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(style, items, listener);
    }

    @Override
    public <T> void frgShowListDialog(int style, int resource, boolean isCancelable, int title, List<T> items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(style, resource, isCancelable, title, items, listener);
    }

    @Override
    public void frgShowListDialog(int style, boolean isCancelable, int title, int items, DialogInterface.OnClickListener listener) {
        carlsShowListDialog(style, isCancelable, title, items, listener);
    }

    @Override
    public void frgShowDialog(boolean isCancelable, int title, int message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carlsShowDialog(CARLS_NONE, isCancelable, title, getString(message), postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    @Override
    public void frgShowDialog(int style, boolean isCancelable, int title, int message, int postiveTextId, DialogInterface.OnClickListener positiveListener, int negativeTextId, DialogInterface.OnClickListener negativeListener) {
        carlsShowDialog(style, isCancelable, title, getString(message), postiveTextId, positiveListener, negativeTextId, negativeListener);
    }

    @Override
    public void frgShowConfirmDialog(int message, int textId, DialogInterface.OnClickListener listener) {
        carlsShowDialog(false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowConfirmDialog(int message, DialogInterface.OnClickListener listener) {
        carlsShowDialog(false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowConfirmDialog(int style, int message, int textId, DialogInterface.OnClickListener listener) {
        carlsShowDialog(style, false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowConfirmDialog_(int style, int message, DialogInterface.OnClickListener listener) {
        carlsShowDialog(style, false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowConfirmDialog(String message, int textId, DialogInterface.OnClickListener listener) {
        carlsShowDialog(false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowConfirmDialog(String message, DialogInterface.OnClickListener listener) {
        carlsShowDialog(false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowConfirmDialog(int style, String message, int textId, DialogInterface.OnClickListener listener) {
        carlsShowDialog(style, false, R.string.carls_confirm, message, textId, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowConfirmDialog_(int style, String message, DialogInterface.OnClickListener listener) {
        carlsShowDialog(style, false, R.string.carls_confirm, message, R.string.carls_confirm, listener, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowAlert(int title, int message) {
        carlsShowDialog(false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowAlert(int message) {
        carlsShowDialog(false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowAlert(int style, int title, int message) {
        carlsShowDialog(style, false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowAlert_(int style, int message) {
        carlsShowDialog(style, false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowAlert(int title, String message) {
        carlsShowDialog(false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowAlert(String message) {
        carlsShowDialog(false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowAlert(int style, int title, String message) {
        carlsShowDialog(style, false, title, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowAlert_(int style, String message) {
        carlsShowDialog(style, false, R.string.carls_alert, message, R.string.carls_okay, null, R.string.carls_cancel, null);
    }

    @Override
    public void frgShowDialogFragment(@NonNull CarlsDialogFragment fragment, String tag) {
        carlsShowDialogFragment(fragment, tag);
    }

    @Override
    public void frgShowDialogFragment(@NonNull CarlsDialogFragment fragment) {
        carlsShowDialogFragment(fragment);
    }

    @Override
    public void setFragmentTitle(String title) {

    }

    @Override
    public void setFragmentTitle(int title) {

    }

}
