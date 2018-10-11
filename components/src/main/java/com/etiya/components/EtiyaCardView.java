package com.etiya.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.etiya.components.util.Utils;

/**
 * EtiyaCardView
 * This view is used to show and separate the content in pages.
 *
 * @author ramazan.vapurcu
 * Created on 8/7/2018
 */
public class EtiyaCardView extends CardView {

    protected final String  DEBUG_TAG = getClass().getName();

    protected final int     DEFAULT_PADDING = 32;
    protected final int     DEFAULT_RADIUS = 1;
    protected final int     DEFAULT_TRANSLATION_VALUE = 6;
    protected final float   DEFAULT_CARD_ELEVATION = 0.8f;
    protected final boolean DEFAULT_SET_USE_COMPAT_PADDING = true;

    protected LinearLayout parent;
    protected LinearLayout.LayoutParams layoutParams;

    protected RelativeLayout rlAccordionHeader;
    protected AppCompatTextView etvAccordionTitle;
    protected ImageView ivAccordionImage;

    protected String accordion_title;
    protected State state;

    protected int orientation;
    protected int gravity;

    public enum State {
        EXPANDED,
        COLLAPSED
    }

    public EtiyaCardView(Context context) {
        super(context);
        this.orientation = LinearLayout.VERTICAL;

        init();
    }

    public EtiyaCardView(Context context, @LinearLayoutCompat.OrientationMode int orientation) {
        super(context);
        this.orientation = orientation;

        init();
    }

    public EtiyaCardView(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EtiyaCardView,
                0, 0
        );

        try {
            accordion_title = Utils.getStringFromAttrs(getResources(), a, R.styleable.EtiyaCardView_card_accordion_title);
            state = a.getInt(R.styleable.EtiyaCardView_card_accordion_state, 0) == 0 ? State.COLLAPSED : State.EXPANDED;
            orientation = a.getInt(R.styleable.EtiyaCardView_android_orientation, LinearLayout.VERTICAL);
            gravity = a.getInt(R.styleable.EtiyaCardView_android_gravity, 0);
        } finally {
            a.recycle();
        }

        init();
    }

    /**
     * Initializes EtiyaCardView.
     */
    protected void init() {
        setRadius(DEFAULT_RADIUS);
        setCardElevation(DEFAULT_CARD_ELEVATION);
        setUseCompatPadding(DEFAULT_SET_USE_COMPAT_PADDING);

        layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );
        setLayoutParams(layoutParams);

        parent = new LinearLayout(getContext());
        parent.setOrientation(orientation);
        parent.setGravity(gravity);
        parent.setPadding(DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING, DEFAULT_PADDING);
        parent.setLayoutParams(layoutParams);

        super.addView(parent);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        if (null != accordion_title)
            showAccordionView();
    }

    /**
     * Adds view to parent view.
     * @param child View
     */
    @Override
    public void addView(View child) {
        if (state == State.COLLAPSED)
            child.setVisibility(GONE);
        parent.addView(child);
    }

    /**
     * Adds view to parent view.
     * @param child View
     * @param params LayoutParams
     */
    @Override
    public void addView(View child, ViewGroup.LayoutParams params) {
        parent.addView(child, params);
    }

    /**
     * Adds view to specified index of parent view.
     * @param child View
     * @param index integer
     */
    public void addViewToIndex(View child, int index) {
        if (state == State.COLLAPSED)
            child.setVisibility(GONE);
        parent.addView(child, index);
    }

    /**
     * Removes view from parent view.
     * @param view View
     */
    @Override
    public void removeView(View view) {
        parent.removeView(view);
    }

    /**
     * Removes view from specified index of parent view.
     * @param index integer
     */
    @Override
    public void removeViewAt(int index) {
        parent.removeViewAt(index);
    }

    /**
     * Sets OnClickListener.
     * @param l OnClickListener
     */
    @Override
    public void setOnClickListener(@Nullable OnClickListener l) {
        if (null == accordion_title)
            super.setOnClickListener(l);
        else
            rlAccordionHeader.setOnClickListener(l);
    }

    /**
     * Inflates accordion header and shows it.
     */
    protected void showAccordionView() {
        rlAccordionHeader = (RelativeLayout) LayoutInflater.from(getContext()).inflate(R.layout.components_card_accordion_header, rlAccordionHeader, false);
        parent.addView(rlAccordionHeader, 0);

        etvAccordionTitle = rlAccordionHeader.findViewById(R.id.components_card_accordion_title);
        ivAccordionImage = rlAccordionHeader.findViewById(R.id.components_card_accordion_image);

        etvAccordionTitle.setText(accordion_title);

        if (state == State.EXPANDED)
            expand();
        else
            collapse();

        rlAccordionHeader.setOnClickListener(v -> toggle());
    }

    /**
     * Sets visibility of child views.
     * @param visibility integer
     */
    protected void setVisibilityOfChildViews(int visibility) {
        Log.d(DEBUG_TAG, "setVisibilityOfChildViews: " + visibility);
        int i = 1;
        while (null != parent.getChildAt(i))
            parent.getChildAt(i++).setVisibility(visibility);
    }

    /**
     * Expands EtiyaCardView.
     */
    public void expand() {
        state = State.EXPANDED;
        setVisibilityOfChildViews(VISIBLE);
        ivAccordionImage.setImageResource(R.drawable.components_ic_up);
        animate().translationY(DEFAULT_TRANSLATION_VALUE);
    }

    /**
     * Collapses EtiyaCardView.
     */
    public void collapse() {
        state = State.COLLAPSED;
        setVisibilityOfChildViews(GONE);
        ivAccordionImage.setImageResource(R.drawable.components_ic_down);
        animate().translationY(0);
    }

    /**
     * Changes the state of EtiyaCardView.
     */
    public void toggle() {
        if (state == State.COLLAPSED)
            expand();
        else
            collapse();
    }

    /**
     * Sets accordion title.
     * @param accordion_title String
     */
    public void setAccordionTitle(String accordion_title) {
        this.accordion_title = accordion_title;
        state = State.COLLAPSED;
        showAccordionView();
    }

    /**
     * Returns the state of EtiyaCardView.
     * @return State
     */
    public State getState() {
        return state;
    }

    /**
     * Returns whether is expanded.
     * @return boolean value
     */
    public boolean isExpanded() {
        return state == State.EXPANDED;
    }

    /**
     * Returns how many views there are.
     * @return integer value
     */
    public int getViewCount() {
        return parent.getChildCount();
    }

    /**
     * Sets gravity of parent view.
     * @param gravity integer value
     */
    public void setGravity(int gravity) {
        parent.setGravity(gravity);
    }

    /**
     * Sets orientation of parent view.
     * @param orientation integer value
     */
    public void setOrientation(@LinearLayoutCompat.OrientationMode int orientation) {
        this.orientation = orientation;
        parent.setOrientation(orientation);
    }
}
