package com.grufelous.listerr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {
    private static String TAG = "debugg";
    private static String PEOPLESITE = "https://www.fakepersongenerator.com/";
    private ListView contactListView;
    private BottomNavigationView bottomNavigationView;
    private TextView introView;
    private ArrayList<Contact> contactArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactListView = findViewById(R.id.listy);
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        introView = findViewById(R.id.introduction_line);
        Toast t = Toast.makeText(getApplicationContext(), "Selected item: " + bottomNavigationView.getSelectedItemId(), Toast.LENGTH_LONG);
        t.setGravity(Gravity.LEFT, 20, -15);
        t.show();
        fetchContact();
        ContactAdapter contactAdapter = new ContactAdapter(this, R.layout.contact_layout, contactArrayList);
        contactListView.setAdapter(contactAdapter);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.contact_item:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                            case R.id.news_item:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                            case R.id.home_item:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.place_holder_frame, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                }
        );
    }

    private void fetchContact() {
        contactArrayList = new ArrayList<>();
        //Contact c;
        /*StringRequest stringRequest = new StringRequest(Request.Method.GET, PEOPLESITE, new Response.Listener<String>() {
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
        Volley.newRequestQueue(this).add(stringRequest);*/

        Contact c1 = new Contact("Anthony N Brown", "617-631-4682", "Somewhere on this planet we call Earth", "The world shall remember thy name", "220.7 pounds (100.3 kilograms)", R.drawable.male1084184606811);
        Contact c2 = new Contact("Thomas T Decastro", "228-447-4905", "Biloxi, Mississippi(MS), 39531", "Sales and Related Occupations", "118.5 pounds (53.75 kilograms)", R.drawable.male20171084122659515);
        Contact c3 = new Contact("Sharon M Cole", "516-610-9996", "South Ozone Park, New York(NY)", "Healthcare Support Worker, All Other", "179.5 pounds (81.6 kilograms)", R.drawable.female20151024435294861);
        Contact c4 = new Contact("Rosario J Ramos", "385-315-8118", "West Valley City, Utah(UT), 84120", "\"I don't care if it takes 10 years, a war and trillions of dollars, I will find you.\" - Me about figuring out who unfriended me on Facebook.", "162.6 pounds (73.9 kilograms)", R.drawable.female1022657313050);
        contactArrayList.add(c1);
        contactArrayList.add(c2);
        contactArrayList.add(c3);
        contactArrayList.add(c4);
    }

    public class Contact {
        String leadingText, contactNumberText, addressText, aboutText, medInfo;
        int imageID;
        public Contact(String leadingText, String contactNumberText, String addressText, String aboutText, String medInfo, int imageID) {
            this.leadingText = leadingText;
            this.contactNumberText = contactNumberText;
            this.addressText = addressText;
            this.aboutText = aboutText;
            this.medInfo = medInfo;
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
            TextView leadingTextView, contactNumberView, addressTextView, aboutTextView, medInfoView;
            CircleImageView personImageView;
//            if(convertView == null) {
            convertView = getLayoutInflater().inflate(layoutResource, rootView, false);
//            }
            //find and link the corresponding elements
            leadingTextView = convertView.findViewById(R.id.leading_text);
            contactNumberView = convertView.findViewById(R.id.person_contact_number);
            addressTextView = convertView.findViewById(R.id.person_address);
            aboutTextView = convertView.findViewById(R.id.about_text);
            personImageView = convertView.findViewById(R.id.person_image);
            medInfoView = convertView.findViewById(R.id.medInfo);
            //create and get the required contact object
            Contact newContact = getItem(position);
            leadingTextView.setText(newContact.leadingText);
            contactNumberView.setText(newContact.contactNumberText);
            addressTextView.setText(newContact.addressText);
            aboutTextView.setText(newContact.aboutText);
            personImageView.setImageResource(newContact.imageID);
            medInfoView.append(newContact.medInfo);
            //return the required view with all of it^
            return convertView;
        }
    }
}
