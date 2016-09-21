package com.andrejp.apchipsedittext;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by Andrej Poljanec on 9/12/2016.
 */
public class StringChipsTextInputLayout extends ChipsTextInputLayout<String> {

    public StringChipsTextInputLayout(Context context) {
        super(context);
    }

    public StringChipsTextInputLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public StringChipsTextInputLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected String getItemFromFilter() {
        EditText editText = getEditText();
        if (editText == null) {
            return null;
        } else {
            return editText.getText().toString();
        }
    }
}
