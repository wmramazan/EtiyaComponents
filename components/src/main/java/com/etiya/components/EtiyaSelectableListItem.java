package com.etiya.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.etiya.components.util.Utils;

/**
 * EtiyaSelectableListItem
 * This view is used to show one of items in a selection list.
 *
 * @author ramazan.vapurcu
 * Created on 10/19/2018
 */
public class EtiyaSelectableListItem extends EtiyaListItem {

    private ImageView icon;
    private boolean selected;

    public EtiyaSelectableListItem(Context context) {
        super(context);
    }

    public EtiyaSelectableListItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EtiyaSelectableListItem,
                0, 0
        );

        try {
            selected = a.getBoolean(R.styleable.EtiyaSelectableListItem_list_item_selected, false);
        } finally {
            a.recycle();
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        setIconVisibility();
    }

    /**
     * Initializes EtiyaSelectableListItem.
     */
    @Override
    protected void init() {
        super.init();

        icon = findViewById(R.id.components_list_item_icon);
        icon.setImageResource(R.drawable.components_ic_check);

        setIconVisibility();

        setOnClickListener(v -> {
            selected = !selected;
            setIconVisibility();
        });
    }

    /**
     * Sets the visibility of icon.
     */
    private void setIconVisibility() {
        icon.setVisibility(selected ? VISIBLE : INVISIBLE);
    }

    /**
     * Returns whether is selected.
     * @return boolean value
     */
    @Override
    public boolean isSelected() {
        return selected;
    }

    /**
     * Sets whether is selected.
     * @param selected boolean value
     */
    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
        setIconVisibility();
    }
}
