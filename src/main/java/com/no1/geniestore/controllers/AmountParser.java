package com.no1.geniestore.controllers;

import static java.util.Objects.requireNonNull;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Optional;

public class AmountParser {
    
    /**
     * Reads {@code itemIdCounter} from the amount text file.
     *
     * @param filePath the path to amount file; cannot be null.
     */
    public static Optional<Integer> readItemIdCounter(String filePath) {
        requireNonNull(filePath);
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            return Optional.of(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
    
    /**
     * Reads the {@code accountIdCounter} from the amount text file.
     *
     * @param filePath the path to the amount file; cannot be null.
     */
    public static Optional<Integer> readAccountCounter(String filePath) {
        requireNonNull(filePath);
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            return Optional.of(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
    
    /**
     * Reads the {@code orderIdCounter} from the amount text file.
     *
     * @param filePath the path to the amount file; cannot be null.
     */
    public static Optional<Integer> readOrderCounter(String filePath) {
        requireNonNull(filePath);
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            reader.readLine();
            reader.readLine();
            return Optional.of(Integer.parseInt(reader.readLine()));
        } catch (IOException e) {
            return Optional.empty();
        }
    }
}
