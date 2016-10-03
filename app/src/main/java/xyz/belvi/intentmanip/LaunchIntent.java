package xyz.belvi.intentmanip;

import android.app.Activity;

import com.cocosw.bottomsheet.BottomSheet;

import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

/**
 * Created by zone2 on 10/2/16.
 */

public class LaunchIntent {


    private static BottomSheet.Builder builder(Activity context, List<ResolveIntent> resolveIntents,String title) {
        BottomSheet.Builder bottomSheet = new BottomSheet.Builder(context);
        bottomSheet.title(title);
        int index = 0;
        for (ResolveIntent resolveIntent : resolveIntents) {
            bottomSheet.sheet(index, resolveIntent.getResolveInfo().loadIcon(context.getPackageManager()), resolveIntent.getResolveInfo().loadLabel(context.getPackageManager()));
            index++;
        }

        return bottomSheet;
    }

    public static void withButtomSheetAsList(Activity context, List<ResolveIntent> resolveIntents,String title) {
        builder(context, resolveIntents,title).show();
    }

    public static void withButtomSheetGrid(Activity context, List<ResolveIntent> resolveIntents,String title) {
        builder(context, resolveIntents,title).grid().show();
    }
}
