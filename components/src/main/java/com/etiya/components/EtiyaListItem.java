package com.etiya.components;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import com.etiya.components.util.Utils;

/**
 * EtiyaListItem
 * This view is used to show one of items in a list.
 *
 * @author ramazan.vapurcu
 * Created on 10/19/2018
 */
public class EtiyaListItem extends RelativeLayout {

    protected final String DEBUG_TAG = getClass().getName();

    protected AppCompatTextView etvTitle, etvItem;
    protected String title, item;

    protected int itemIndex;

    public EtiyaListItem(Context context) {
        super(context);

        init();
    }

    public EtiyaListItem(Context context, AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.EtiyaListItem,
                0, 0
        );

        try {
            title = Utils.getStringFromAttrs(getResources(), a, R.styleable.EtiyaListItem_list_item_title);
            item = Utils.getStringFromAttrs(getResources(), a, R.styleable.EtiyaListItem_list_item);
        } finally {
            a.recycle();
        }

        init();
    }

    /**
     * Initializes EtiyaListItem.
     */
    protected void init() {
        inflate(getContext(), R.layout.components_list_item, this);

        setBackground(ContextCompat.getDrawable(getContext(), R.drawable.components_default_selector));
        setPadding(getResources().getDimensionPixelSize(R.dimen.components_list_item_padding));

        setClickable(true);
        setFocusable(true);

        etvTitle = findViewById(R.id.components_list_item_title);
        etvItem = findViewById(R.id.components_list_item);

        if (null != title)
            etvTitle.setText(title);

        if (null != item)
            etvItem.setText(item);
    }

    /**
     * Sets padding.
     * @param value integer
     */
    public void setPadding(int value) {
        setPadding(value, value, value, value);
    }

    /**
     * Returns the title of item.
     * @return String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of item.
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
        etvTitle.setText(title);
    }

    /**
     * Returns the item.
     * @return String
     */
    public String getItem() {
        return item;
    }

    /**
     * Sets the item.
     * @param item String
     */
    public void setItem(String item) {
        this.item = item;
        etvItem.setText(item);
    }

    /**
     * Returns the index of item.
     * @return integer value
     */
    public int getItemIndex() {
        return itemIndex;
    }

    /**
     * Sets the index of item.
     * @param itemIndex integer value
     */
    public void setItemIndex(int itemIndex) {
        this.itemIndex = itemIndex;
    }

    /**
     * Returns whether has item.
     * @return boolean value
     */
    public boolean hasItem() {
        return null != item;
    }

    /**
     * Sets list for selection.
     * @param list String array
     * @param selectFirst boolean value, true if the first item will be selected
     */
    public void setList(String[] list, boolean selectFirst) {
        if (selectFirst) {
            setItem(list[0]);
            setItemIndex(0);
        }

        setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setSingleChoiceItems(list, getItemIndex(), (dialogInterface, i) -> {
                setItem(list[i]);
                setItemIndex(i);
                dialogInterface.dismiss();
            });
            builder.show();
        });
    }
}
