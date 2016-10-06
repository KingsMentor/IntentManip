package xyz.belvi.intentmanipsample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.marvinlabs.intents.GeoIntents;
import com.marvinlabs.intents.MediaIntents;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.CategorisedIntent;
import xyz.belvi.intentmanip.IntentUtils.IntentAppend;
import xyz.belvi.intentmanip.IntentUtils.IntentCallBack.ResolvedIntentListener;
import xyz.belvi.intentmanip.IntentUtils.IntentIgnore;
import xyz.belvi.intentmanip.IntentUtils.ManipUtils;
import xyz.belvi.intentmanip.IntentUtils.MergeIntent;
import xyz.belvi.intentmanip.IntentUtils.Models.PreparedIntent;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveCategory;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;
import xyz.belvi.intentmanip.LaunchIntent;

import static xyz.belvi.intentmanip.IntentUtils.MergeIntent.mergeIntents;


public class MainActivity extends AppCompatActivity {

    private final int MERGE = 0;
    private final int CATEGORISE = 1;
    private final int APPENDING = 2;
    private final int IGNORE = 3;
    private final int LOOKUP = 4;
    private final int PREF = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.intent_list);
        listView.setAdapter(ArrayAdapter.createFromResource(this, R.array.manip_list, android.R.layout.simple_list_item_1));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                switch (position) {
                    case MERGE:
                        runMerge();
                        break;
                    case CATEGORISE:
                        categorised();
                        break;
                    case APPENDING:
                        appendIntent();
                        break;
                    case IGNORE:
                        ignore();

                }
            }
        });
    }


    private void runMerge() {
        List<ResolveIntent> resolveIntentList = mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        LaunchIntent.withButtomSheetAsList(this, resolveIntentList, "launch using", new ResolvedIntentListener() {
            @Override
            public void onIntentSelected(Object resolveIntent) {

            }
        });
    }

    private void ignore() {
        List<ResolveIntent> resolveIntentList = mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        IntentIgnore.IgnoreIntentWithName(this, resolveIntentList, new ArrayList<String>(Arrays.asList(new String[]{"Maps"})));
        LaunchIntent.withButtomSheetAsList(this, resolveIntentList, "launch using", new ResolvedIntentListener() {
            @Override
            public void onIntentSelected(Object resolveIntent) {

            }
        });
    }

    private void appendIntent() {
        PreparedIntent preparedIntent = new PreparedIntent(new Intent(this, Sample.class), R.string.sample, R.mipmap.ic_launcher);
        List<ResolveIntent> resolveIntentList = IntentAppend.appendCustomIntent(this, MediaIntents.newSelectPictureIntent(), preparedIntent);
        LaunchIntent.withButtomSheetAsList(this, resolveIntentList, "launch using", new ResolvedIntentListener<ResolveIntent>() {
            @Override
            public void onIntentSelected(ResolveIntent resolveIntent) {
                startActivity(ManipUtils.getLaunchableIntent(resolveIntent));
            }
        });
    }


    private void categorised() {
        ResolveCategory pixResolveCategory = CategorisedIntent.categorized(this, MediaIntents.newSelectPictureIntent(), "picture", 1);
        List<ResolveIntent> merge = new MergeIntent().mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        ResolveCategory mergeResolveCategory = CategorisedIntent.categorized(merge, "Geo and Media", 2);
        List<ResolveCategory> resolveCategories = new ArrayList<>();
        resolveCategories.add(pixResolveCategory);
        resolveCategories.add(mergeResolveCategory);
        LaunchIntent.categorised(this, resolveCategories, "Share With", new ResolvedIntentListener<ResolveIntent>() {
            @Override
            public void onIntentSelected(ResolveIntent resolveIntent) {
                startActivity(ManipUtils.getLaunchableIntent(resolveIntent));
            }
        });
    }
}
