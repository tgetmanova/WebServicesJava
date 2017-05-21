package com.github.spb.tget.restapi.spring.demo;

import com.github.spb.tget.infrastructure.manager.SyncProfileRequestManager;
import com.github.spb.tget.infrastructure.model.SyncProfileRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
public class SyncProfileRequestController {

    private SyncProfileRequestManager syncProfileRequestManager;

    public SyncProfileRequestController() throws IOException {
        this.syncProfileRequestManager = new SyncProfileRequestManager();
    }

    @RequestMapping(value = "/profiles", method = RequestMethod.GET)
    public ResponseEntity<List<SyncProfileRequest>> getSyncProfileRequests() {
        return new ResponseEntity(this.syncProfileRequestManager.getSyncProfileRequests(),
                HttpStatus.OK);
    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.GET)
    public ResponseEntity<SyncProfileRequest> getSyncProfileRequest(@PathVariable("id") UUID id) {
        SyncProfileRequest targetProfile;
        try {
            targetProfile = this.syncProfileRequestManager.getSyncProfileRequestById(id);
        } catch (IllegalStateException exception) {
            return new ResponseEntity(String.format("Profile with ID: %s is not found", id), HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity(targetProfile, HttpStatus.OK);
    }

    @RequestMapping(value = "/profiles", method = RequestMethod.POST)
    public ResponseEntity<SyncProfileRequest> createSyncProfileRequest(@RequestBody SyncProfileRequest syncProfileRequest) {
        try {
            this.syncProfileRequestManager.validateSyncProfileRequest(syncProfileRequest);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

        if (this.syncProfileRequestManager.doesUserExistInRepository(syncProfileRequest.getUserId())) {
            this.syncProfileRequestManager.updateSyncProfileRequest(syncProfileRequest);
            return new ResponseEntity(String.format("Profile with ID: %s has been updated", syncProfileRequest.getUserId()),
                    HttpStatus.NO_CONTENT);
        }

        SyncProfileRequest newlyCreatedProfile = this.syncProfileRequestManager.createSyncProfileRequest(syncProfileRequest);
        return new ResponseEntity(newlyCreatedProfile, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/profiles/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateSyncProfileRequest(@PathVariable("id") UUID id, @RequestBody SyncProfileRequest syncProfileRequest) {
        try {
            this.syncProfileRequestManager.validateSyncProfileRequest(syncProfileRequest);
        } catch (IllegalArgumentException exception) {
            return new ResponseEntity(exception.getMessage(), HttpStatus.BAD_REQUEST);
        }

        try {
            this.syncProfileRequestManager.getSyncProfileRequestById(syncProfileRequest.getUserId());
        } catch (IllegalStateException exception) {
            return new ResponseEntity(String.format("Failed to update profile: profile with ID: %s is not found", syncProfileRequest.getUserId()),
                    HttpStatus.NOT_FOUND);
        }

        this.syncProfileRequestManager.updateSyncProfileRequest(syncProfileRequest);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}