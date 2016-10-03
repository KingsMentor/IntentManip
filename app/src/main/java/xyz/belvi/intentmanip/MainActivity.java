package xyz.belvi.intentmanip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.marvinlabs.intents.MediaIntents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.IntentCallBack.ResolvedIntentListener;
import xyz.belvi.intentmanip.IntentUtils.MergeIntent;
import xyz.belvi.intentmanip.IntentUtils.Models.PreferenceType;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;
import xyz.belvi.intentmanip.IntentUtils.PreferenceIntent;

import static xyz.belvi.intentmanip.R.id.merge;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(merge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MergeIntent mergeIntent = new MergeIntent();
                List<ResolveIntent> resolveIntents = mergeIntent.mergeIntents(MainActivity.this, MediaIntents.newPlayVideoIntent(""), MediaIntents.newSelectPictureIntent());
//                .mergeIntents(MainActivity.this, MediaIntents.newSelectPictureIntent(), PhoneIntents.newSmsIntent(MainActivity.this, ""));
//                LaunchIntent.withButtomSheetGrid(MainActivity.this,
//                        new MergeIntent().mergeIntents(MainActivity.this, MediaIntents.newSelectPictureIntent(), PhoneIntents.newSmsIntent(MainActivity.this, "")), "Complete action using:");

                new PreferenceIntent().preferredIntent(MainActivity.this, PreferenceType.CUSTOM_APPNAME, new ArrayList<String>(Arrays.asList(new String[]{"Photos", "Images"})),
                        resolveIntents);
                LaunchIntent.withButtomSheetGrid(MainActivity.this, resolveIntents,
                        "using", new ResolvedIntentListener() {
                            @Override
                            public void onIntentSelected(ResolveIntent resolveIntent) {

                            }
                        });
            }
        });
    }
}
