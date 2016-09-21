package com.andrejp.apchipsedittext;

import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by Andrej Poljanec on 9/21/2016.
 */
public class Utils {

    public static LinearLayout.LayoutParams getMPHorizontalWCVerticalParams() {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    public static LinearLayout.LayoutParams getWCParams() {
        return new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

}
