package com.alz313.johncontactlist;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;

import java.util.UUID;

public class ContactActivity extends SingleFragmentActivity {
    private static final String EXTRA_CONTACT_UUID = "com.alz313.johncontactlist.contact_uuid";

    public static Intent newIntent(Context packageContext, UUID crimeUuid) {
        Intent intent = new Intent(packageContext, ContactActivity.class);
        intent.putExtra(EXTRA_CONTACT_UUID, crimeUuid);
        return intent;
    }

    @Override
    protected Fragment createFragment() {
        UUID crimeUuid = (UUID) getIntent().getSerializableExtra(EXTRA_CONTACT_UUID);
        return ContactFragment.newInstance(crimeUuid);
    }
}
