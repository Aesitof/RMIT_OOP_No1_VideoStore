package com.no1.geniestore.model.util;

import java.util.ArrayList;
import java.util.Arrays;

import com.no1.geniestore.accounts.Account;

public class SampleAccountsUtil {
    
    /**
     * Returns a list of sample accounts.
     */
    public static Account[] getSampleAccounts() {
        return new Account[] {
                new Account("C001", "Hings1949", "Tyler T. Appell", "Guest",
                        "2885 White Oak Drive", "8166235844", "Deipei", 0, 0),
                new Account("C002", "earlyPainting", "Earl M. Painter", "VIP",
                        "6 Ton-bridge Rd", "0796159915", "di0nly", 12, 20),
                new Account("C003", "Dingethey", "Charles K. Leonard", "Regular", "His house", "0776868753", "chienooGh0", 5, 0),
                new Account("C004", "Thicautmout38", "Patrick J. Harlow", "Regular",
                        "67 Asfordby Rd", "0794498419", "aFoto", 8, 0),
                new Account("C005", "traveloka", "Henry Parker", "Guest",
                        "22A Beidei Road TX", "0987654321", "thisismypw", 0, 0),
                new Account("C006", "hello123", "Tom Parker", "VIP",
                        "23A Beidei Road TX", "0923232278", "thisismypw", 33, 230),
                new Account("C007", "michaelne", "Michael", "Guest",
                        "23 Fl Rd", "0932444666", "guest123", 0, 0),
                new Account("C008", "ngaodg4f", "NgaoDGF", "Guest",
                        "hihihihi", "0123456789", "hehehehe", 2, 0)
        };
    }
    
    /**
     * Returns an {@code ArrayList} of sample accounts.
     */
    public static ArrayList<Account> getSampleAccountList() {
        return new ArrayList<>(Arrays.asList(getSampleAccounts()));
    }
}
