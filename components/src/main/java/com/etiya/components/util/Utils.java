package com.etiya.components.util;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.StyleableRes;
import android.util.TypedValue;

public class Utils {

    public static String getStringFromAttrs(Resources resources, TypedArray a, @StyleableRes int index) {
        TypedValue value = new TypedValue();
        a.getValue(index, value);

        if (value.type == TypedValue.TYPE_NULL)
            return null;

        return value.type == TypedValue.TYPE_REFERENCE ? resources.getString(value.data) : value.string.toString();
    }

    public static int getColorFromAttrs(Resources resources, TypedValue a, @StyleableRes int index) {
        return 0;
    }

}
