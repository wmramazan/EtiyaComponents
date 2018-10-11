package com.etiya.components;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatTextView;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * EtiyaMenuItemView
 * This view is used to show menu item.
 *
 * @author ramazan.vapurcu
 * Created on 10/2/2018
 */
public class EtiyaMenuItemView extends AppCompatTextView {

    private boolean selected;

    public EtiyaMenuItemView(Context context) {
        super(context);

        init();
    }

    public EtiyaMenuItemView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    /**
     * Initializes EtiyaMenuItemView.
     */
    protected void init() {
        setTextSize(getResources().getDimensionPixelSize(R.dimen.components_menu_item_text_size));
        setTypeface(getTypeface(), Typeface.BOLD);
        setTextColor();
        setEllipsize(TextUtils.TruncateAt.END);
    }

    /**
     * Sets text color by checking selection.
     */
    protected void setTextColor() {
        setTextColor(ContextCompat.getColor(getContext(), selected ? R.color.components_primary : R.color.components_black));
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
