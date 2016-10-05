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
 * Created by zone2 on 10/3/16.
 */

public class IntentLookUp extends ManipUtils {

    private List<ResolveIntent> lookUp(Context context, Intent intent) {
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

    public ResolveIntent lookUpByAppName(Context context, Intent intent, String appName) {
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (getName(context, resolveIntent.getResolveInfo()).equals(appName))
                return resolveIntent;
        }
        return null;
    }

    public ResolveIntent lookUpByAppName(Context context, List<ResolveIntent> resolveIntents, String appName) {
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getName(context, resolveIntent.getResolveInfo()).equals(appName))
                return resolveIntent;
        }
        return null;
    }

    public ResolveIntent lookUpByPackageName(Intent intent, String packageName) {
        return null;
    }

    public ResolveIntent lookUpByPackageName(List<ResolveIntent> resolveIntents, String packageName) {
        return null;
    }

    public List<ResolveIntent> lookUpAppsByAppName(Intent intent, String appName) {
        return null;
    }

    public void lookUpAppsByAppName(List<ResolveIntent> resolveIntents, String appName) {

    }

    public List<ResolveIntent> lookUpAppsByPackageName(Intent intent, String appName) {
        return null;
    }

    public void lookUpAppsByPackageName(List<ResolveIntent> resolveIntents, String appName) {

    }

    public List<ResolveIntent> lookUpAppsByRegEx(Intent intent, String appName) {
        return null;
    }

    public void lookUpAppsByRegEx(List<ResolveIntent> resolveIntents, String appName) {

    }

}
