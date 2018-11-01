package com.etiya.components;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.etiya.components.listener.OnSelectListener;

import java.util.ArrayList;

/**
 * EtiyaSelectableViewGroup
 * This view is used to show and manage EtiyaSelectableView's.
 *
 * @author ramazan.vapurcu
 * Created on 9/5/2018
 */
public class EtiyaSelectableViewGroup extends LinearLayout {

    protected final String DEBUG_TAG = getClass().getName();

    public static final int SINGLE_CHOICE = 0;
    public static final int MULTIPLE_CHOICE = 1;

    protected OnClickListener onClickListener;
    protected OnSelectListener onSelectListener;
    protected MarginLayoutParams layoutParams;
    protected ArrayList<EtiyaSelectableView> selectableViews;
    protected ArrayList<EtiyaSelectableView> selectedViews;
    protected int limit;
    protected int type;


    public EtiyaSelectableViewGroup(Context context) {
        super(context);

        init();
    }

    public EtiyaSelectableViewGroup(Context context, int type) {
        super(context);
        this.type = type;

        init();
    }

    public EtiyaSelectableViewGroup(Context context, int type, int limit) {
        super(context);
        this.type = type;
        this.limit = limit;

        init();
    }

    public EtiyaSelectableViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EtiyaSelectableViewGroup,
                0, 0
        );

        try {
            type = a.getInt(R.styleable.EtiyaSelectableViewGroup_selectable_view_group_type, SINGLE_CHOICE);
            limit = a.getInt(R.styleable.EtiyaSelectableViewGroup_selectable_view_group_limit, 0);
        } finally {
            a.recycle();
        }

        init();
    }

    /**
     * Initializes EtiyaSelectableViewGroup.
     */
    protected void init() {
        setOrientation(VERTICAL);

        selectableViews = new ArrayList<>();
        selectedViews = new ArrayList<>();

        onClickListener = v -> {
            Log.d(DEBUG_TAG, "View Type: " + type);
            EtiyaSelectableView selectableView = (EtiyaSelectableView) v;
            if (selectableView.isEnabled()) {
                if (selectableView.isSelected())
                    unselectView(selectableView);
                else if (type == SINGLE_CHOICE || limit == 1) {
                    removeSelection();
                    selectView(selectableView);
                } else if (limit == 0 || selectedViews.size() < limit)
                    selectView(selectableView);
            }
        };

        layoutParams = new MarginLayoutParams(
                MarginLayoutParams.MATCH_PARENT,
                MarginLayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(
                0,
                getResources().getDimensionPixelSize(R.dimen.components_selectable_view_group_margin_top),
                0,
                0
        );
    }

    /**
     * Selects EtiyaSelectableView.
     * @param selectableView EtiyaSelectableView
     */
    public void selectView(EtiyaSelectableView selectableView) {
        if (selectableView.isEnabled()) {
            if (null != onSelectListener)
                onSelectListener.onSelect(selectableView);
            selectableView.selectView();
            selectedViews.add(selectableView);
        }
    }

    /**
     * Unselects EtiyaSelectableView.
     * @param selectableView EtiyaSelectableView
     */
    public void unselectView(EtiyaSelectableView selectableView) {
        if (selectableView.isEnabled()) {
            if (null != onSelectListener)
                onSelectListener.onUnselect(selectableView);
            selectableView.unselectView();
            selectedViews.remove(selectableView);
        }
    }

    /**
     * Unselects all selected views.
     */
    public void removeSelection() {
        for (EtiyaSelectableView selectableView : selectedViews)
            if (selectableView.isSelected())
                unselectView(selectableView);
    }

    /**
     * Adds selectable view.
     * @param view View
     */
    protected void addSelectableView(View view) {
        if (view instanceof EtiyaSelectableView) {
            view.setLayoutParams(layoutParams);
            EtiyaSelectableView selectableView = (EtiyaSelectableView) view;
            if (selectableView.isSelected())
                selectedViews.add(selectableView);
            selectableView.setOnClickListener(onClickListener);
            selectableViews.add(selectableView);
        } else
            throw new IllegalArgumentException("Wrong argument! You can add only EtiyaSelectableView.");
    }

    /**
     * Adds view to parent view.
     * @param child View
     */
    @Override
    public void addView(View child) {
        addSelectableView(child);

        super.addView(child);
        Log.d(DEBUG_TAG, "View has been added.");
    }

    /**
     * Adds view to parent view.
     * @param child View
     * @param params LayoutParams
     */
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        addSelectableView(child);

        super.addView(child, params);
        Log.d(DEBUG_TAG, "View has been added.");
    }

    /**
     * Returns the type of view.
     * @return type integer value
     */
    public int getType() {
        return type;
    }

    /**
     * Sets limit to prevent selection.
     * @param limit integer value
     */
    public void setLimit(int limit) {
        this.limit = limit;
    }

    /**
     * Sets OnSelectListener.
     * @param onSelectListener OnSelectListener
     */
    public void setOnSelectListener(OnSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }

    /**
     * Returns selected view.
     * @return EtiyaSelectableView
     */
    public EtiyaSelectableView getSelectedView() {
        return selectedViews.get(selectedViews.size() - 1);
    }

    /**
     * Returns all selected views.
     * @return EtiyaSelectableView
     */
    public ArrayList<EtiyaSelectableView> getSelectedViews() {
        return selectedViews;
    }
}
