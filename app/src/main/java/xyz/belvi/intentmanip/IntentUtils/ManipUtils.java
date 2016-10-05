package xyz.belvi.intentmanip.IntentUtils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/5/16.
 */

public class ManipUtils {


    protected List<ResolveIntent> lookUp(Context context, Intent intent) {
        List<ResolveIntent> resolveIntents = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        List<ResolveInfo> listCam = packageManager.queryIntentActivities(intent, 0);
        for (ResolveInfo res : listCam) {
            final Intent finalIntent = new Intent(intent);
            finalIntent.setComponent(new ComponentName(res.activityInfo.packageName, res.activityInfo.name));
            resolveIntents.add(new ResolveIntent(res, intent));

        }
        return resolveIntents;
    }

    protected String getName(Context context, ResolveInfo resolveInfo) {
        return String.valueOf(resolveInfo.loadLabel(context.getPackageManager()));
    }

    protected String getPackageName(ResolveInfo resolveInfo) {
        return String.valueOf(resolveInfo.activityInfo.packageName);
    }
}
