package xyz.belvi.intentmanip.IntentUtils;

import android.content.Intent;

import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/3/16.
 */

public class IntentIgnore {

    public List<ResolveIntent> IgnoreIntentWithName(Intent intent, String... appName) {
        return null;
    }

    public void IgnoreIntentWithName(List<ResolveIntent> resolveIntents, String... packageName) {

    }


    public List<ResolveIntent> IgnoreIntentWithPackageName(Intent intent, String... appName) {
        return null;
    }

    public void IgnoreIntentWithPackageName(List<ResolveIntent> resolveIntents, String packageName) {

    }

    public List<ResolveIntent> IgnoreIntentsMatching(Intent intent, String regEx) {
        return null;
    }

    public void IgnoreIntentsMatching(List<ResolveIntent> resolveIntents) {

    }
}
