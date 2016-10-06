package xyz.belvi.intentmanipsample.IntentUtils;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zone2 on 10/3/16.
 */

public class AccountIntent {


    public List<Apps> getAppsWithAccounts(Context context) {

        return listAccountApps(context);
    }


    private ArrayList<Inclusion> getAccountAuth(ArrayList<String> contactAccount, Context context) {
        ArrayList<Inclusion> inclusions = new ArrayList<>();
        AccountManager accountManager = AccountManager.get(context);

        for (String acctType : contactAccount) {
            Account[] accounts = AccountManager.get(context).getAccountsByType(acctType);
            for (Account account : accounts) {
                String appName = account.type;
                Log.e("apn", appName);
            }
        }
        for (AuthenticatorDescription account : accountManager.getAuthenticatorTypes()) {


            inclusions.add(new Inclusion(account, false));

        }


        return inclusions;
    }

    private ArrayList<Apps> listAccountApps(Context context) {
        ArrayList<Inclusion> inclusions = getAccountAuth(new ArrayList<String>(), context);
        ArrayList<String> getAppsPackageName = new ArrayList<>();
        for (Inclusion inclusion : inclusions) {
            getAppsPackageName.add(inclusion.getAuthenticatorDescription().packageName);
        }

        Collections.sort(getAppsPackageName);

        return getAppsFromPackageDetails(context, getAppsPackageName);
    }


    private ArrayList<Apps> getAppsFromPackageDetails(Context context, ArrayList<String> appPackageNames) {

        HashMap<String, LabeledIntent> targetHashMap = new HashMap<>();
        ArrayList<Apps> appsArrayList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        int count = 0;
        for (String packageName : appPackageNames) {
            Apps apps = new Apps();
            apps.setId(count);
            apps.setTargetActivity(targetHashMap.get(packageName));
            apps.setPackageName(packageName);
            try {
                apps.setDrawable(packageManager.getApplicationIcon(packageName));
                CharSequence name = packageManager.getApplicationLabel(packageManager.getApplicationInfo(packageName, 0));
                apps.setName(name.toString());
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }
            appsArrayList.add(apps);
            count++;

        }

        Log.e("", "");
        return appsArrayList;
    }

    private class Inclusion {
        AuthenticatorDescription authenticatorDescription;
        boolean shouldInclude;

        public Inclusion(AuthenticatorDescription authenticatorDescription, boolean shouldInclude) {
            this.authenticatorDescription = authenticatorDescription;
            this.shouldInclude = shouldInclude;
        }

        public AuthenticatorDescription getAuthenticatorDescription() {
            return this.authenticatorDescription;
        }

        public void setAuthenticatorDescription(AuthenticatorDescription authenticatorDescription) {
            this.authenticatorDescription = authenticatorDescription;
        }

        public boolean isShouldInclude() {
            return this.shouldInclude;
        }

        public void setShouldInclude(boolean shouldInclude) {
            this.shouldInclude = shouldInclude;
        }
    }

    public class Apps {

        private int id;
        private String name, packageName;
        private LabeledIntent targetActivity;
        private Drawable drawable;


        public LabeledIntent getTargetActivity() {
            return targetActivity;
        }

        public void setTargetActivity(LabeledIntent targetActivity) {
            this.targetActivity = targetActivity;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPackageName() {
            return packageName;
        }

        public void setPackageName(String packageName) {
            this.packageName = packageName;
        }

        public Drawable getDrawable() {
            return drawable;
        }

        public void setDrawable(Drawable drawable) {
            this.drawable = drawable;
        }
    }

}
