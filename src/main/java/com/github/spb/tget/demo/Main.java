package com.github.spb.tget.demo;

import com.github.spb.tget.demo.controller.SyncProfileRequestController;
import com.github.spb.tget.demo.manager.SyncProfileRequestManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Services are being run at http://localhost:4567");
        new SyncProfileRequestController(new SyncProfileRequestManager());
        System.in.read();
    }
}
