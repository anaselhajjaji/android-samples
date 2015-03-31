package anaselhajjaji.android.samples.firebase;

import java.util.ArrayList;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class HelloAndroidActivity extends Activity {

	ArrayList<String> list = new ArrayList<String>();
    private Firebase myFirebaseRef;
	
	/**
	 * Called when the activity is first created.
	 * 
	 * @param savedInstanceState
	 *            If the activity is being re-initialized after previously being
	 *            shut down then this Bundle contains the data it most recently
	 *            supplied in onSaveInstanceState(Bundle). <b>Note: Otherwise it
	 *            is null.</b>
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// Retrieve components
		final ListView listView = (ListView)findViewById(R.id.messagesListView);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, list);
		listView.setAdapter(adapter);
 
		final EditText msgText = (EditText)findViewById(R.id.msgEditText);
		Button sendButton = (Button)findViewById(R.id.sendButton);
		
		// setting context to firebase
		Firebase.setAndroidContext(this);
	    myFirebaseRef = new Firebase("https://anaware.firebaseio.com/");
	    myFirebaseRef.child("message").addValueEventListener(new ValueEventListener() {
		        public void onDataChange(DataSnapshot snapshot) {
		        	list.add(snapshot.getValue().toString());
		        	((BaseAdapter) listView.getAdapter()).notifyDataSetChanged(); 
		        }
		        
	        	public void onCancelled(FirebaseError error) {
	        		// TODO
	        	}
	        });
	    
		// Add events
		sendButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				if (!msgText.getText().toString().equals("")) {
				    myFirebaseRef.child("message").setValue(msgText.getText().toString());
				    msgText.setText("");
				}
			}
		});
	}
}
