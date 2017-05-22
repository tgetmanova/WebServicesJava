package com.github.spb.tget.restapi.spring.test;

import com.github.spb.tget.infrastructure.model.SyncProfileRequest;
import com.github.spb.tget.restapi.spring.test.utils.ProfileServiceUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;

public class SyncProfileRequestControllerCreateTest extends SyncProfileRequestControllerBaseTest {

    @Test
    public void createSyncProfileRequest() throws IOException {

        HttpPost httpPost = new HttpPost("http://localhost:8080/profiles");

        SyncProfileRequest expetedProfile = ProfileServiceUtils.generateValidSyncProfileRequest();

        String json = ProfileServiceUtils.gson.toJson(expetedProfile).toString();
        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = this.httpClient.execute(httpPost);

        List<SyncProfileRequest> updatedList = ProfileServiceUtils.retrieveListOfCurrentSyncProfileRequests();
        SyncProfileRequest createdProfile = updatedList
                .stream()
                .filter(i -> i.getUserId().equals(expetedProfile.getUserId()))
                .findFirst()
                .orElse(null);

        Assert.assertNotNull("Failed to create profile", createdProfile);
        errorCollector.checkThat("incorrect AdvertisingOptIn",
                createdProfile.getAdvertisingOptIn(),
                equalTo(expetedProfile.getAdvertisingOptIn()));
        errorCollector.checkThat("incorrect Country ISO code",
                createdProfile.getCountryIsoCode(),
                equalTo(expetedProfile.getCountryIsoCode()));
        errorCollector.checkThat("incorrect Locale",
                createdProfile.getLocale(),
                equalTo(expetedProfile.getLocale()));
    }
}