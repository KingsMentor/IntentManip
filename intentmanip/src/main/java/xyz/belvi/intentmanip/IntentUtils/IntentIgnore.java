package xyz.belvi.intentmanip.IntentUtils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/3/16.
 */

public class IntentIgnore extends ManipUtils {


    public List<ResolveIntent> IgnoreIntentWithName(Context context, Intent intent, ArrayList<String> skipList) {
        List<ResolveIntent> resolveIntents = lookUp(context, intent);
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (skipList.contains(getName(context, resolveIntent.getResolveInfo())))
                resolveIntents.remove(resolveIntent);
        }
        return resolveIntents;
    }

    public void IgnoreIntentWithName(Context context, List<ResolveIntent> resolveIntents, ArrayList<String> skipList) {
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (skipList.contains(getName(context, resolveIntent.getResolveInfo())))
                resolveIntents.remove(resolveIntent);
        }

    }


    public List<ResolveIntent> IgnoreIntentWithPackageName(Context context, Intent intent, ArrayList<String> skipList) {
        List<ResolveIntent> resolveIntents = lookUp(context, intent);
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (skipList.contains(getPackageName(resolveIntent.getResolveInfo())))
                resolveIntents.remove(resolveIntent);
        }
        return resolveIntents;
    }

    public void IgnoreIntentWithPackageName(List<ResolveIntent> resolveIntents, ArrayList<String> skipList) {
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (skipList.contains(getPackageName(resolveIntent.getResolveInfo())))
                resolveIntents.remove(resolveIntent);
        }

    }

    public List<ResolveIntent> IgnoreIntentsWithAppNameMatching(Context context, Intent intent, String regEx) {
        List<ResolveIntent> resolveIntents = lookUp(context, intent);
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getName(context, resolveIntent.getResolveInfo()).matches(regEx))
                resolveIntents.remove(resolveIntent);
        }
        return resolveIntents;
    }

    public void IgnoreIntentsWithAppNameMatching(Context context, List<ResolveIntent> resolveIntents, String regEx) {
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getName(context, resolveIntent.getResolveInfo()).matches(regEx))
                resolveIntents.remove(resolveIntent);
        }
    }

    public List<ResolveIntent> IgnoreIntentsWithPackageNameMatching(Context context, Intent intent, String regEx) {
        List<ResolveIntent> resolveIntents = lookUp(context, intent);
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getPackageName(resolveIntent.getResolveInfo()).matches(regEx))
                resolveIntents.remove(resolveIntent);
        }
        return resolveIntents;
    }

    public void IgnoreIntentsWithPacjageNameMatching(List<ResolveIntent> resolveIntents, String regEx) {
        for (ResolveIntent resolveIntent : resolveIntents) {
            if (getPackageName(resolveIntent.getResolveInfo()).matches(regEx))
                resolveIntents.remove(resolveIntent);
        }
    }
}
