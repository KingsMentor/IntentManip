package xyz.belvi.intentmanip;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.MenuItem;

import com.cocosw.bottomsheet.BottomSheet;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.AccountIntent;
import xyz.belvi.intentmanip.IntentUtils.IntentCallBack.ResolvedIntentListener;
import xyz.belvi.intentmanip.IntentUtils.ManipUtils;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveCategory;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;

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

    private static BottomSheet.Builder categoryBuilder(Activity context, String title) {
        return new BottomSheet.Builder(context)
                .title(title).sheet(R.menu.category_menu);

    }

    private static BottomSheet.Builder appBuilder(Activity context, List<AccountIntent.Apps> apps, String title) {
        BottomSheet.Builder bottomSheet = new BottomSheet.Builder(context);
        bottomSheet.title(title);
        for (AccountIntent.Apps app : apps) {
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

    public static void showProfileApps(Activity context, final List<AccountIntent.Apps> apps, String title, final ResolvedIntentListener resolvedIntentListener) {
        appBuilder(context, apps, title).grid().listener(new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
//                resolvedIntentListener.onIntentSelected(resolveIntents.get(i));
            }
        }).show();
    }

    public static void categorised(Activity context, final List<ResolveCategory> resolveCategories, String title, final ResolvedIntentListener<ResolveIntent> resolvedIntentListener) {
        BottomSheet sheet = categoryBuilder(context, "Complete action using").listener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                ResolveIntent resolveIntent = findResolveIntentFromIndex(resolveCategories, menuItem.getItemId());
                resolvedIntentListener.onIntentSelected(resolveIntent);
                return false;
            }
        }).build();
        Menu menu = sheet.getMenu();
        Collections.sort(resolveCategories, new IntentCategoryOrdering());
        int id = 0;
        for (ResolveCategory resolveCategory : resolveCategories) {
            int index = 0;
            for (ResolveIntent resolveIntent : resolveCategory.getResolveIntents()) {
                if (index == 0) {
                    menu.add(0, fakeID(id), resolveCategory.getOrderId(), resolveCategory.getCategoryName());
                    menu.findItem(fakeID(id)).setEnabled(false);
                }
                menu.add(0, id, resolveCategory.getOrderId(), new ManipUtils().getName(context, resolveIntent.getResolveInfo()));
                menu.findItem(id).setIcon(resolveIntent.getResolveInfo().loadIcon(context.getPackageManager()));
                index++;
                id++;
            }

        }
        sheet.show();


    }

    private static ResolveIntent findResolveIntentFromIndex(List<ResolveCategory> resolveCategories, int index) {
        int currentIndex = index;
        for (ResolveCategory resolveCategory : resolveCategories) {
            int size = resolveCategory.getResolveIntents().size();
            if (currentIndex < size) {
                return resolveCategory.getResolveIntents().get(currentIndex);
            } else {
                currentIndex -= size;
            }
        }
        return null;
    }

    private static int fakeID(int index) {
        return 200 * (index + 1);
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

    private static class IntentCategoryOrdering implements Comparator<ResolveCategory> {


        @Override
        public int compare(ResolveCategory resolveCategory, ResolveCategory t1) {
            if (t1.getOrderId() > resolveCategory.getOrderId()) {
                return -1;
            } else if (t1.getOrderId() > resolveCategory.getOrderId()) {
                return 1;
            }
            return 0;
        }
    }

}
