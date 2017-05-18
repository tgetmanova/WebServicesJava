package com.github.spb.tget.demo.controller;

import static spark.Spark.*;

import com.github.spb.tget.demo.manager.SyncProfileRequestManager;
import com.github.spb.tget.demo.model.SyncProfileRequest;
import spark.Request;
import spark.Response;
import spark.Route;

import java.util.List;

public class SyncRequestController{

    public SyncRequestController(final SyncProfileRequestManager syncProfileRequestManager) {
        get("/users", (request, response) -> syncProfileRequestManager.getSyncProfileRequests());

    }
}

