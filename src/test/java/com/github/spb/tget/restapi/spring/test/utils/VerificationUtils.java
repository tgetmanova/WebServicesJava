package com.github.spb.tget.restapi.spring.test.utils;

import org.junit.runners.model.MultipleFailureException;

import java.util.ArrayList;
import java.util.List;

public class VerificationUtils {

    public static void aggregate(Runnable... runnables) throws Exception {
        List<Throwable> throwables = new ArrayList<>();
        for (Runnable r : runnables) {
            try {
                r.run();
            } catch (Throwable t) {
                throwables.add(t);
            }
        }
        MultipleFailureException.assertEmpty(throwables);
    }
}
