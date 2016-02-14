package kent.kentapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.*;

public class Directory extends Activity {

    HashMap<String, List<String>> optionsHashMap;
    List<String> optionsHashMapKeys;
    ExpandableListView expandableListView;
    DirectoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_directory);

        expandableListView = (ExpandableListView) findViewById(R.id.directory_list);
        optionsHashMap = DataProvider.getDataHashMap();
        optionsHashMapKeys = new ArrayList<String>(optionsHashMap.keySet());

        adapter = new DirectoryAdapter(this, optionsHashMap, optionsHashMapKeys);
        expandableListView.setAdapter(adapter);

        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                Toast.makeText(Directory.this,
                        optionsHashMapKeys.get(groupPosition)
                                + " expanded", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int groupPosition) {
                Toast.makeText(Directory.this, optionsHashMapKeys.get(groupPosition) + " collapsed", Toast.LENGTH_SHORT).show();
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View clickedView, int groupPosition, int childPosition, long id) {
                Toast.makeText(Directory.this, "Selected " + optionsHashMap.get(optionsHashMapKeys.get(groupPosition)).get(childPosition)
                        + " from " + optionsHashMapKeys.get(groupPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
