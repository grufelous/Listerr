package com.grufelous.listerr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
            //create and get the required contact data

            return super.getView(position, convertView, rootView);
        }
    }
}
