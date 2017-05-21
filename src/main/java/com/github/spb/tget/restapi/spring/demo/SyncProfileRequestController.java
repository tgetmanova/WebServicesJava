package com.github.spb.tget.restapi.spring.demo;

import com.github.spb.tget.infrastructure.manager.SyncProfileRequestManager;
import com.github.spb.tget.infrastructure.model.SyncProfileRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SyncProfileRequestController {

    private SyncProfileRequestManager syncProfileRequestManager;

    public SyncProfileRequestController() throws IOException {
        this.syncProfileRequestManager = new SyncProfileRequestManager();
    }

    @RequestMapping("/profiles")
    public List<SyncProfileRequest> getSyncProfileRequests() {
        return this.syncProfileRequestManager.getSyncProfileRequests();
    }
}