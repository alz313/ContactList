package com.alz313.johncontactlist;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;


public class ContactListFragment extends Fragment {
    // Progress dialog
    private ProgressDialog pDialog;

    private RecyclerView mContactRecyclerView;
    private ContactAdapter mAdapter;

    private Callbacks mCallbacks;
    public interface Callbacks {
        void onContactSelected(Contact contact);
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mCallbacks = (Callbacks) activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contact_list, container, false);

        mContactRecyclerView = (RecyclerView) view.findViewById(R.id.contact_recycler_view);
        mContactRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(mContactRecyclerView.getContext(), LinearLayoutManager.VERTICAL);
        mContactRecyclerView.addItemDecoration(dividerItemDecoration);

        pDialog = new ProgressDialog(getActivity());
        pDialog.setMessage(getResources().getString(R.string.loading_contacts));
        pDialog.setCancelable(false);

        updateUI();

        return view;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallbacks = null;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_sort_az:
                ContactLab.get(getActivity()).sortAsc();
                updateUI();
                return true;
            case R.id.action_sort_za:
                ContactLab.get(getActivity()).sortDesc();
                updateUI();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void updateUI() {
        ContactLab contactLab = ContactLab.get(getActivity());
        List<Contact> contacts = contactLab.getContacts();
        mAdapter = new ContactAdapter(contacts);
        mContactRecyclerView.setAdapter(mAdapter);
    }

    private class ContactHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private Contact mContact;

        private TextView mNameTextView;
        private TextView mEmailTextView;

        public ContactHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            mNameTextView = (TextView) itemView.findViewById(R.id.list_item_contact_name_text_view);
            mEmailTextView = (TextView) itemView.findViewById(R.id.list_item_contact_email_text_view);
        }

        public void bindContact(Contact contact) {
            mContact = contact;
            mNameTextView.setText(mContact.getName());
            mEmailTextView.setText(mContact.getEmail());
        }

        @Override
        public void onClick(View v) {
            mCallbacks.onContactSelected(mContact);
        }
    }

    private class ContactAdapter extends RecyclerView.Adapter<ContactHolder> {
        private List<Contact> mContacts;

        public ContactAdapter(List<Contact> contacts) {
            mContacts = contacts;
        }

        @Override
        public ContactHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_contact, parent, false);
            return new ContactHolder(view);
        }

        @Override
        public void onBindViewHolder(ContactHolder holder, int position) {
            Contact contact = mContacts.get(position);
            holder.bindContact(contact);
        }

        @Override
        public int getItemCount() {
            return mContacts.size();
        }
    }

    public void showpDialog() {
        if (!pDialog.isShowing())
            pDialog.show();
    }

    public void hidepDialog() {
        if (pDialog.isShowing()) {
            pDialog.dismiss();
            updateUI();
        }

    }
}
