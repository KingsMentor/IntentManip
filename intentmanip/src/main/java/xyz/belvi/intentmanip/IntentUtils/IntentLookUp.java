package xyz.belvi.intentmanip.IntentUtils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/3/16.
 */

public class IntentLookUp extends ManipUtils {


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

    public ResolveIntent lookUpByPackageName(Context context, Intent intent, String packageName) {
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (getPackageName(resolveIntent.getResolveInfo()).equals(packageName))
                return resolveIntent;
        }
        return null;
    }

    public ResolveIntent lookUpByPackageName(List<ResolveIntent> resolveIntents, String packageName) {
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getPackageName(resolveIntent.getResolveInfo()).equals(packageName))
                return resolveIntent;
        }
        return null;
    }

    public List<ResolveIntent> lookUpAppsByAppName(Context context, Intent intent, String appName) {
        List<ResolveIntent> resolveIntents = new ArrayList<>();
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (getName(context, resolveIntent.getResolveInfo()).contains(appName))
                resolveIntents.add(resolveIntent);
        }
        return resolveIntents;
    }

    public List<ResolveIntent> lookUpAppsByAppName(Context context, List<ResolveIntent> resolveIntents, String appName) {
        List<ResolveIntent> resultResolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getName(context, resolveIntent.getResolveInfo()).contains(appName))
                resultResolveIntentList.add(resolveIntent);
        }
        return resultResolveIntentList;
    }

    public List<ResolveIntent> lookUpAppsByPackageName(Context context, Intent intent, String packageName) {
        List<ResolveIntent> resolveIntents = new ArrayList<>();
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (getPackageName(resolveIntent.getResolveInfo()).contains(packageName))
                resolveIntents.add(resolveIntent);
        }
        return resolveIntents;
    }

    public List<ResolveIntent> lookUpAppsByPackageName(List<ResolveIntent> resolveIntents, String packageName) {
        List<ResolveIntent> resultResolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getPackageName(resolveIntent.getResolveInfo()).contains(packageName))
                resultResolveIntentList.add(resolveIntent);
        }
        return resultResolveIntentList;
    }

    public List<ResolveIntent> lookUpAppsByRegEx(Context context, Intent intent, String regEx) {
        List<ResolveIntent> resolveIntents = new ArrayList<>();
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (getPackageName(resolveIntent.getResolveInfo()).matches(regEx))
                resolveIntents.add(resolveIntent);
        }
        return resolveIntents;
    }

    public List<ResolveIntent> lookUpAppsByRegEx(List<ResolveIntent> resolveIntents, String regEx) {
        List<ResolveIntent> resultResolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getPackageName(resolveIntent.getResolveInfo()).matches(regEx))
                resultResolveIntentList.add(resolveIntent);
        }
        return resultResolveIntentList;
    }

}
