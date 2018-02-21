package com.grufelous.listerr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.internal.BottomNavigationMenu;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "debugg";
    private static String PEOPLESITE = "https://www.fakepersongenerator.com/";
    private ListView contactListView;
    private BottomNavigationView bottomNavigationMenu;
    private TextView introView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactListView = findViewById(R.id.listy);
        bottomNavigationMenu = findViewById(R.id.bottom_navigation);
        introView = findViewById(R.id.introduction_line);
        Toast t = Toast.makeText(getApplicationContext(), "Selected item: " + bottomNavigationMenu.getSelectedItemId(), Toast.LENGTH_LONG);
        t.setGravity(Gravity.LEFT, 20, -15);
        t.show();
        ArrayList<Contact> contactArrayList = new ArrayList<>();
        fetchContact();

        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.contact_layout, contactArrayList);
        contactListView.setAdapter(contactAdapter);
    }

    private void fetchContact() {
        //Contact c;
        StringRequest stringRequest = new StringRequest(Request.Method.GET, PEOPLESITE, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(TAG, "onErrorResponse: something went wrong fetching the contacts :(");
                introView.setText("Sedlyf");
            }
        }
        );
        Volley.newRequestQueue(this).add(stringRequest);
    }

    public class Contact {
        String leadingText, contactNumberText, addressText, aboutText;
        int imageID;
        public Contact(String leadingText, String contactNumberText, String addressText, String aboutText, int imageID) {
            this.leadingText = leadingText;
            this.contactNumberText = contactNumberText;
            this.addressText = addressText;
            this.aboutText = aboutText;
            this.imageID = imageID;
        }
    }

    public class ContactAdapter extends ArrayAdapter<Contact> {
        private ArrayList<Contact> list;
        private int layoutResource;
        public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
            super(context, resource, objects);
            layoutResource = resource;
            list = (ArrayList<Contact>) objects;
        }

        @Override
        public int getCount() {
            return /*super.getCount()*/ list.size();
        }

        @Nullable
        @Override
        public Contact getItem(int position) {
            return /*super.getItem(position)*/ list.get(position);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup rootView) {
            TextView leadingTextView, contactNumberView, addressTextView, aboutTextView;
            CircleImageView personImageView;
            if(convertView == null) {
                convertView = getLayoutInflater().inflate(layoutResource, rootView, false);
            }
            //find and link the corresponding elements
            leadingTextView = convertView.findViewById(R.id.leading_text);
            contactNumberView = convertView.findViewById(R.id.person_contact_number);
            addressTextView = convertView.findViewById(R.id.person_address);
            aboutTextView = convertView.findViewById(R.id.about_text);
            personImageView = convertView.findViewById(R.id.person_image);
            //create and get the required contact object
            Contact newContact = getItem(position);
            leadingTextView.setText(newContact.leadingText);
            contactNumberView.setText(newContact.contactNumberText);
            addressTextView.setText(newContact.addressText);
            aboutTextView.setText(newContact.aboutText);
            personImageView.setImageResource(newContact.imageID);
            //return the required view with all of it^
            return convertView;
        }
    }
}
