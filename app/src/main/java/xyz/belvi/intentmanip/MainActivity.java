package xyz.belvi.intentmanip;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.marvinlabs.intents.MediaIntents;
import com.marvinlabs.intents.PhoneIntents;

import xyz.belvi.intentmanip.IntentUtils.MergeIntent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.merge).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchIntent.withButtomSheetGrid(MainActivity.this,
                        new MergeIntent().mergeIntents(MainActivity.this, MediaIntents.newSelectPictureIntent(), PhoneIntents.newSmsIntent(MainActivity.this, "")), "Complete action using:");
            }
        });
    }
}
