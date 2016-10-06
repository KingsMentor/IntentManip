package xyz.belvi.intentmanipsample.IntentUtils;

import android.content.Context;
import android.content.Intent;

import java.util.List;

import xyz.belvi.intentmanipsample.IntentUtils.Models.ResolveCategory;
import xyz.belvi.intentmanipsample.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/3/16.
 */

public class CategorisedIntent extends ManipUtils {

    public ResolveCategory categorized(Context context, Intent intent, String name, int order, int grpId) {
        List<ResolveIntent> resolveIntents = lookUp(context, intent);

        return new ResolveCategory(resolveIntents, name, order, grpId);
    }

    public ResolveCategory categorized(List<ResolveIntent> resolveIntents, String name, int order, int grpId) {
        return new ResolveCategory(resolveIntents, name, order, grpId);
    }
}
