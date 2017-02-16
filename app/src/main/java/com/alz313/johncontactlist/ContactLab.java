package com.alz313.johncontactlist;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;


public class ContactLab {
    private static String TAG = ContactLab.class.getSimpleName();

    static OnProcessLoadingListener mCallback;

    // Container Activity must implement this interface
    public interface OnProcessLoadingListener {
        void onLoadStart();
        void onLoadFinished();
    }


    private static ContactLab sContactLab;

    private List<Contact> mContacts;

    public static ContactLab get(Context context) {
        if (sContactLab == null) {
            sContactLab = new ContactLab(context);
            makeJsonArrayRequest(context);
        }
        return sContactLab;
    }

    private ContactLab(Context context) {
        mContacts = new ArrayList<>();
    }

    public void sortAsc(){
        Collections.sort(mContacts, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Contact p1 = (Contact) o1;
                Contact p2 = (Contact) o2;
                    return p1.getName().compareToIgnoreCase(p2.getName());
            }
        });
    }

    public void sortDesc(){
        Collections.sort(mContacts, new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                Contact p1 = (Contact) o1;
                Contact p2 = (Contact) o2;
                return -1*(p1.getName().compareToIgnoreCase(p2.getName()));
            }
        });
    }



    public void addContact(Contact c) {
        mContacts.add(c);
    }

    public List<Contact> getContacts() {
        return mContacts;
    }

    public Contact getContact(UUID uuid) {
        for (Contact contact : mContacts) {
            if (contact.getUuid().equals(uuid)) {
                return contact;
            }
        }
        return null;
    }

    private static void makeJsonArrayRequest(final Context context) {
        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
            mCallback = (OnProcessLoadingListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + " must implement OnProcessLoadingListener");
        }

        mCallback.onLoadStart();

        JsonArrayRequest req = new JsonArrayRequest(Constants.URL_JSON_ARRAY, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d(TAG, response.toString());
                try {
                    // Parsing json array response
                    // loop through each json object

                    for (int i = 0; i < response.length(); i++) {
                        Contact contact = new Contact();

                        JSONObject person = (JSONObject) response.get(i);

                        contact.setId(person.getString(Constants.ID));
                        contact.setName(person.getString(Constants.NAME));
                        contact.setUsername(person.getString(Constants.USERNAME));
                        contact.setEmail(person.getString(Constants.EMAIL));

                        JSONObject address = person.getJSONObject(Constants.ADDRESS_OBJ);
                        contact.setAddressStreet(address.getString(Constants.ADDRESS_STREET));
                        contact.setAddressSuite(address.getString(Constants.ADDRESS_SUITE));
                        contact.setAddressCity(address.getString(Constants.ADDRESS_CITY));
                        contact.setAddressZipcode(address.getString(Constants.ADDRESS_ZIPCODE));

                        JSONObject addressGeo = address.getJSONObject(Constants.ADDRESS_GEO_OBJ);
                        contact.setAddressGeoLat(addressGeo.getString(Constants.ADDRESS_GEO_LAT));
                        contact.setAddressGeolng(addressGeo.getString(Constants.ADDRESS_GEO_LNG));

                        contact.setPhone(person.getString(Constants.PHONE));
                        contact.setWebsite(person.getString(Constants.WEBSITE));

                        JSONObject company = person.getJSONObject(Constants.COMPANY_OBJ);
                        contact.setCompanyName(company.getString(Constants.COMPANY_NAME));
                        contact.setCompanyCatchPhrase(company.getString(Constants.COMPANY_CATCH_PHRASE));
                        contact.setCompanyBs(company.getString(Constants.COMPANY_BS));

                        sContactLab.addContact(contact);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(context, "Error: " + e.getMessage(), Toast.LENGTH_LONG).show();
                }

                mCallback.onLoadFinished();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                Toast.makeText(context, error.getMessage(), Toast.LENGTH_SHORT).show();

                mCallback.onLoadFinished();
            }
        });

        // Adding request to request queue
        AppController.getInstance().addToRequestQueue(req);
    }
}
