package com.github.spb.tget.restapi.test;

import com.github.spb.tget.infrastructure.model.SyncProfileRequest;
import com.github.spb.tget.restapi.test.utils.ProfileServiceUtils;
import com.github.spb.tget.restapi.test.utils.VerificationUtils;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SyncProfileRequestControllerCreateValidationTest extends SyncProfileRequestControllerBaseTest {

    private String invalidLocale;

    private String invalidCountryIsoCode;

    public SyncProfileRequestControllerCreateValidationTest(String invalidCountryIsoCode, String invalidLocale) {
        this.invalidLocale = invalidLocale;
        this.invalidCountryIsoCode = invalidCountryIsoCode;
    }

    @Parameters
    public static Collection<String[]> getTestCasesData() {
        return Arrays.asList(new String[][]{{"RUU", "RUU"}, {"R3", "13"}, {"R", "L"}, {"R_", "*L"},
                {"R U", "()"}, {"", ""}, {null, null}, {"R ", "ESES"}, {"$5", "QQ"}});
    }

    @Test
    public void createSyncProfileRequest_InvalidIsoCode_InvalidLocale_ShouldBeValidated() throws Exception {

        HttpPost httpPost = new HttpPost(ProfileServiceUtils.PROFILES_BASE_URL);

        SyncProfileRequest expectedProfile = ProfileServiceUtils.generateValidSyncProfileRequest();
        expectedProfile.setCountryIsoCode(this.invalidCountryIsoCode);
        expectedProfile.setLocale(this.invalidLocale);

        String json = ProfileServiceUtils.gson.toJson(expectedProfile).toString();
        StringEntity entity = new StringEntity(json);

        httpPost.setEntity(entity);
        httpPost.setHeader("Accept", "application/json");
        httpPost.setHeader("Content-type", "application/json");

        CloseableHttpResponse response = this.httpClient.execute(httpPost);
        InputStream content = response.getEntity().getContent();

        String responseBody = ProfileServiceUtils.getResponseBodyFromInputStreamAsString(content);

        VerificationUtils.aggregate(
                () -> Assert.assertThat(responseBody, CoreMatchers.containsString("The Sync Profile request is invalid:")),
                () -> Assert.assertThat(responseBody, CoreMatchers.containsString(String.format("'%s' is incorrect ISO code", this.invalidCountryIsoCode))),
                () -> Assert.assertThat(responseBody, CoreMatchers.containsString(String.format("'%s' is incorrect locale format", this.invalidLocale))));
    }
}
