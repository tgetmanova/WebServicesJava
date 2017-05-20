package com.github.spb.tget.demo.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class SyncProfileRequest extends MyAccountRequestBase {

    private Boolean advertisingOptIn;

    private LocalDateTime dateModified;

    private String countryIsoCode;

    private String locale;

    public SyncProfileRequest() {
    }

    public SyncProfileRequest(UUID usersId, UUID requestId, Boolean advertisingOptIn,
                              LocalDateTime dateModified, String countryIsoCode, String locale) {
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

    public LocalDateTime getDateModified() {
        return dateModified;
    }

    public void setDateModified(LocalDateTime dateModified) {
        this.dateModified = dateModified;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
