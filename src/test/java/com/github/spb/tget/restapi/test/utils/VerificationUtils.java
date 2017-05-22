package com.github.spb.tget.restapi.test.utils;

import junit.framework.AssertionFailedError;

import java.util.StringJoiner;

public class VerificationUtils {

    public static void aggregate(Runnable... runnables) throws Exception {
        StringJoiner errors = new StringJoiner(System.lineSeparator());
        for (Runnable r : runnables) {
            try {
                r.run();
            } catch (Throwable throwable) {
                errors.add(throwable.getMessage());
            }
        }

        String stringMessages = errors.toString();

        if (!stringMessages.isEmpty()) {
            throw new AssertionFailedError(stringMessages);
        }
    }
}
