package com.etiya.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.util.ArrayList;

/**
 * EtiyaCardViewGroup
 * This view is used to show and manage EtiyaCardView's.
 *
 * @author ramazan.vapurcu
 * Created on 9/10/2018
 */
public class EtiyaCardViewGroup extends LinearLayout {

    protected final String DEBUG_TAG = getClass().getName();

    public static final int NORMAL = 0;
    public static final int ACCORDION = 1;

    protected OnClickListener onClickListener;
    protected ArrayList<EtiyaCardView> cardViews;
    protected EtiyaCardView expandedCardView;
    protected int type;


    public EtiyaCardViewGroup(Context context) {
        super(context);

        init();
    }

    public EtiyaCardViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EtiyaCardViewGroup,
                0, 0
        );

        try {
            type = a.getInt(R.styleable.EtiyaCardViewGroup_card_view_group_type, NORMAL);
        } finally {
            a.recycle();
        }

        init();
    }

    /**
     * Initializes EtiyaCardViewGroup.
     */
    protected void init() {
        setOrientation(VERTICAL);

        cardViews = new ArrayList<>();

        onClickListener = v -> {
            Log.d(DEBUG_TAG, "View Type: " + type);
            EtiyaCardView cardView = (EtiyaCardView) v.getParent().getParent();
            if (cardView.isExpanded())
                collapseView(cardView);
            else {
                if (type == ACCORDION)
                    collapseAllViews();
                expandView(cardView);
            }
        };
    }

    /**
     * Expands EtiyaCardView.
     * @param cardView EtiyaCardView
     */
    public void expandView(EtiyaCardView cardView) {
        cardView.expand();
        expandedCardView = cardView;
    }

    /**
     * Collapses EtiyaCardView
     * @param cardView EtiyaCardView
     */
    public void collapseView(EtiyaCardView cardView) {
        cardView.collapse();
    }

    /**
     * Expands all card views.
     */
    public void expandAllViews() {
        for (EtiyaCardView cardView : cardViews)
            cardView.expand();
    }

    /**
     * Collapses all card views;
     */
    public void collapseAllViews() {
        for (EtiyaCardView cardView : cardViews)
            cardView.collapse();
    }

    /**
     * Adds card view to parent view.
     * @param view View
     */
    protected void addCardView(View view) {
        if (view instanceof EtiyaCardView) {
            EtiyaCardView cardView = (EtiyaCardView) view;
            if (cardView.isExpanded())
                expandedCardView = cardView;
            cardView.setOnClickListener(onClickListener);
            cardViews.add(cardView);
        } else
            throw new IllegalArgumentException("Wrong argument! You can add only EtiyaCardView.");
    }

    /**
     * Adds view to parent view.
     * @param child View
     */
    @Override
    public void addView(View child) {
        addCardView(child);

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
        addCardView(child);

        super.addView(child, params);
        Log.d(DEBUG_TAG, "View has been added.");
    }

    /**
     * Returns the type of EtiyaCardViewGroup.
     * @return type integer value
     */
    public int getType() {
        return type;
    }

    /**
     * Returns expanded view.
     * @return EtiyaCardView
     */
    public EtiyaCardView getExpandedCardView() {
        return expandedCardView;
    }

    /**
     * Returns all expanded views.
     * @return ArrayList&lt;EtiyaCardView&gt;
     */
    public ArrayList<EtiyaCardView> getExpandedCardViews() {
        ArrayList<EtiyaCardView> expandedCardViews = new ArrayList<>();
        for (EtiyaCardView cardView : expandedCardViews)
            if (cardView.isExpanded())
                expandedCardViews.add(cardView);
        return expandedCardViews;
    }
}
