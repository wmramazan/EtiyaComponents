package com.etiya.components;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

/**
 * EtiyaSelectableView
 * This view is used to make view(s) selectable.
 *
 * @author ramazan.vapurcu
 * Created on 9/4/2018
 */
public class EtiyaSelectableView extends RelativeLayout {

    protected final String DEBUG_TAG = getClass().getName();

    protected ImageView ivImage;
    protected RelativeLayout parent;

    protected boolean selected;
    protected boolean enabled;
    protected int id;

    public EtiyaSelectableView(Context context) {
        super(context);
        this.enabled = true;

        init();
    }

    public EtiyaSelectableView(Context context, boolean enabled) {
        super(context);
        this.enabled = enabled;

        init();
    }

    public EtiyaSelectableView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EtiyaSelectableView,
                0, 0
        );

        try {
            selected = a.getBoolean(R.styleable.EtiyaSelectableView_selected, false);
            enabled = a.getBoolean(R.styleable.EtiyaSelectableView_enabled, true);
        } finally {
            a.recycle();
        }

        init();
    }

    /**
     * Initializes EtiyaSelectableView.
     */
    protected void init() {
        ivImage = new ImageView(getContext());
        ivImage.setImageResource(R.drawable.components_ic_check);
        DrawableCompat.setTint(ivImage.getDrawable(), ContextCompat.getColor(getContext(), R.color.components_blue));

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
        ivImage.setLayoutParams(params);

        addView(ivImage);

        parent = new RelativeLayout(getContext());
        setPadding(getResources().getDimensionPixelSize(R.dimen.components_selectable_view_padding));

        addView(parent);

        this.setEnabled(enabled);
        setSelection();
    }

    /**
     * Adds view to parent view.
     * @param child View
     * @param params LayoutParams
     */
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        Log.d(DEBUG_TAG, "View has been added.");
        parent.addView(child, params);
    }

    /**
     * Sets padding to parent view.
     * @param left integer value
     * @param top integer value
     * @param right integer value
     * @param bottom integer value
     */
    @Override
    public void setPadding(int left, int top, int right, int bottom) {
        parent.setPadding(left, top, right, bottom);
    }

    /**
     * Sets padding to parent view.
     * @param value integer value
     */
    public void setPadding(int value) {
        parent.setPadding(value, value, value, value);
    }

    /**
     * Sets selection of view by checking enabled and selected values.
     */
    protected void setSelection() {
        if (!isEnabled()) {
            ivImage.setVisibility(GONE);
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.components_selectable_view_disabled));
        } else if (isSelected()) {
            ivImage.setVisibility(VISIBLE);
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.components_selectable_view_selected));
        } else {
            ivImage.setVisibility(GONE);
            setBackground(ContextCompat.getDrawable(getContext(), R.drawable.components_selectable_view));
        }
    }

    /**
     * Returns whether is selected.
     * @return selected boolean value
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * Returns whether is enabled.
     * @return enabled boolean value
     */
    @Override
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Sets enabled value.
     * @param enabled boolean value
     */
    @Override
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
        super.setEnabled(enabled);
        setOnClickListener(enabled ? v -> toggleSelection() : null);
        setSelection();
    }

    /**
     * Changes the selection of view.
     */
    public void toggleSelection() {
        if (isEnabled()) {
            selected = !selected;
            setSelection();
        }
    }

    /**
     * Selects view.
     */
    public void selectView() {
        selected = true;
        setSelection();
    }

    /**
     * Unselects view.
     */
    public void unselectView() {
        selected = false;
        setSelection();
    }

    /**
     * Returns identifier.
     * @return id integer value
     */
    @Override
    public int getId() {
        return id;
    }

    /**
     * Sets identifier.
     * @param id integer value
     */
    @Override
    public void setId(int id) {
        this.id = id;
    }
}
