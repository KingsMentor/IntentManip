package xyz.belvi.intentmanip.IntentUtils.Models;

import android.content.Intent;

/**
 * Created by zone2 on 10/5/16.
 */

public class PreparedIntent {

    private Intent intent;
    private int nameRes, iconRes;

    public PreparedIntent(Intent intent, int nameRes, int iconRes) {
        this.intent = intent;
        this.nameRes = nameRes;
        this.iconRes = iconRes;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }

    public int getNameRes() {
        return this.nameRes;
    }

    public void setNameRes(int nameRes) {
        this.nameRes = nameRes;
    }

    public int getIconRes() {
        return this.iconRes;
    }

    public void setIconRes(int iconRes) {
        this.iconRes = iconRes;
    }
}
