package xyz.belvi.intentmanipsample.IntentUtils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import xyz.belvi.intentmanipsample.IntentUtils.Models.PreferenceType;
import xyz.belvi.intentmanipsample.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/3/16.
 */

public class PreferenceIntent extends ManipUtils {

    Context mContext;

    public PreferenceIntent(Context context) {
        mContext = context;
    }

    public List<ResolveIntent> preferredIntent(Context context, Intent intents) {

        return preferredIntent(context, PreferenceType.ASCENDING, intents);
    }

    private List<ResolveIntent> getResolveIntents(Context context, Intent intent) {
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

    public List<ResolveIntent> preferredIntent(Context context, PreferenceType preferenceType, Intent intent) {

        List<ResolveIntent> resolveIntents = getResolveIntents(context, intent);
        Collections.sort(resolveIntents, new IntentComparator(context, preferenceType));
        return resolveIntents;

    }

    public List<ResolveIntent> preferredIntent(Context context, PreferenceType preferenceType, ArrayList<String> orderingPackageName, Intent intent) {
        List<ResolveIntent> resolveIntents = getResolveIntents(context, intent);
        Collections.sort(resolveIntents, new IntentComparator(context, preferenceType, orderingPackageName));
        return resolveIntents;

    }

    public List<ResolveIntent> preferredIntent(Context context, PreferenceType preferenceType, String regEx, Intent intent) {
        List<ResolveIntent> resolveIntents = getResolveIntents(context, intent);
        Collections.sort(resolveIntents, new IntentComparator(context, preferenceType, regEx));
        return resolveIntents;

    }

    public void preferredIntent(Context context, ArrayList<ResolveIntent> resolveIntents) {
        preferredIntent(context, PreferenceType.ASCENDING, resolveIntents);

    }

    public void preferredIntent(Context context, PreferenceType preferenceType, ArrayList<ResolveIntent> resolveIntents) {

        Collections.sort(resolveIntents, new IntentComparator(context, preferenceType));

    }

    public void preferredIntent(Context context, PreferenceType preferenceType, ArrayList<String> orderingPackageName, List<ResolveIntent> resolveIntents) {
        Collections.sort(resolveIntents, new IntentComparator(context, preferenceType, orderingPackageName));

    }

    public void preferredIntent(Context context, PreferenceType preferenceType, String regEx, List<ResolveIntent> resolveIntents) {
        Collections.sort(resolveIntents, new IntentComparator(context, preferenceType, regEx));

    }


    public class IntentComparator implements Comparator<ResolveIntent> {
        private PreferenceType preferenceType;
        private ArrayList<String> preferencesList;
        private Context mContext;
        private String regEx;

        public IntentComparator(Context context, PreferenceType preferenceType) {
            this.preferenceType = preferenceType;
            mContext = context;
        }

        public IntentComparator(Context context, PreferenceType preferenceType, ArrayList preferenceList) {
            this.preferenceType = preferenceType;
            this.preferencesList = preferenceList;
            mContext = context;
        }

        public IntentComparator(Context context, PreferenceType preferenceType, String regEx) {
            this.preferenceType = preferenceType;
            this.regEx = regEx;
            mContext = context;
        }


        @Override
        public int compare(ResolveIntent resolveIntent, ResolveIntent t1) {
            switch (preferenceType) {
                case ASCENDING:
                    return getName(mContext, resolveIntent.getResolveInfo()).compareTo(getName(mContext, t1.getResolveInfo()));
                case DECENDING:
                    return getName(mContext, t1.getResolveInfo()).compareTo(getName(mContext, resolveIntent.getResolveInfo()));
                case CUSTOM_APPNAME:
                    return compare(getName(mContext, resolveIntent.getResolveInfo()), getName(mContext, t1.getResolveInfo()));
                case CUSTOM_PACKAGENAME:
                    return compare(getPackageName(resolveIntent.getResolveInfo()), getPackageName(t1.getResolveInfo()));
                case CUSTOM_REGEX_APPNAME:
                    return compareRegEx(getName(mContext, resolveIntent.getResolveInfo()), getName(mContext, t1.getResolveInfo()));
                case CUSTOM_REGEX_PACKAGE_NAME:
                    return compareRegEx(getPackageName(resolveIntent.getResolveInfo()), getPackageName(t1.getResolveInfo()));

            }
            return 0;
        }

        private int compare(String resolveIntentName, String t1Name) {
            if (preferencesList.contains(resolveIntentName) && preferencesList.contains(t1Name)) {
                int resolveIntentIndex = preferencesList.indexOf(resolveIntentName);
                int t1Index = preferencesList.indexOf(t1Name);
                if (resolveIntentIndex > t1Index) {
                    return 1;
                }
                return -1;
            } else if (preferencesList.contains(resolveIntentName)) {
                Log.e("arrang", "jdf");
                return -1;
            } else if (preferencesList.contains(t1Name)) {
                Log.e("arrang", "jdf");
                return 1;
            }
            return resolveIntentName.compareTo(t1Name);
        }

        private int compareRegEx(String resolveIntentName, String t1Name) {
            if (resolveIntentName.matches(regEx) && !t1Name.matches(regEx)) {
                return -1;
            } else if (t1Name.matches(regEx) && !resolveIntentName.matches(regEx)) {
                return 1;
            }
            return resolveIntentName.compareTo(t1Name);
        }


    }

}
