package com.sdl.sdl_support_lib.base.fragment.broadcast;


import android.content.BroadcastReceiver;
import android.content.IntentFilter;

import com.sdl.sdl_support_lib.base.fragment.CarlsFragmentCommunicator;


public interface CarlsBroadcastCommunicator extends CarlsFragmentCommunicator {
    void frgRegisterLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    void frgUnRegisterLocalBroadcastReceiver(BroadcastReceiver broadcastReceiver);

    void frgRegisterBroadcastReceiver(BroadcastReceiver broadcastReceiver, IntentFilter intentFilter);

    void frgUnRegisterBroadcastReceiver(BroadcastReceiver broadcastReceiver);
}