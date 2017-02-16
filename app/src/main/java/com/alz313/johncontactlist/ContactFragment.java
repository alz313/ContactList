package com.alz313.johncontactlist;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.UUID;

public class ContactFragment extends Fragment {
    private static final String ARG_CONTACT_UUID = "contact_uuid";
    private Contact mContact;


    public static ContactFragment newInstance(UUID crimeUuid) {
        Bundle args = new Bundle();
        args.putSerializable(ARG_CONTACT_UUID, crimeUuid);
        ContactFragment fragment = new ContactFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        UUID contactUuid = (UUID) getArguments().getSerializable(ARG_CONTACT_UUID);
        mContact = ContactLab.get(getActivity()).getContact(contactUuid);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_contact, container, false);

        TextView id = (TextView) v.findViewById(R.id.contact_id);
        id.setText(mContact.getId());

        TextView username = (TextView) v.findViewById(R.id.contact_username);
        username.setText(mContact.getUsername());

        TextView email = (TextView) v.findViewById(R.id.contact_email);
        email.setText(mContact.getEmail());

        TextView addressSuite = (TextView) v.findViewById(R.id.contact_address_suite);
        addressSuite.setText(mContact.getAddressSuite());

        TextView addressStreet = (TextView) v.findViewById(R.id.contact_address_street);
        addressStreet.setText(mContact.getAddressStreet());

        TextView addressCity = (TextView) v.findViewById(R.id.contact_address_city);
        addressCity.setText(mContact.getAddressCity());

        TextView addressZipCode = (TextView) v.findViewById(R.id.contact_address_zipcode);
        addressZipCode.setText(mContact.getAddressZipcode());

        TextView addressGeoLat = (TextView) v.findViewById(R.id.contact_geo_lat);
        addressGeoLat.setText(mContact.getAddressGeoLat());

        TextView addressGeoLng = (TextView) v.findViewById(R.id.contact_geo_lng);
        addressGeoLng.setText(mContact.getAddressGeolng());

        TextView phone = (TextView) v.findViewById(R.id.contact_phone);
        phone.setText(mContact.getPhone());

        TextView website = (TextView) v.findViewById(R.id.contact_website);
        website.setText(mContact.getWebsite());

        TextView companyName = (TextView) v.findViewById(R.id.contact_company_name);
        companyName.setText(mContact.getCompanyName());

        TextView companyCatchPhrase = (TextView) v.findViewById(R.id.contact_catch_phrase);
        companyCatchPhrase.setText(mContact.getCompanyCatchPhrase());

        TextView companyBs = (TextView) v.findViewById(R.id.contact_company_bs);
        companyBs.setText(mContact.getCompanyBs());

        AppCompatActivity activity = (AppCompatActivity) getActivity();
        activity.getSupportActionBar().setTitle(mContact.getName());

        return v;
    }
}
