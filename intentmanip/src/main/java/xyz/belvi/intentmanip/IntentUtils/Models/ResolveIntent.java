package xyz.belvi.intentmanip.IntentUtils.Models;

import android.content.Intent;
import android.content.pm.ResolveInfo;

/**
 * Created by zone2 on 10/3/16.
 */

public class ResolveIntent {

    ResolveInfo resolveInfo;
    Intent intent;


    public ResolveIntent(ResolveInfo resolveInfo, Intent intent) {
        this.resolveInfo = resolveInfo;
        this.intent = intent;
    }

    public ResolveInfo getResolveInfo() {
        return this.resolveInfo;
    }

    public void setResolveInfo(ResolveInfo resolveInfo) {
        this.resolveInfo = resolveInfo;
    }

    public Intent getIntent() {
        return this.intent;
    }

    public void setIntent(Intent intent) {
        this.intent = intent;
    }
}
