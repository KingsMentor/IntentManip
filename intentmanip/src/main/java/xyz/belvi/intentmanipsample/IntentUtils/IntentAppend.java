package xyz.belvi.intentmanipsample.IntentUtils;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import xyz.belvi.intentmanipsample.IntentUtils.Models.PreparedIntent;
import xyz.belvi.intentmanipsample.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/3/16.
 */

public class IntentAppend extends ManipUtils {

    public List<ResolveIntent> appendCustomIntent(Context context, Intent launchItent, PreparedIntent... customIntent) {
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

    public void appendCustomIntent(Context context, List<ResolveIntent> launchResolveIntents, PreparedIntent... customIntent) {
        for (PreparedIntent preparedIntent : customIntent) {
            for (ResolveIntent resolveIntent : lookUp(context, preparedIntent.getIntent())) {
                resolveIntent.getResolveInfo().labelRes = preparedIntent.getNameRes();
                resolveIntent.getResolveInfo().icon = preparedIntent.getIconRes();
                launchResolveIntents.add(resolveIntent);
            }
        }
    }

    public List<ResolveIntent> appendToIntent(Context context, Intent launchItent, Intent... customIntent) {
        List<ResolveIntent> resolveIntents = lookUp(context, launchItent);
        for (Intent intent : customIntent) {
            resolveIntents.addAll(lookUp(context, intent));
        }
        return resolveIntents;
    }

    public void appendToIntent(Context context, List<ResolveIntent> launchResolveIntents, Intent... customIntent) {
        for (Intent intent : customIntent) {
            launchResolveIntents.addAll(lookUp(context, intent));
        }
    }
}
