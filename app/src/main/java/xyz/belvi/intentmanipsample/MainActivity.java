package xyz.belvi.intentmanipsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.marvinlabs.intents.GeoIntents;
import com.marvinlabs.intents.MediaIntents;

import java.util.ArrayList;
import java.util.List;

import xyz.belvi.intentmanip.IntentUtils.CategorisedIntent;
import xyz.belvi.intentmanip.IntentUtils.IntentCallBack.ResolvedIntentListener;
import xyz.belvi.intentmanip.IntentUtils.ManipUtils;
import xyz.belvi.intentmanip.IntentUtils.MergeIntent;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveCategory;
import xyz.belvi.intentmanip.IntentUtils.Models.ResolveIntent;
import xyz.belvi.intentmanip.LaunchIntent;


public class MainActivity extends AppCompatActivity {

    private final int MERGE = 0;
    private final int ACCOUNT = 1;
    private final int CATEGORISE = 2;
    private final int APPENDING = 3;
    private final int IGNORE = 4;
    private final int LOOKUP = 5;
    private final int PREF = 6;

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
                }
            }
        });
    }


    private void runMerge() {
        List<ResolveIntent> resolveIntentListView = new MergeIntent().mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        LaunchIntent.withButtomSheetAsList(this, resolveIntentListView, "launch using", new ResolvedIntentListener() {
            @Override
            public void onIntentSelected(Object resolveIntent) {

            }
        });
    }

    private void appendIntent() {
        List<ResolveIntent> resolveIntentListView = new MergeIntent().mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        LaunchIntent.withButtomSheetAsList(this, resolveIntentListView, "launch using", new ResolvedIntentListener() {
            @Override
            public void onIntentSelected(Object resolveIntent) {

            }
        });
    }

    private void categorised() {
        ResolveCategory pixResolveCategory = new CategorisedIntent().categorized(this, MediaIntents.newSelectPictureIntent(), "picture", 1);
        List<ResolveIntent> merge = new MergeIntent().mergeIntents(this, MediaIntents.newSelectPictureIntent(), GeoIntents.newNavigationIntent(""));
        ResolveCategory mergeResolveCategory = new CategorisedIntent().categorized(merge, "Geo and Media", 2);
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
