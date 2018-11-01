package com.etiya.components;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * EtiyaTabLayout
 * This layout is used to show tab items.
 *
 * @author ramazan.vapurcu
 * Created on 10/25/2018
 */
public class EtiyaTabLayout extends LinearLayout {

    ArrayList<EtiyaTabItem> tabItems;
    EtiyaTabItem selectedTabItem;
    ViewGroup parent;

    OnClickListener onClickListener;

    public EtiyaTabLayout(Context context) {
        super(context);

        init();
    }

    public EtiyaTabLayout(Context context, AttributeSet attrs) {
        super(context, attrs);

        init();
    }

    /**
     * Initializes EtiyaTabLayout.
     */
    protected void init() {
        setOrientation(HORIZONTAL);

        tabItems = new ArrayList<>();

        onClickListener = v -> {
            EtiyaTabItem tabItem = (EtiyaTabItem) v;
            if (tabItem.isSelected())
                unselectItem(tabItem);
            else
                selectItem(tabItem);

        };
    }

    /**
     * Adds view to tab layout.
     * @param child View
     */
    @Override
    public void addView(View child) {
        addTabItemView(child);
        super.addView(child);
    }

    /**
     * Adds view to tab layout with layout params.
     * @param child View
     * @param params ViewGroup.LayoutParams
     */
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        addTabItemView(child);
        super.addView(child, params);
    }


    /**
     * Adds EtiyaTabItem to tab layout.
     * @param child View
     */
    protected void addTabItemView(View child) {
        if (child instanceof EtiyaTabItem) {
            EtiyaTabItem tabItem = (EtiyaTabItem) child;
            if (tabItem.isSelected())
                selectedTabItem = tabItem;
            tabItem.setOnClickListener(onClickListener);
            tabItems.add(tabItem);
        }
    }

    /**
     *  Sets layout params.
     *  Sets parent to find layouts of tab items.
     *  Shows the layout of tab item if it is selected.
     */
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        setLayoutParamsToTabItems();

        if (!(getParent() instanceof ViewGroup))
            throw new IllegalArgumentException("The parent of this layout should be ViewGroup.");

        parent = (ViewGroup) getParent();

        for (EtiyaTabItem tabItem : tabItems)
            setVisibilityOfItemLayout(tabItem, tabItem.isSelected() ? VISIBLE : GONE);
    }

    /**
     * Sets layout params to all tab items for equal weight.
     */
    protected void setLayoutParamsToTabItems() {
        float weight = (float) 1 / tabItems.size();
        for (EtiyaTabItem tabItem : tabItems)
            ((LayoutParams) tabItem.getLayoutParams()).weight = weight;
    }

    /**
     * Sets visibility of item layout if there is layout identifier.
     * @param tabItem EtiyaTabItem
     * @param visibility integer value
     */
    protected void setVisibilityOfItemLayout(EtiyaTabItem tabItem, int visibility) {
        if (tabItem.hasLayoutId())
            parent.findViewById(tabItem.layoutId).setVisibility(visibility);
    }

    /**
     * Selects the specified tab item.
     * @param tabItem EtiyaTabItem
     */
    public void selectItem(EtiyaTabItem tabItem) {
        unselectItem();
        selectedTabItem = tabItem;
        tabItem.setSelected(true);
        setVisibilityOfItemLayout(tabItem, VISIBLE);
    }

    /**
     * Unselects the selected tab item.
     */
    public void unselectItem() {
        if (null != selectedTabItem)
            unselectItem(selectedTabItem);
    }

    /**
     * Unselects the specified tab item.
     * @param tabItem EtiyaTabItem
     */
    public void unselectItem(EtiyaTabItem tabItem) {
        selectedTabItem = null;
        tabItem.setSelected(false);
        setVisibilityOfItemLayout(tabItem, GONE);
    }

    /**
     * Adds EtiyaTabItem which has specified title and layout identifier.
     * @param title String
     * @param layoutId integer value
     */
    public void addTabItem(String title, int layoutId) {
        EtiyaTabItem tabItem = new EtiyaTabItem(getContext());
        tabItem.setText(title);
        tabItem.setLayoutId(layoutId);
        addView(tabItem);
    }
}
