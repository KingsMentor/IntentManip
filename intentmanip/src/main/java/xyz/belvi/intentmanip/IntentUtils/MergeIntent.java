package xyz.belvi.intentmanip.IntentUtils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.ArrayList;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/2/16.
 */

public class MergeIntent extends ManipUtils {

    public static List<ResolveIntent> mergeIntents(Context context, Intent... intents) {

        PackageManager packageManager = context.getPackageManager();


        List<ResolveIntent> resolveIntents = new ArrayList<>();
        for (Intent intent : intents) {
            List<ResolveInfo> listCam = packageManager.queryIntentActivities(intent, 0);
            for (ResolveInfo res : listCam) {
                resolveIntents.add(new ResolveIntent(res, intent));
            }
        }
        return resolveIntents;
    }

    public static List<ResolveIntent> distinctMerge(Context context, Intent... intents) {

        PackageManager packageManager = context.getPackageManager();


        ArrayList<String> intentsNameLog = new ArrayList<>();
        List<ResolveIntent> resolveIntents = new ArrayList<>();
        for (Intent intent : intents) {
            List<ResolveInfo> listCam = packageManager.queryIntentActivities(intent, 0);
            for (ResolveInfo res : listCam) {
                if (!intentsNameLog.contains(getPackageName(res))) {
                    intentsNameLog.add(getPackageName(res));
                    resolveIntents.add(new ResolveIntent(res, intent));
                }
            }
        }
        intentsNameLog.clear();
        return resolveIntents;
    }
}
