package com.sdl.sdl_support_lib.base.fragment;

import androidx.annotation.NonNull;

import com.sdl.sdl_support_lib.base.CarlsActivity;
import com.sdl.sdl_support_lib.base.CarlsFragment;


public abstract class CarlsFragmentActivity extends CarlsActivity implements CarlsFragmentCommunicator {

    @Override
    public void frgReplaceFragment(@NonNull CarlsFragment fragment) {
        carlsReplaceFragment(carlsGetContainerViewId(fragment.getId()), fragment);
    }

    protected abstract int carlsGetContainerViewId(int id);

    @Override
    public void frgReplaceFragment(@NonNull CarlsFragment fragment, String tag) {
        carlsReplaceFragment(carlsGetContainerViewId(fragment.getId()), fragment, tag);
    }

    @Override
    public void frgAddFragment(@NonNull CarlsFragment fragment, String tag) {
        carlsAddFragment(carlsGetContainerViewId(fragment.getId()), fragment, tag);
    }

    @Override
    public void frgAddFragment(@NonNull CarlsFragment fragment) {
        carlsAddFragment(carlsGetContainerViewId(fragment.getId()), fragment);
    }
}
