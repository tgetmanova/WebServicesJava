package com.github.spb.tget.demo.model;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class SyncProfileRequest extends MyAccountRequestBase {

    private Boolean advertisingOptIn;

    private Instant dateModified;

    private String countryIsoCode;

    private String locale;

    public SyncProfileRequest() {
    }

    public SyncProfileRequest(UUID usersId, UUID requestId, Boolean advertisingOptIn,
                              Instant dateModified, String countryIsoCode, String locale) {
        super(usersId, requestId);
        this.advertisingOptIn = advertisingOptIn;
        this.dateModified = dateModified;
        this.countryIsoCode = countryIsoCode;
        this.locale = locale;
    }

    public Boolean getAdvertisingOptIn() {
        return advertisingOptIn;
    }

    public void setAdvertisingOptIn(Boolean advertisingOptIn) {
        this.advertisingOptIn = advertisingOptIn;
    }

    public String getCountryIsoCode() {
        return countryIsoCode;
    }

    public void setCountryIsoCode(String countryIsoCode) {
        this.countryIsoCode = countryIsoCode;
    }

    public Instant getDateModified() {
        return dateModified;
    }

    public void setDateModified(Instant dateModified) {
        this.dateModified = dateModified;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
