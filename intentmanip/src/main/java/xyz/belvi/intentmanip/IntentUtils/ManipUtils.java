package xyz.belvi.intentmanip.IntentUtils;

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
            resolveIntents.add(new ResolveIntent(res, intent));
        }
        return resolveIntents;
    }

    public String getName(Context context, ResolveInfo resolveInfo) {
        return String.valueOf(resolveInfo.loadLabel(context.getPackageManager()));
    }

    protected String getPackageName(ResolveInfo resolveInfo) {
        return String.valueOf(resolveInfo.activityInfo.packageName);
    }

    public List<ResolveIntent> getInstalledAppList(Context context) {
        List<ResolveIntent> resolveIntents = new ArrayList<>();
        Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
        mainIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        List<ResolveInfo> pkgAppsList = context.getPackageManager().queryIntentActivities(mainIntent, 0);
        for (ResolveInfo res : pkgAppsList) {
            resolveIntents.add(new ResolveIntent(res, mainIntent));
        }

        return resolveIntents;
    }
}
