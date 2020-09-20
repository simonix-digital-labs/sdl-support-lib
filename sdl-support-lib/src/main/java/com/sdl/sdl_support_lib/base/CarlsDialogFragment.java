package com.sdl.sdl_support_lib.base;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;


public abstract class CarlsDialogFragment<T extends CarlsCommunicator> extends DialogFragment {

    protected T communicator;

    @SuppressWarnings("unchecked")
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        communicator = (T) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return carlsBindView(inflater, container);
    }

    protected abstract View carlsBindView(LayoutInflater inflater, ViewGroup container);


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    protected int getColor(int color) {
        return getActivity().getColor(color);
    }



}
