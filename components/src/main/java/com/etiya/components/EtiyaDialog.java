package com.etiya.components;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.AppCompatTextView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * EtiyaDialog
 * This view is used to show custom dialog.
 *
 * @author ramazan.vapurcu
 * Created on 9/7/2018
 */
public class EtiyaDialog {

    protected Context context;
    protected AlertDialog alertDialog;
    protected View view;
    protected View content;

    protected String title;
    protected String message;

    private AppCompatTextView etvDialogTitle;
    private AppCompatTextView etvDialogMessage;
    private LinearLayout llDialogContent;

    public EtiyaDialog(Context context) {
        this.context = context;
        init();
    }

    public EtiyaDialog(Context context, @LayoutRes int resource) {
        this.context = context;
        content = View.inflate(context, resource, null);
        init();
    }

    /**
     * Initializes EtiyaDialog.
     */
    private void init() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        view = View.inflate(context, R.layout.components_dialog, null);

        ImageView ivDialogClose = view.findViewById(R.id.components_dialog_close);
        etvDialogTitle = view.findViewById(R.id.components_dialog_title);
        etvDialogMessage = view.findViewById(R.id.components_dialog_message);
        llDialogContent = view.findViewById(R.id.components_dialog_content);

        if (null != content)
            llDialogContent.addView(content);

        ivDialogClose.setOnClickListener(v -> dismiss());

        builder.setView(view);
        alertDialog = builder.create();

        Window window = alertDialog.getWindow();
        if (null != window)
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
    }

    /**
     * Returns the title of dialog.
     * @return title String
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of dialog.
     * @param title String
     */
    public void setTitle(String title) {
        this.title = title;
        etvDialogTitle.setText(title);
    }

    /**
     * Returns the message of dialog.
     * @return message String
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the message of dialog.
     * @param message String
     */
    public void setMessage(String message) {
        this.message = message;
        etvDialogMessage.setText(message);
    }

    /**
     * Adds view to content view.
     * @param view View
     */
    public void addView(View view) {
        llDialogContent.addView(view);
    }

    /**
     * Shows the dialog.
     */
    public void show() {
        alertDialog.show();
    }

    /**
     * Closes the dialog.
     */
    public void dismiss() {
        alertDialog.dismiss();
    }

    /**
     * Finds the view with specified identifier.
     * @param id integer value
     * @param <T> View class
     * @return View class
     */
    @Nullable
    public <T extends View> T findViewById(int id) {
        return view.findViewById(id);
    }

    /**
     * Returns EtiyaDialog object.
     * @param context Context
     * @param title String
     * @param message String
     * @return EtiyaDialog
     */
    public static EtiyaDialog make(Context context, String title, String message) {
        EtiyaDialog etiyaDialog = new EtiyaDialog(context);
        etiyaDialog.setTitle(title);
        etiyaDialog.setMessage(message);
        return etiyaDialog;
    }
}
