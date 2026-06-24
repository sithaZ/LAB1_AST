package com.itc.private_cloudstorage;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StorageMethodTests {

    @Test
    void contentEqualsTest() {
        long quotaBytes = 50 * 1024 * 1024;
        assertEquals(52428800, quotaBytes);
    }

    @Test
    void containsTest() {
        List<String> folders = List.of("Documents", "Images", "Videos");
        assertTrue(folders.contains("Documents"));
    }

    @Test
    void regexMatchedTest() {
        String email = "sitha@test.com";
        assertTrue(email.matches("^[\\w.+-]+@[\\w.-]+\\.[A-Za-z]{2,}$"));
    }

    @Test
    void formulaMatchedTest() {
        long quota = 52428800;
        long used = 66269;
        long free = quota - used;

        assertEquals(52362531, free);
    }

    @Test
    void predicateTest() {
        long usedBytes = 66269;
        long quotaBytes = 52428800;

        assertTrue(usedBytes < quotaBytes);
    }

    @Test
    void collectionTest() {
        List<String> files = List.of("a.txt", "b.txt", "c.txt");

        assertEquals(3, files.size());
        assertFalse(files.isEmpty());
    }

    @Test
    void exceptionTest() {
        assertThrows(RuntimeException.class, () -> {
            throw new RuntimeException("Quota exceeded");
        });
    }

    @Test
    void toleranceTest() {
        double expectedMb = 0.063;
        double actualMb = 66269.0 / 1024 / 1024;

        assertEquals(expectedMb, actualMb, 0.01);
    }

    @Test
    void schemaJsonTest() {
        String json = "{\"email\":\"sitha@test.com\",\"quotaBytes\":52428800}";

        assertTrue(json.contains("email"));
        assertTrue(json.contains("quotaBytes"));
    }
}