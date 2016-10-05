package xyz.belvi.intentmanip.IntentUtils;

import android.content.Context;
import android.content.pm.ResolveInfo;

/**
 * Created by zone2 on 10/5/16.
 */

public class ManipUtils {


    protected String getName(Context context, ResolveInfo resolveInfo) {
        return String.valueOf(resolveInfo.loadLabel(context.getPackageManager()));
    }

    protected String getPackageName(ResolveInfo resolveInfo) {
        return String.valueOf(resolveInfo.activityInfo.packageName);
    }
}
