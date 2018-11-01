package com.etiya.components.sample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.etiya.components.EtiyaCardView;
import com.etiya.components.EtiyaDialog;
import com.etiya.components.EtiyaListItem;
import com.etiya.components.EtiyaMenu;
import com.etiya.components.R;

public class MainActivity extends AppCompatActivity {

    private final String DEBUG_TAG = getClass().getName();

    ImageView logo;
    EtiyaCardView cardListItem;
    EtiyaCardView cardVerticalMenu;
    EtiyaCardView cardHorizontalMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        cardListItem = findViewById(R.id.card_list_item);
        cardVerticalMenu = findViewById(R.id.card_vertical_menu);
        cardHorizontalMenu = findViewById(R.id.card_horizontal_menu);

        EtiyaListItem listItem = new EtiyaListItem(this);
        listItem.setTitle(getString(R.string.components));
        listItem.setList(getResources().getStringArray(R.array.components), true);

        cardListItem.addView(listItem);

        EtiyaMenu verticalMenu = new EtiyaMenu(this, EtiyaMenu.VERTICAL);
        verticalMenu.setMenuItems(
                "Menu 1",
                "Menu 2",
                "Menu 3"
        );
        verticalMenu.setOnMenuItemSelectListener(index -> Log.d(DEBUG_TAG, "Selected menu item: " + index));

        cardVerticalMenu.addView(verticalMenu);

        EtiyaMenu horizontalMenu = new EtiyaMenu(this, EtiyaMenu.HORIZONTAL);
        horizontalMenu.setMenuItems(
                "Menu 1",
                "Menu 2",
                "Menu 3"
        );
        horizontalMenu.setOnMenuItemSelectListener(index -> Log.d(DEBUG_TAG, "Selected menu item: " + index));

        cardHorizontalMenu.addView(horizontalMenu);

        showDialog();
        logo.setOnClickListener(v -> showDialog());
    }

    public void showDialog() {
        EtiyaDialog.make(this, getString(R.string.dialog_title), getString(R.string.dialog_message)).show();
    }
}
