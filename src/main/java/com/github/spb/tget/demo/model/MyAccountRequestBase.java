package com.github.spb.tget.demo.model;

import java.util.UUID;

public class MyAccountRequestBase {

    private UUID userId;

    private UUID requestId;

    public MyAccountRequestBase() {
    }

    public MyAccountRequestBase(UUID userId, UUID requestId) {
        this.userId = userId;
        this.requestId = requestId;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    public UUID getRequestId() {
        return requestId;
    }

    public void setRequestId(UUID requestId) {
        this.requestId = requestId;
    }
}
