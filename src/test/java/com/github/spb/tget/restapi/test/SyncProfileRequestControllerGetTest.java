package com.github.spb.tget.restapi.test;

import com.github.spb.tget.infrastructure.model.SyncProfileRequest;
import com.github.spb.tget.restapi.test.utils.ProfileServiceUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.Matchers.empty;

public class SyncProfileRequestControllerGetTest extends SyncProfileRequestControllerBaseTest{

    @Test
    public void getSyncProfileRequests() throws IOException {

        HttpGet getRequest = new HttpGet(ProfileServiceUtils.PROFILES_BASE_URL);
        getRequest.addHeader("accept", "application/json");

        HttpResponse response = httpClient.execute(getRequest);
        InputStream content = response.getEntity().getContent();

        List<SyncProfileRequest> allProfiles = ProfileServiceUtils.getProfilesFromInputStream(content);

        Assert.assertFalse("there should be some profiles", allProfiles.isEmpty());
        errorCollector.checkThat("there should be some profiles", allProfiles, is(not(empty())));
    }
}
