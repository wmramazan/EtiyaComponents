package com.etiya.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;

import com.etiya.components.util.Utils;

/**
 * EtiyaTabItem
 * This view is used to show one tab.
 *
 * @author ramazan.vapurcu
 * Created on 10/25/2018
 */
public class EtiyaTabItem extends AppCompatTextView {

    protected int layoutId;

    public EtiyaTabItem(Context context) {
        super(context);

        init();
    }

    public EtiyaTabItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = getContext().getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EtiyaTabItem,
                0, 0
        );

        try {
            layoutId = a.getResourceId(R.styleable.EtiyaTabItem_components_tab_item_layout_id, 0);
            setSelected(a.getBoolean(R.styleable.EtiyaTabItem_components_tab_item_selected, false));
        } finally {
            a.recycle();
        }

        init();
    }

    /**
     * Initializes EtiyaTabItem.
     */
    protected void init() {
        setTextColor(ContextCompat.getColor(getContext(), R.color.components_dark_gray));
        setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.components_tab_item_text_size));
        setGravity(Gravity.CENTER);

        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.components_tab_item));
        setPadding(getResources().getDimensionPixelSize(R.dimen.components_tab_item_padding));
    }

    /**
     * Sets padding.
     * @param value integer value
     */
    public void setPadding(int value) {
        setPadding(value, value, value, value);
    }

    /**
     * Returns identifier of the related layout.
     * @return integer value
     */
    public int getLayoutId() {
        return layoutId;
    }

    /**
     * Returns whether has identifier of the related layout.
     * @return boolean value
     */
    public boolean hasLayoutId() {
        return layoutId != 0;
    }

    /**
     * Sets identifier of the related layout.
     * @param layoutId integer value
     */
    public void setLayoutId(int layoutId) {
        this.layoutId = layoutId;
    }

    /**
     * Sets selected value and changes background of tab.
     * @param selected boolean value
     */
    @Override
    public void setSelected(boolean selected) {
        super.setSelected(selected);
        setBackground(ContextCompat.getDrawable(getContext(), selected ? R.drawable.components_tab_item_pressed : R.drawable.components_tab_item));
    }
}
