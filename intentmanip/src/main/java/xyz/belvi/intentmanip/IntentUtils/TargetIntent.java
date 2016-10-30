package xyz.belvi.intentmanip.IntentUtils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/7/16.
 */

public class TargetIntent extends IntentLookUp {


    public static List<ResolveIntent> getAllMainActionApps(Context context) {
        return lookUp(context, new Intent().setAction("android.intent.action.MAIN"));
    }

    public static List<ResolveIntent> getAllApps(Context context) {
        return lookUp(context, new Intent().setAction("android.intent.action.MAIN").addCategory(Intent.CATEGORY_LAUNCHER));
    }

    public static ResolveIntent targetByAppName(Context context, List<ResolveIntent> resolveIntents, String appName) {
        lookUpAppsByAppName(context, resolveIntents, new ArrayList<String>(Arrays.asList(appName)));
        if (resolveIntents.size() > 0) {
            return resolveIntents.get(0);
        }
        return null;
    }

    public static ResolveIntent targetByPackageName(Context context, List<ResolveIntent> resolveIntents, String packageName) {
        lookUpAppsByPackageName(resolveIntents, new ArrayList<String>(Arrays.asList(packageName)));
        if (resolveIntents.size() > 0) {
            return resolveIntents.get(0);
        }
        return null;
    }


}
