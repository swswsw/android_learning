package com.nextgenapp.lunchlist;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class LunchListActivity extends Activity {
	List<Restaurant> model = new ArrayList<Restaurant>();
	ArrayAdapter<Restaurant> adapter = null;
	ListView list = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.d("debug", "oncreate()");
        
        // setup the save button
        Button save = (Button)findViewById(R.id.save);
        save.setOnClickListener(onSave);
        
        // setup the list adapter
        list = (ListView)findViewById(R.id.restaurants);
        
        adapter = new ArrayAdapter<Restaurant>(this, android.R.layout.simple_list_item_1, model);
        list.setAdapter(adapter);
        
        list.post(new Runnable() {
            @Override
            public void run() {
                // Select the last row so it will scroll into view...
            	list.smoothScrollToPosition(adapter.getCount() - 1);
                // Just add something to scroll to the top ;-)
            	Log.d("debug", "inside run()");
            }
        });
    }
    
    private View.OnClickListener onSave = new View.OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Restaurant restaurant = new Restaurant();
			
			EditText name = (EditText)findViewById(R.id.name);
			EditText address = (EditText)findViewById(R.id.addr);
			
			restaurant.setName(name.getText().toString());
			restaurant.setAddress(address.getText().toString());
			
			
			RadioGroup types = (RadioGroup)findViewById(R.id.types);
			
			switch (types.getCheckedRadioButtonId()) {
			case R.id.sit_down:
				restaurant.setType("sit_down");
				break;
			case R.id.take_out:
				restaurant.setType("take_out");
				break;
			case R.id.delivery:
				restaurant.setType("delivery");
				break;
			}
			
			
			adapter.add(restaurant);
			
			
//			ListView list = (ListView)findViewById(R.id.restaurants);
//			list.smoothScrollToPosition(adapter.getCount() - 1);
			
		}
	};
}