package xyz.belvi.intentmanipsample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class MainActivity extends AppCompatActivity {

    private final int MERGE = 0;
    private final int ACCOUNT = 0;
    private final int CATEGORISE = 0;
    private final int APPENDING = 0;
    private final int IGNORE = 0;
    private final int LOOKUP = 0;
    private final int PREF = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView listView = (ListView) findViewById(R.id.intent_list);
        listView.setAdapter(ArrayAdapter.createFromResource(this, R.array.manip_list, android.R.layout.simple_list_item_1));
    }
}
