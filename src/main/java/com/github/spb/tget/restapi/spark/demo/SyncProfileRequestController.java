package com.github.spb.tget.restapi.spark.demo;

import static com.github.spb.tget.restapi.spark.demo.JsonDataConverter.*;
import static spark.Spark.*;

import com.github.spb.tget.infrastructure.manager.SyncProfileRequestManager;
import com.github.spb.tget.infrastructure.model.SyncProfileRequest;

import java.util.UUID;

public class SyncProfileRequestController {

    public SyncProfileRequestController(final SyncProfileRequestManager syncProfileRequestManager) {

        get("/profiles", (request, response) -> syncProfileRequestManager.getSyncProfileRequests(), getJsonResponseTransformer());

        get("/profiles/:id", (request, response) -> {
            String idString = request.params(":id");
            SyncProfileRequest syncProfileRequest = syncProfileRequestManager.getSyncProfileRequestById(UUID.fromString(idString));
            return syncProfileRequest;
        }, getJsonResponseTransformer());
    }
}

