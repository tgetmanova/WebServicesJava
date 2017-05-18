package com.github.spb.tget.demo.data;

import java.time.Instant;
import java.util.UUID;

public class UserEntity {

    private UUID userId;

    private UUID requestId;

    private Boolean advertisingOptIn;

    private Instant dateModified;

    private String countryIsoCode;

    private String locale;

    public UserEntity(UUID userId, UUID requestId, Boolean advertisingOptIn, Instant dateModified, String countryIsoCode, String locale) {
        this.userId = userId;
        this.requestId = requestId;
        this.advertisingOptIn = advertisingOptIn;
        this.dateModified = dateModified;
        this.countryIsoCode = countryIsoCode;
        this.locale = locale;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
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
