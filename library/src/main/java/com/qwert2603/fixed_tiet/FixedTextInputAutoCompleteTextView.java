package com.qwert2603.fixed_tiet;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputAutoCompleteTextView;

import java.lang.reflect.Field;

public class FixedTextInputAutoCompleteTextView extends TextInputAutoCompleteTextView {
    public FixedTextInputAutoCompleteTextView(Context context) {
        super(context);
    }

    public FixedTextInputAutoCompleteTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FixedTextInputAutoCompleteTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public CharSequence getHint() {
        String manufacturer = Build.MANUFACTURER.toUpperCase();
        if (!manufacturer.contains("MEIZU") || Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
            return super.getHint();
        } else {
            try {
                return getSuperHintHack();
            } catch (Exception e) {
                return super.getHint();
            }
        }
    }

    private CharSequence getSuperHintHack() throws NoSuchFieldException, IllegalAccessException {
        @SuppressWarnings("JavaReflectionMemberAccess")
        Field hintField = TextView.class.getDeclaredField("mHint");
        hintField.setAccessible(true);
        return (CharSequence) hintField.get(this);
    }
}
