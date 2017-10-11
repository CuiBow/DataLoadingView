package com.example.mylibrary.view.type;

import android.support.annotation.IntDef;
import android.support.annotation.StringDef;

/**
 * Created by cuibowen on 2017/10/11.
 */

public class LoadingType {
    public static final int LOADING_DEFAULT=0;
    public static final int LOADING_DRAWABLE=1;
    public static final int LOADING_VIEW=2;

    @IntDef({LOADING_DEFAULT,LOADING_DRAWABLE,LOADING_VIEW})
    public @interface type{

    }
}
