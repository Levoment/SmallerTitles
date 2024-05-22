package com.github.levoment;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Properties;

public class SmallerTitlesConfig {

    // Config values
    public static int titleYOffset = 100;
    public static int titleXOffset = 0;
    public static float titleScaleInX = 1f, titleScaleInY = 1f, titleScaleInZ = 1f;
    public static boolean showTitles = true;
    public static int subtitleYOffset = 80;
    public static int subtitleXOffset = 0;
    public static float subtitleScaleInX = 0.5f, subtitleScaleInY = 0.5f, subtitleScaleInZ = 0.5f;
    public static boolean showSubtitles = true;

    public SmallerTitlesConfig() {
    }

    @SuppressWarnings({"TryWithIdenticalCatches", "UnnecessaryBoxing"})
    public static void createOrReadConfig(Path configFilePath) {

        // Default values
        Properties properties = getProperties();

        // If the config file doesn't exist
        if (!Files.exists(configFilePath)) {
            // Create the file and open it for writing
            try (OutputStream outputStream = new FileOutputStream(configFilePath.toFile())) {
                // Write the defaults
                properties.store(outputStream, null);
            } catch (FileNotFoundException e) { // this is basically the same as only catching IOException
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            // Read the file
            try (InputStream inputStream = new FileInputStream(configFilePath.toFile())) {
                // Load the properties file
                properties.load(inputStream);
                titleYOffset =  Integer.valueOf(properties.getProperty("titleYOffset", "100"));
                titleXOffset =  Integer.valueOf(properties.getProperty("titleXOffset", "0"));
                titleScaleInX =  Float.valueOf(properties.getProperty("titleScaleInX", "1.0"));
                titleScaleInY =  Float.valueOf(properties.getProperty("titleScaleInY", "1.0"));
                titleScaleInZ =  Float.valueOf(properties.getProperty("titleScaleInZ", "1.0"));
                showTitles = Boolean.valueOf(properties.getProperty("showTitles", "true"));

                subtitleYOffset =  Integer.valueOf(properties.getProperty("subtitleYOffset", "80"));
                subtitleXOffset =  Integer.valueOf(properties.getProperty("subtitleXOffset", "0"));
                subtitleScaleInX =  Float.valueOf(properties.getProperty("subtitleScaleInX", "0.5"));
                subtitleScaleInY =  Float.valueOf(properties.getProperty("subtitleScaleInY", "0.5"));
                subtitleScaleInZ =  Float.valueOf(properties.getProperty("subtitleScaleInZ", "0.5"));
                showSubtitles = Boolean.valueOf(properties.getProperty("showSubtitles", "true"));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private static Properties getProperties() {
        Properties properties = new Properties();
        properties.setProperty("titleYOffset", String.valueOf(titleYOffset));
        properties.setProperty("titleXOffset", String.valueOf(titleXOffset));
        properties.setProperty("titleScaleInX", String.valueOf(titleScaleInX));
        properties.setProperty("titleScaleInY", String.valueOf(titleScaleInY));
        properties.setProperty("titleScaleInZ", String.valueOf(titleScaleInZ));
        properties.setProperty("showTitles", String.valueOf(showTitles));

        properties.setProperty("subtitleYOffset", String.valueOf(subtitleYOffset));
        properties.setProperty("subtitleXOffset", String.valueOf(subtitleXOffset));
        properties.setProperty("subtitleScaleInX", String.valueOf(subtitleScaleInX));
        properties.setProperty("subtitleScaleInY", String.valueOf(subtitleScaleInY));
        properties.setProperty("subtitleScaleInZ", String.valueOf(subtitleScaleInZ));
        properties.setProperty("showSubtitles", String.valueOf(showSubtitles));
        return properties;
    }

    @SuppressWarnings("TryWithIdenticalCatches")
    public static void saveConfig(Path configFilePath) {
        try (OutputStream outputStream = new FileOutputStream(configFilePath.toFile())) {
            Properties properties = getProperties();

            // Write the defaults
            properties.store(outputStream, null);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
