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


    public static List<ResolveIntent> lookUpByAppName(Context context, Intent intent, ArrayList<String> appName) {
        List<ResolveIntent> resolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (appName.contains(getName(context, resolveIntent.getResolveInfo())))
                resolveIntentList.add(resolveIntent);
        }
        return resolveIntentList;
    }

    public static void lookUpByAppName(Context context, List<ResolveIntent> resolveIntents, ArrayList<String> appName) {
        List<ResolveIntent> resolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (appName.contains(getName(context, resolveIntent.getResolveInfo())))
                resolveIntentList.add(resolveIntent);
        }
        resolveIntents.clear();
        resolveIntents.addAll(resolveIntentList);
    }

    public static List<ResolveIntent> lookUpByPackageName(Context context, Intent intent, ArrayList<String> packageName) {
        List<ResolveIntent> resolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (packageName.contains(getPackageName(resolveIntent.getResolveInfo())))
                resolveIntentList.add(resolveIntent);
        }
        return resolveIntentList;
    }

    public static void lookUpByPackageName(List<ResolveIntent> resolveIntents, ArrayList<String> packageName) {
        List<ResolveIntent> resolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (packageName.contains(getPackageName(resolveIntent.getResolveInfo())))
                resolveIntentList.add(resolveIntent);
        }
        resolveIntentList.clear();
        resolveIntents.addAll(resolveIntentList);
    }

    public static List<ResolveIntent> lookUpAppsByAppName(Context context, Intent intent, ArrayList<String> appName) {
        List<ResolveIntent> resolveIntents = new ArrayList<>();
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (appName.contains(getName(context, resolveIntent.getResolveInfo())))
                resolveIntents.add(resolveIntent);
        }
        return resolveIntents;
    }

    public static void lookUpAppsByAppName(Context context, List<ResolveIntent> resolveIntents, ArrayList<String> appName) {
        List<ResolveIntent> resultResolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (appName.contains(getName(context, resolveIntent.getResolveInfo())))
                resultResolveIntentList.add(resolveIntent);
        }
        resolveIntents.clear();
        resolveIntents.addAll(resultResolveIntentList);
    }


    public static List<ResolveIntent> lookUpAppsByPackageName(Context context, Intent intent, ArrayList<String> packageName) {
        List<ResolveIntent> resolveIntents = new ArrayList<>();
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (packageName.contains(getPackageName(resolveIntent.getResolveInfo())))
                resolveIntents.add(resolveIntent);
        }
        return resolveIntents;
    }

    public static void lookUpAppsByPackageName(List<ResolveIntent> resolveIntents, ArrayList<String> packageName) {
        List<ResolveIntent> resultResolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (packageName.contains(getPackageName(resolveIntent.getResolveInfo())))
                resultResolveIntentList.add(resolveIntent);
        }
        resolveIntents.clear();
        resolveIntents.addAll(resultResolveIntentList);
    }

    public static List<ResolveIntent> lookUpAppsByRegEx(Context context, Intent intent, String regEx) {
        List<ResolveIntent> resolveIntents = new ArrayList<>();
        for (ResolveIntent resolveIntent : lookUp(context, intent)) {
            if (getPackageName(resolveIntent.getResolveInfo()).matches(regEx))
                resolveIntents.add(resolveIntent);
        }
        return resolveIntents;
    }

    public static void lookUpAppsByRegEx(List<ResolveIntent> resolveIntents, String regEx) {
        List<ResolveIntent> resultResolveIntentList = new ArrayList<>();
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getPackageName(resolveIntent.getResolveInfo()).matches(regEx))
                resultResolveIntentList.add(resolveIntent);
        }
        resolveIntents.clear();
        resolveIntents.addAll(resultResolveIntentList);
    }

}
