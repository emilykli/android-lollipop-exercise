package com.codepath.android.lollipopexercise.activities;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.android.lollipopexercise.R;
import com.codepath.android.lollipopexercise.adapters.ContactsAdapter;
import com.codepath.android.lollipopexercise.models.Contact;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class ContactsActivity extends AppCompatActivity {
    private RecyclerView rvContacts;
    private ContactsAdapter mAdapter;
    private List<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Find RecyclerView and bind to adapter
        rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // allows for optimizations
        rvContacts.setHasFixedSize(true);

        // Define 2 column grid layout
        final GridLayoutManager layout = new GridLayoutManager(ContactsActivity.this, 2);

        // Unlike ListView, you have to explicitly give a LayoutManager to the RecyclerView to position items on the screen.
        // There are three LayoutManager provided at the moment: GridLayoutManager, StaggeredGridLayoutManager and LinearLayoutManager.
        rvContacts.setLayoutManager(layout);

        // get data
        contacts = Contact.getContacts();

        // Create an adapter
        mAdapter = new ContactsAdapter(ContactsActivity.this, contacts);

        // Bind adapter to list
        rvContacts.setAdapter(mAdapter);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_contacts, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // Define the click listener as a member
        View.OnClickListener myOnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Do something here
                Log.i("HELLO", "WE HAVE CLICKED THE undo BUTTON");
                contacts.remove(0);
                mAdapter.notifyDataSetChanged();
            }
        };

        switch(id) {
            case R.id.miAdd:
                Snackbar.make(findViewById(R.id.rvContacts), R.string.snackbar_text, Snackbar.LENGTH_LONG)
                        .setAction(R.string.snackbar_action, myOnClickListener)
                        .setActionTextColor(ContextCompat.getColor(ContactsActivity.this, R.color.accent))
                        .show(); // Don’t forget to show!
                Contact new_contact = Contact.getRandomContact(ContactsActivity.this);
                Log.i("CONTACT ADDED?", new_contact.getName());
                contacts.add(0, new_contact);
                mAdapter.notifyDataSetChanged();

        }
        return super.onOptionsItemSelected(item);
    }
}
