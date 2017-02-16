package com.alz313.johncontactlist;

import android.content.Intent;
import android.support.v4.app.Fragment;

public class ContactListActivity extends SingleFragmentActivity  implements ContactLab.OnProcessLoadingListener, ContactListFragment.Callbacks {

    @Override
    protected Fragment createFragment() {
        return new ContactListFragment();
    }

    @Override
    protected int getLayoutResId() {
        return R.layout.activity_masterdetail;
    }

    @Override
    public void onLoadStart() {
        ContactListFragment contactListFragment = (ContactListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (contactListFragment != null) {
            contactListFragment.showpDialog();
        }
    }

    @Override
    public void onLoadFinished() {
        ContactListFragment contactListFragment = (ContactListFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_container);
        if (contactListFragment != null) {
            contactListFragment.hidepDialog();
        }
    }

    @Override
    public void onContactSelected(Contact contact) {
        if (findViewById(R.id.detail_fragment_container) == null) {
            Intent intent = ContactActivity.newIntent(this, contact.getUuid());
            startActivity(intent);
        } else {
            Fragment newDetail = ContactFragment.newInstance(contact.getUuid());
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.detail_fragment_container, newDetail)
                    .commit();
        }
    }
}
