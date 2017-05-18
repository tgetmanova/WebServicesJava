package com.github.spb.tget.demo;

import com.github.spb.tget.demo.controller.SyncRequestController;
import com.github.spb.tget.demo.manager.SyncProfileRequestManager;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("hello here");
        new SyncRequestController(new SyncProfileRequestManager());
        System.in.read();
    }
}
