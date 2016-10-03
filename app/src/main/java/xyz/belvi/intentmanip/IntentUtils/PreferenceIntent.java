package xyz.belvi.intentmanip.IntentUtils;

import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.PreferenceType;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/3/16.
 */

public class PreferenceIntent {

    public List<ResolveIntent> preferredIntent(Context context, Intent... intents) {

        return null;
    }

    public List<ResolveIntent> preferredIntent(Context context, PreferenceType preferenceType, Intent... intents) {
        return null;

    }

    public List<ResolveIntent> preferredIntent(Context context, PreferenceType preferenceType, ArrayList<String> orderingPackageName, Intent... intents) {
        return null;

    }

    public List<ResolveIntent> preferredIntent(Context context, ArrayList<ResolveIntent> resolveIntents) {
        return null;

    }

    public List<ResolveIntent> preferredIntent(Context context, PreferenceType preferenceType, ArrayList<ResolveIntent> resolveIntents) {

        return null;
    }

    public List<ResolveIntent> preferredIntent(Context context, PreferenceType preferenceType, ArrayList<String> orderingPackageName, ArrayList<ResolveIntent> resolveIntents) {
        return null;

    }


}
