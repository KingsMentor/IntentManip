package xyz.belvi.intentmanipsample;

import android.content.Intent;
import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.marvinlabs.intents.GeoIntents;
import com.marvinlabs.intents.MediaIntents;

import java.util.ArrayList;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.AccountIntent;
import xyz.belvi.intentmanip.IntentUtils.CategorisedIntent;
import xyz.belvi.intentmanip.IntentUtils.IntentAppend;
import xyz.belvi.intentmanip.IntentUtils.IntentCallBack.ResolvedIntentListener;
import xyz.belvi.intentmanip.IntentUtils.MergeIntent;
import xyz.belvi.intentmanip.IntentUtils.Models.PreparedIntent;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveCategory;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;
import xyz.belvi.intentmanip.LaunchIntent;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.prer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchIntent.showProfileApps(MainActivity.this, new AccountIntent().getAppsWithAccounts(MainActivity.this), "title", new ResolvedIntentListener<LabeledIntent>() {
                    @Override
                    public void onIntentSelected(LabeledIntent resolveIntent) {

                    }
                });
            }
        });
        findViewById(R.id.merge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MergeIntent mergeIntent = new MergeIntent();
                List<ResolveIntent> resolveIntents = mergeIntent.mergeIntents(MainActivity.this, MediaIntents.newPlayVideoIntent(""), MediaIntents.newSelectPictureIntent());
//                .mergeIntents(MainActivity.this, MediaIntents.newSelectPictureIntent(), PhoneIntents.newSmsIntent(MainActivity.this, ""));
//                LaunchIntent.withButtomSheetGrid(MainActivity.this,
//                        new MergeIntent().mergeIntents(MainActivity.this, MediaIntents.newSelectPictureIntent(), PhoneIntents.newSmsIntent(MainActivity.this, "")), "Complete action using:");

//                new PreferenceIntent().preferredIntent(MainActivity.this, PreferenceType.CUSTOM_APPNAME, new ArrayList<String>(Arrays.asList(new String[]{"Photos", "Images"})),
//                        resolveIntents);
                new IntentAppend().appendCustomIntent(MainActivity.this, resolveIntents, new PreparedIntent(new Intent(MainActivity.this, MainActivity.class), R.string.sample,R.mipmap.ic_launcher));
//                LaunchIntent.withButtomSheetGrid(MainActivity.this, resolveIntents,
//                        "using", new ResolvedIntentListener<ResolveIntent>() {
//                            @Override
//                            public void onIntentSelected(ResolveIntent resolveIntent) {
//
//                            }
//                        });

                List<ResolveCategory> resolveCategories = new ArrayList<ResolveCategory>();
                resolveCategories.add(new CategorisedIntent().categorized(MainActivity.this, MediaIntents.newSelectPictureIntent(), "Select Picture", 1, 0));
                resolveCategories.add(new CategorisedIntent().categorized(MainActivity.this, GeoIntents.newNavigationIntent(""), "Or User google map", 2, 0));
                LaunchIntent.categorised(MainActivity.this, resolveCategories, "title", new ResolvedIntentListener() {
                    @Override
                    public void onIntentSelected(Object resolveIntent) {

                    }
                });
            }
        });
    }
}
