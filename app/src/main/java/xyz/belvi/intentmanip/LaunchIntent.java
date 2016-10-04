package xyz.belvi.intentmanip;

import android.app.Activity;
import android.content.DialogInterface;

import com.cocosw.bottomsheet.BottomSheet;

import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.IntentCallBack.ResolvedIntentListener;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveCategory;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;
import xyz.belvi.intentmanip.IntentUtils.ProfileActionIntent;

/**
 * Created by zone2 on 10/2/16.
 */

public class LaunchIntent {


    private static BottomSheet.Builder builder(Activity context, List<ResolveIntent> resolveIntents, String title) {
        BottomSheet.Builder bottomSheet = new BottomSheet.Builder(context);
        bottomSheet.title(title);
        int index = 0;
        for (ResolveIntent resolveIntent : resolveIntents) {
            bottomSheet.sheet(index, resolveIntent.getResolveInfo().loadIcon(context.getPackageManager()), resolveIntent.getResolveInfo().loadLabel(context.getPackageManager()));
            index++;
        }

        return bottomSheet;
    }

    private static BottomSheet.Builder appBuilder(Activity context, List<ProfileActionIntent.Apps> apps, String title) {
        BottomSheet.Builder bottomSheet = new BottomSheet.Builder(context);
        bottomSheet.title(title);
        for (ProfileActionIntent.Apps app : apps) {
            bottomSheet.sheet(app.getId(), app.getDrawable(), app.getName());
        }

        return bottomSheet;
    }

    public static void withButtomSheetAsList(Activity context, List<ResolveIntent> resolveIntents, String title, ResolvedIntentListener resolvedIntentListener) {
        builder(context, resolveIntents, title).show();
    }

    public static void withButtomSheetGrid(Activity context, final List<ResolveIntent> resolveIntents, String title, final ResolvedIntentListener resolvedIntentListener) {
        builder(context, resolveIntents, title).grid().listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                resolvedIntentListener.onIntentSelected(resolveIntents.get(i));
            }
        }).grid().show();
    }

    public static void showProfileApps(Activity context, final List<ProfileActionIntent.Apps> apps, String title, final ResolvedIntentListener resolvedIntentListener) {
        appBuilder(context, apps, title).grid().listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                resolvedIntentListener.onIntentSelected(resolveIntents.get(i));
            }
        }).show();
    }

    public static void categorised(Activity context, List<ResolveCategory> resolveIntents, String title, ResolvedIntentListener resolvedIntentListener) {
//        builder(context, resolveIntents, title).grid().show();
    }

    public static boolean checkByAppNAme(ResolveIntent resolveIntent, String appName) {
        return false;
    }

    public static boolean checkByPackageName(ResolveIntent resolveIntent, String appName) {
        return false;
    }

    public static boolean matchesAppName(ResolveIntent resolveIntent, String regEx) {
        return false;
    }

    public static boolean matchesPackageName(ResolveIntent resolveIntent, String regEx) {
        return false;
    }

}
