package com.etiya.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.TypedValue;

import com.etiya.components.util.Utils;

/**
 * EtiyaMenuItem
 * This view is used to show menu item.
 *
 * @author ramazan.vapurcu
 * Created on 10/2/2018
 */
public class EtiyaMenuItem extends AppCompatTextView {

    private boolean selected;

    private String name;
    private int text_color, selected_color;

    public EtiyaMenuItem(Context context) {
        super(context);
        this.text_color = R.color.components_black;
        this.selected_color = R.color.components_indigo;

        init();
    }

    public EtiyaMenuItem(Context context, String name, @ColorInt int text_color, @ColorInt int selected_color) {
        super(context);
        this.name = name;
        this.text_color = text_color;
        this.selected_color = selected_color;

        init();
    }

    public EtiyaMenuItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EtiyaMenuItem,
                0, 0
        );

        try {
            name = Utils.getStringFromAttrs(getResources(), a, R.styleable.EtiyaMenuItem_android_text);
            text_color = a.getInt(R.styleable.EtiyaMenuItem_android_textColor, R.color.components_black);
            selected_color = a.getInt(R.styleable.EtiyaMenuItem_android_textColor, R.color.components_black);
        } finally {
            a.recycle();
        }

        init();
    }

    /**
     * Initializes EtiyaMenuItem.
     */
    protected void init() {
        setText(name);
        setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.components_menu_item_text_size));
        setTypeface(getTypeface(), Typeface.BOLD);
        setTextColor();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * Sets text color by checking selection.
     */
    protected void setTextColor() {
        setTextColor(ContextCompat.getColor(getContext(), selected ? selected_color : text_color));
    }

    /**
     * Returns whether is selected.
     * @return selected boolean
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets selected value.
     * @param selected boolean
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
        setTextColor();
    }

    /**
     * Changes selected value.
     */
    public void toggleSelected() {
        setSelected(!selected);
    }
}
