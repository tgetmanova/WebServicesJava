package com.github.spb.tget.demo.controller;

import static com.github.spb.tget.demo.converter.JsonDataConverter.*;
import static spark.Spark.*;

import com.github.spb.tget.demo.manager.SyncProfileRequestManager;

public class SyncProfileRequestController {

    public SyncProfileRequestController(final SyncProfileRequestManager syncProfileRequestManager) {
        get("/users", (request, response) -> syncProfileRequestManager.getSyncProfileRequests(), getJsonResponseTransformer());
    }
}

