package com.github.spb.tget.demo.controller;

import static com.github.spb.tget.demo.converter.JsonDataConverter.*;
import static spark.Spark.*;

import com.github.spb.tget.demo.manager.SyncProfileRequestManager;
import com.github.spb.tget.demo.model.SyncProfileRequest;

import java.util.UUID;

public class SyncProfileRequestController {

    public SyncProfileRequestController(final SyncProfileRequestManager syncProfileRequestManager) {

        get("/profiles", (request, response) -> syncProfileRequestManager.getSyncProfileRequests(), getJsonResponseTransformer());

        get("/profiles/:id", (req, res) -> {
            String idString = req.params(":id");
            SyncProfileRequest syncProfileRequest = syncProfileRequestManager.getSyncProfileRequestById(UUID.fromString(idString));
            return syncProfileRequest;
        }, getJsonResponseTransformer());
    }
}

