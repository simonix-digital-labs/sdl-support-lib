package com.sdl.sdl_support_lib.base.fragment.broadcast;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.sdl.sdl_support_lib.base.fragment.CarlsFragmentActivity;
import com.sdl.sdl_support_lib.helper.BroadcastReceiverModel;
import com.sdl.sdl_support_lib.helper.CarlsLogger;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public abstract class CarlsBroadcastActivity extends CarlsFragmentActivity implements CarlsBroadcastCommunicator {

    private boolean regitrationAllowed = true;
    private Map<String, BroadcastReceiverModel> carlsBroadcastReceiverMap = new HashMap<>();
    private Set<String> carlsOnCreateList = new HashSet<>();
    private Set<String> carlsOnStartList = new HashSet<>();
    private Set<String> carlsOnResumeList = new HashSet<>();
    private Set<String> carlsOnPauseList = new HashSet<>();
    private Set<String> carlsOnStopList = new HashSet<>();
    private Set<String> carlsOnDestroyList = new HashSet<>();

    protected abstract void carlsIndexBroadcastReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        carlsIndexBroadcastReceiver();
        regitrationAllowed = false;
        carlsRegister(carlsOnCreateList);
    }

    @Override
    protected void onStart() {
        super.onStart();
        carlsRegister(carlsOnStartList);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carlsRegister(carlsOnResumeList);
    }

    @Override
    protected void onPause() {
        super.onPause();
        carlsUnregister(carlsOnPauseList);
    }

    @Override
    protected void onStop() {
        super.onStop();
        carlsUnregister(carlsOnStopList);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        carlsUnregister(carlsOnDestroyList);
        carlsBroadcastReceiverMap = null;
        carlsOnCreateList = null;
        carlsOnStartList = null;
        carlsOnResumeList = null;
        carlsOnPauseList = null;
        carlsOnStopList = null;
        carlsOnDestroyList = null;
    }

    protected void carls_registerLocalBroadcastReceiver(RegisterBroadcastAction register, UnregisterBroadcastAction unregister, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        String name = broadcastReceiver.getClass().getName();
        if (carlsCanRegister(name)) {
            carlsBroadcastReceiverMap.put(name, BroadcastReceiverModel.getInstance(broadcastReceiver, intentFilter, register, unregister, true));
            carlsRegister(register, broadcastReceiver, intentFilter, name);
            carlsUnregister(unregister, name);
            CarlsLogger.i("Registration Successful");
        }
    }

    protected void carlsRegisterBroadcastReceiver(RegisterBroadcastAction register, UnregisterBroadcastAction unregister, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        String name = broadcastReceiver.getClass().getName();
        if (carlsCanRegister(name)) {
            carlsBroadcastReceiverMap.put(name, BroadcastReceiverModel.getInstance(broadcastReceiver, intentFilter, register, unregister, false));
            carlsRegister(register, broadcastReceiver, intentFilter, name);
            carlsUnregister(unregister, name);
            CarlsLogger.i("Registration Successful");
        }
    }


    /*register and unregister*/
    protected void carlsRegisterLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(broadcastReceiver, intentFilter);
    }

    protected void carlsUnRegisterLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(broadcastReceiver);
    }

    protected void carlsRegisterBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        registerReceiver(broadcastReceiver, intentFilter);
    }

    protected void carlsUnRegisterBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        unregisterReceiver(broadcastReceiver);
    }

    private boolean carlsCanRegister(String name) {
        if (carlsBroadcastReceiverMap.containsKey(name)) {
            CarlsLogger.w("Broadcast receiver is already added, Please add action in intenet filter");
            return false;
        } else if (!regitrationAllowed) {
            CarlsLogger.w("Can't Register. Please add broadcast receiver on carls_indexBroadcastReceiver() method");
            return false;
        }
        return true;
    }

    private void carlsRegister(RegisterBroadcastAction register, BroadcastReceiver broadcastReceiver, IntentFilter intentFilter, String name) {
        switch (register) {
            case CARLS_ON_CREATE:
                carlsOnCreateList.add(name);
                break;
            case CARLS_ON_START:
                carlsOnStartList.add(name);
                break;
            case CARLS_ON_RESUME:
                carlsOnResumeList.add(name);
                break;
        }
    }

    private void carlsRegister(Set<String> receivers) {
        if (receivers.isEmpty())
            return;
        BroadcastReceiverModel broadcastReceiverModel;
        for (String receiver :
                receivers) {
            broadcastReceiverModel = carlsBroadcastReceiverMap.get(receiver);
            if (broadcastReceiverModel.isLocal())
                carlsRegisterLocalBroadcastReceiver(broadcastReceiverModel.getBroadcastReceiver(), broadcastReceiverModel.getIntentFilter());
            else
                carlsRegisterBroadcastReceiver(broadcastReceiverModel.getBroadcastReceiver(), broadcastReceiverModel.getIntentFilter());
        }
    }

    private void carlsUnregister(UnregisterBroadcastAction unregister, String name) {
        switch (unregister) {
            case CARLS_ON_PAUSE:
                carlsOnPauseList.add(name);
                break;
            case CARLS_ON_STOP:
                carlsOnStopList.add(name);
                break;
            case CARLS_ON_DESTROY:
                carlsOnDestroyList.add(name);
                break;
        }
    }

    private void carlsUnregister(Set<String> receivers) {
        if (receivers.isEmpty())
            return;
        BroadcastReceiverModel broadcastReceiverModel;
        for (String receiver :
                receivers) {
            broadcastReceiverModel = carlsBroadcastReceiverMap.get(receiver);
            if (broadcastReceiverModel.isLocal())
                carlsUnRegisterLocalBroadcastReceiver(broadcastReceiverModel.getBroadcastReceiver());
            else
                carlsUnRegisterBroadcastReceiver(broadcastReceiverModel.getBroadcastReceiver());
        }
    }

    public enum RegisterBroadcastAction {
        CARLS_ON_CREATE, CARLS_ON_START, CARLS_ON_RESUME
    }

    public enum UnregisterBroadcastAction {
        CARLS_ON_PAUSE, CARLS_ON_STOP, CARLS_ON_DESTROY
    }

    @Override
    public void frgRegisterLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        carlsRegisterLocalBroadcastReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    public void frgUnRegisterLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        carlsUnRegisterLocalBroadcastReceiver(broadcastReceiver);
    }

    @Override
    public void frgRegisterBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter) {
        carlsRegisterBroadcastReceiver(broadcastReceiver, intentFilter);

    }

    @Override
    public void frgUnRegisterBroadcastReceiver(BroadcastReceiver broadcastReceiver) {
        carlsUnRegisterBroadcastReceiver(broadcastReceiver);
    }
}
