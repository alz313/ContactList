package com.alz313.johncontactlist;


import java.util.UUID;

public class Contact {
    private UUID mUuid;

    private String mId;
    private String mName;
    private String mUsername;
    private String mEmail;
    private String mAddressStreet;
    private String mAddressSuite;
    private String mAddressCity;
    private String mAddressZipcode;
    private String mAddressGeoLat;
    private String mAddressGeolng;
    private String mPhone;
    private String mWebsite;
    private String mCompanyName;
    private String mCompanyCatchPhrase;
    private String mCompanyBs;

    public Contact() {
        mUuid = UUID.randomUUID();
    }

    public UUID getUuid() {
        return mUuid;
    }

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getAddressStreet() {
        return mAddressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        mAddressStreet = addressStreet;
    }

    public String getAddressSuite() {
        return mAddressSuite;
    }

    public void setAddressSuite(String addressSuite) {
        mAddressSuite = addressSuite;
    }

    public String getAddressCity() {
        return mAddressCity;
    }

    public void setAddressCity(String addressCity) {
        mAddressCity = addressCity;
    }

    public String getAddressZipcode() {
        return mAddressZipcode;
    }

    public void setAddressZipcode(String addressZipcode) {
        mAddressZipcode = addressZipcode;
    }

    public String getAddressGeoLat() {
        return mAddressGeoLat;
    }

    public void setAddressGeoLat(String addressGeoLat) {
        mAddressGeoLat = addressGeoLat;
    }

    public String getAddressGeolng() {
        return mAddressGeolng;
    }

    public void setAddressGeolng(String addressGeolng) {
        mAddressGeolng = addressGeolng;
    }

    public String getPhone() {
        return mPhone;
    }

    public void setPhone(String phone) {
        mPhone = phone;
    }

    public String getWebsite() {
        return mWebsite;
    }

    public void setWebsite(String website) {
        mWebsite = website;
    }

    public String getCompanyName() {
        return mCompanyName;
    }

    public void setCompanyName(String companyName) {
        mCompanyName = companyName;
    }

    public String getCompanyCatchPhrase() {
        return mCompanyCatchPhrase;
    }

    public void setCompanyCatchPhrase(String companyCatchPhrase) {
        mCompanyCatchPhrase = companyCatchPhrase;
    }

    public String getCompanyBs() {
        return mCompanyBs;
    }

    public void setCompanyBs(String companyBs) {
        mCompanyBs = companyBs;
    }
}
