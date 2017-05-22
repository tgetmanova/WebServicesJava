package com.github.spb.tget.restapi.test.utils;

import com.github.spb.tget.infrastructure.model.SyncProfileRequest;
import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ProfileServiceUtils {

    public static Gson gson = new Gson();

    public static final String PROFILES_BASE_URL = "http://localhost:8080/profiles";

    public static SyncProfileRequest generateValidSyncProfileRequest() {
        return new SyncProfileRequest(UUID.randomUUID(), UUID.randomUUID(), true,
                null, "IT", "ES");
    }

    public static List<SyncProfileRequest> retrieveListOfCurrentSyncProfileRequests() throws IOException {

        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build()) {
            HttpGet getRequest = new HttpGet(PROFILES_BASE_URL);
            getRequest.addHeader("accept", "application/json");

            HttpResponse response = httpClient.execute(getRequest);

            InputStream content = response.getEntity().getContent();

            return getProfilesFromInputStream(content);
        }
    }

    public static List<SyncProfileRequest> getProfilesFromInputStream(InputStream input) {
        List<SyncProfileRequest> allProfiles;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            allProfiles = Arrays.asList(ProfileServiceUtils.gson.fromJson(reader, SyncProfileRequest[].class));
        } catch (Throwable throwable) {
            throw new IllegalStateException(String.format("Cannot read items from content: %s", throwable.getMessage()));
        }

        return allProfiles;
    }

    public static SyncProfileRequest getProfileFromInputStream(InputStream input) {
        SyncProfileRequest profile;
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            profile = gson.fromJson(reader, SyncProfileRequest.class);
        } catch (Throwable throwable) {
            throw new IllegalStateException(String.format("Cannot read single item from content: %s", throwable.getMessage()));
        }

        return profile;
    }
}
