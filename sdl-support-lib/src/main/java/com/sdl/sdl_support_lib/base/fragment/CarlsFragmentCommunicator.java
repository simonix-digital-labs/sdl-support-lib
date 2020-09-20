package com.sdl.sdl_support_lib.base.fragment;


import androidx.annotation.NonNull;

import com.sdl.sdl_support_lib.base.CarlsCommunicator;
import com.sdl.sdl_support_lib.base.CarlsFragment;

public interface CarlsFragmentCommunicator extends CarlsCommunicator {
    /*fragments*/
    void frgReplaceFragment(@NonNull CarlsFragment fragment);

    void frgReplaceFragment(@NonNull CarlsFragment fragment, String tag);

    void frgAddFragment(@NonNull CarlsFragment fragment, String tag);

    void frgAddFragment(@NonNull CarlsFragment fragment);
}