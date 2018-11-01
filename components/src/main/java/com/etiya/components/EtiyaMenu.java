package com.etiya.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Space;

import com.etiya.components.listener.OnMenuItemSelectListener;

import java.util.ArrayList;

/**
 * EtiyaMenu
 * This view is used to show menu.
 *
 * @author ramazan.vapurcu
 * Created on 10/2/2018
 */
public class EtiyaMenu extends LinearLayout {

    protected final String DEBUG_TAG = getClass().getName();
    protected final int DEFAULT_ELEVATION = 2;

    protected OnClickListener onClickListener;
    protected OnMenuItemSelectListener onMenuItemSelectListener;
    protected ArrayList<EtiyaMenuItem> menuItemViews;
    protected EtiyaMenuItem selectedMenuItemView;
    protected int orientation;

    public EtiyaMenu(Context context) {
        super(context);
        this.orientation = HORIZONTAL;

        init();
    }

    public EtiyaMenu(Context context, @LinearLayoutCompat.OrientationMode int orientation) {
        super(context);
        this.orientation = orientation;

        init();
    }

    public EtiyaMenu(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EtiyaMenu,
                0, 0
        );

        try {
            orientation = a.getInt(R.styleable.EtiyaMenu_android_orientation, HORIZONTAL);
        } finally {
            a.recycle();
        }

        init();
    }

    /**
     * Initializes EtiyaMenu.
     */
    protected void init() {
        setOrientation(orientation);

        menuItemViews = new ArrayList<>();
        onClickListener = v -> {
            if (null != selectedMenuItemView)
                selectedMenuItemView.setSelected(false);
            setSelected((EtiyaMenuItem) v);

            if (null != onMenuItemSelectListener)
                onMenuItemSelectListener.onMenuItemSelect(menuItemViews.indexOf(selectedMenuItemView));
        };
    }

    @Override
    protected void onFinishInflate() {
        addSpaceView();

        super.onFinishInflate();
    }

    /**
     * Adds menu item.
     * @param view View
     */
    protected void addMenuView(View view) {
        if (view instanceof EtiyaMenuItem) {
            if (orientation == HORIZONTAL)
                addSpaceView();

            //view.setLayoutParams(layoutParams);
            EtiyaMenuItem menuItemView = (EtiyaMenuItem) view;
            menuItemView.setOnClickListener(onClickListener);
            menuItemViews.add(menuItemView);
        } else
            throw new IllegalArgumentException("Wrong argument! You can add only EtiyaMenuItemView.");
    }

    /**
     * Adds view to parent view.
     * @param child View
     */
    @Override
    public void addView(View child) {
        addMenuView(child);

        super.addView(child);
        Log.d(DEBUG_TAG, "View has been added.");
    }

    /**
     * Adds view to parent view with params.
     * @param child View
     * @param params LayoutParams
     */
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        addMenuView(child);

        super.addView(child, params);
        Log.d(DEBUG_TAG, "View has been added.");
    }

    /**
     * Adds space to parent view.
     */
    protected void addSpaceView() {
        LayoutParams layoutParams = new LayoutParams(
                0,
                LayoutParams.MATCH_PARENT,
                1
        );

        Space space = new Space(getContext());
        space.setLayoutParams(layoutParams);
        addView(space, getChildCount());
    }

    /**
     * Adds EtiyaMenuItemView to parent view.
     * @param name String
     */
    protected void addItem(String name) {
        EtiyaMenuItem menuItemView = new EtiyaMenuItem(getContext());
        menuItemView.setText(name);
        addView(menuItemView);
    }

    /**
     * Sets selected menu item.
     * @param menuItemView EtiyaMenuItemView
     */
    protected void setSelected(EtiyaMenuItem menuItemView) {
        menuItemView.setSelected(true);
        selectedMenuItemView = menuItemView;
    }

    /**
     * Removes selection of menu.
     */
    public void removeSelection() {
        selectedMenuItemView = null;
        for (EtiyaMenuItem menuItemView : menuItemViews)
            menuItemView.setSelected(false);
    }

    /**
     * Sets default elevation.
     */
    public void setElevation() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            setElevation(DEFAULT_ELEVATION * getContext().getResources().getDisplayMetrics().density);
    }

    /**
     * Sets padding to EtiyaMenu.
     * @param value integer
     */
    public void setPadding(int value) {
        super.setPadding(value, value, value, value);
    }

    /**
     * Sets menu items with spaces.
     * @param args String...
     */
    public void setMenuItems(String... args) {
        for (String arg : args)
            addItem(arg);

        if (orientation == HORIZONTAL)
            addSpaceView();
    }

    /**
     * Sets OnMenuItemSelectListener.
     * @param onMenuItemSelectListener OnMenuItemSelectListener
     */
    public void setOnMenuItemSelectListener(OnMenuItemSelectListener onMenuItemSelectListener) {
        this.onMenuItemSelectListener = onMenuItemSelectListener;
    }

    /**
     * Selects menu item.
     * @param index integer
     */
    public void selectMenuItem(int index) {
        menuItemViews.get(index).callOnClick();
    }
}
