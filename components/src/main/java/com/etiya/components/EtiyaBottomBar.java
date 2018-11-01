package com.etiya.components;

import android.content.Context;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

/**
 * EtiyaBottomBar
 * This view is used to show bottom bar in a layout.
 *
 * @author ramazan.vapurcu
 * Created on 10/26/2018
 */
public class EtiyaBottomBar extends RelativeLayout {

    protected final String  DEBUG_TAG = getClass().getName();
    protected final float   DEFAULT_ELEVATION = .9f;
    protected final float   DEFAULT_TRANSLATION_Z = 50f;

    public EtiyaBottomBar(Context context) {
        super(context);

        init();
    }

    public EtiyaBottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    /**
     * Initializes EtiyaBottomBar.
     */
    protected void init() {
        setPadding(getResources().getDimensionPixelSize(R.dimen.components_padding));
        setBackgroundColor(ContextCompat.getColor(getContext(), android.R.color.white));

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            setElevation(DEFAULT_ELEVATION);
            setTranslationZ(DEFAULT_TRANSLATION_Z);
        }
    }

    /**
     * Sets padding.
     * @param value integer value
     */
    public void setPadding(int value) {
        setPadding(value, value, value, value);
    }
}
