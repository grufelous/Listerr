package com.grufelous.listerr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public class Contact {
        String leadingText, addressText, aboutText;
        int imageID;
        public Contact(String leadingText, String addressText, String aboutText, int imageID) {
            this.leadingText = leadingText;
            this.addressText = addressText;
            this.aboutText = aboutText;
            this.imageID = imageID;
        }
    }

    public class ContactAdapter extends ArrayAdapter<Contact> {
        private ArrayList<Contact> list;
        private int layoutResource;
        @Override
        public ContactAdapter(@NonNull Context context, int resource, @NonNull List<Contact> objects) {
            super(context, resource, objects);
            layoutResource = resource;
            list = (ArrayList<Contact>) objects;
        }
    }
}
