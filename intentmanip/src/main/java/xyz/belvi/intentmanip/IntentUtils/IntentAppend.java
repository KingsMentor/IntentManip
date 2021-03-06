package xyz.belvi.intentmanip.IntentUtils;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.PreparedIntent;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/3/16.
 */

public class IntentAppend extends ManipUtils {

    public static List<ResolveIntent> appendCustomIntent(Context context, Intent launchItent, PreparedIntent... customIntent) {
        List<ResolveIntent> resolveIntents = lookUp(context, launchItent);
        for (PreparedIntent preparedIntent : customIntent) {
            for (ResolveIntent resolveIntent : lookUp(context, preparedIntent.getIntent())) {
                resolveIntent.getResolveInfo().labelRes = preparedIntent.getNameRes();
                resolveIntent.getResolveInfo().icon = preparedIntent.getIconRes();
                resolveIntents.add(resolveIntent);
            }
        }
        return resolveIntents;
    }

    public static void appendCustomIntent(Context context, List<ResolveIntent> launchResolveIntents, PreparedIntent... customIntent) {
        for (PreparedIntent preparedIntent : customIntent) {
            for (ResolveIntent resolveIntent : lookUp(context, preparedIntent.getIntent())) {
                resolveIntent.getResolveInfo().labelRes = preparedIntent.getNameRes();
                resolveIntent.getResolveInfo().icon = preparedIntent.getIconRes();
                launchResolveIntents.add(resolveIntent);
            }
        }
    }

    public static List<ResolveIntent> appendToIntent(Context context, Intent launchItent, Intent... customIntent) {
        List<ResolveIntent> resolveIntents = lookUp(context, launchItent);
        for (Intent intent : customIntent) {
            resolveIntents.addAll(lookUp(context, intent));
        }
        return resolveIntents;
    }

    public static void appendToIntent(Context context, List<ResolveIntent> launchResolveIntents, Intent... customIntent) {
        for (Intent intent : customIntent) {
            launchResolveIntents.addAll(lookUp(context, intent));
        }
    }
}
