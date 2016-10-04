package xyz.belvi.intentmanip.IntentUtils;

import android.accounts.AccountManager;
import android.accounts.AuthenticatorDescription;
import android.content.Context;
import android.content.pm.LabeledIntent;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by zone2 on 10/3/16.
 */

public class ProfileActionIntent {

    public List<Apps> getAppWithProfileAction(Context context) {

        return listInviteApps(context);
    }

    private ArrayList<String> getAllAccountTypeInContact(Context context) {
        Cursor cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null, null, null, ContactsContract.Contacts.DISPLAY_NAME + " COLLATE LOCALIZED ASC");
        ArrayList<String> contactAccountType = new ArrayList<>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                String type = (cursor.getString(cursor.getColumnIndex(ContactsContract.RawContacts.ACCOUNT_TYPE)));
                if (!type.equalsIgnoreCase("Local Phone Account")) {
                    if (!contactAccountType.contains(type))
                        contactAccountType.add(type);
                }
            }
            cursor.close();
        }
        return contactAccountType;
    }

    private ArrayList<Inclusion> getAccountAuth(Context context) {
        ArrayList<Inclusion> inclusions = new ArrayList<>();
        AccountManager accountManager = AccountManager.get(context);

        for (AuthenticatorDescription account : accountManager.getAuthenticatorTypes()) {

            inclusions.add(new Inclusion(account, false));

        }
        return inclusions;
    }

    private ArrayList<Apps> listInviteApps(Context context) {
        HashMap<String, LabeledIntent> targetHashMap = new HashMap<>();
        ArrayList<String> contactAccount = getAllAccountTypeInContact(context);
        ArrayList<Inclusion> inclusions = getAccountAuth(context);

        ArrayList<String> getAppsPackageName = new ArrayList<>();
        for (Inclusion inclusion : inclusions) {
            boolean isMember = contactAccount.contains(inclusion.getAuthenticatorDescription().packageName);
            inclusion.setShouldInclude(isMember);

            Log.e("package", inclusion.authenticatorDescription.packageName);
            if (isMember) {
                getAppsPackageName.add(inclusion.getAuthenticatorDescription().packageName);
            }
        }


        Collections.sort(getAppsPackageName);

        ArrayList<Apps> appsArrayList = new ArrayList<>();
        PackageManager packageManager = context.getPackageManager();
        int count = 0;
        for (String packageName : getAppsPackageName) {
            Apps apps = new Apps();
            apps.setId(count);
            apps.setTargetActivity(targetHashMap.get(packageName));
            apps.setPackageName(packageName);
            try {

                List<ProviderInfo> providerInfos = packageManager.queryContentProviders(ContactsContract.RawContacts.CONTENT_URI.toString(), 0, 0);
                for (ProviderInfo providerInfo : providerInfos) {
                    Log.e("f",  "found");
                }
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
