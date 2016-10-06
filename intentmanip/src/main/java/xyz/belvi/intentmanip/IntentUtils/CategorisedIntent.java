package xyz.belvi.intentmanip.IntentUtils;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.ResolveCategory;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/3/16.
 */

public class CategorisedIntent extends ManipUtils {

    public static ResolveCategory categorized(Context context, Intent intent, String name, int order) {
        List<ResolveIntent> resolveIntents = lookUp(context, intent);

        return new ResolveCategory(resolveIntents, name, order);
    }

    public static ResolveCategory categorized(List<ResolveIntent> resolveIntents, String name, int order) {
        return new ResolveCategory(resolveIntents, name, order);
    }
}
