package com.dasteeny.hcb_creditassitant.Utils;

import android.content.Context;
import android.view.MenuItem;
import android.support.v7.widget.PopupMenu;
import android.widget.Toast;

import com.dasteeny.hcb_creditassitant.R;

/**
 * Created by User on 11/1/2017.
 */

public class CardMenuItemClickListener implements PopupMenu.OnMenuItemClickListener {

    private Context context;

    public CardMenuItemClickListener(Context context) {

        this.context = context;

    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.action_1:
                Toast.makeText(context, "Action 1", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_2:
                Toast.makeText(context, "Action 2", Toast.LENGTH_SHORT).show();
                return true;
            default:
        }
        return false;
    }

}
