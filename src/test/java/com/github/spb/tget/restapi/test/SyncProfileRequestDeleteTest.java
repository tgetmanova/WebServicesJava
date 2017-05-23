package com.github.spb.tget.restapi.test;

import com.github.spb.tget.restapi.test.utils.ProfileServiceUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.UUID;

public class SyncProfileRequestDeleteTest extends SyncProfileRequestControllerBaseTest {

    @Test
    public void deleteSyncProfileRequest_NonExistingProfile_ShouldReturn_NotFound() throws IOException {
        UUID randomUUID = UUID.randomUUID();
        HttpDelete deleteRequest = new HttpDelete(String.format("%s/%s", ProfileServiceUtils.PROFILES_BASE_URL, randomUUID));
        HttpResponse response = this.httpClient.execute(deleteRequest);

        Assert.assertEquals("incorrect HTTP code while attempting to delete non existing profile",
                404, response.getStatusLine().getStatusCode());
    }
}
