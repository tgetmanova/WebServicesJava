package com.github.spb.tget.infrastructure;

import com.github.spb.tget.restapi.spark.demo.SyncProfileRequestController;
import com.github.spb.tget.infrastructure.manager.SyncProfileRequestManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Services are being run at http://localhost:4567");
        new SyncProfileRequestController(new SyncProfileRequestManager());
        System.in.read();
    }
}
