package com.no1.geniestore.model.util;

import static com.no1.geniestore.constants.Genre.COMEDY;
import static com.no1.geniestore.constants.Genre.DRAMA;
import static com.no1.geniestore.constants.Genre.HORROR;
import static com.no1.geniestore.constants.ItemType.DVD;
import static com.no1.geniestore.constants.ItemType.GAME;
import static com.no1.geniestore.constants.ItemType.VIDEO_RECORD;
import static com.no1.geniestore.constants.LoanType.ONE_WEEK_LOAN;
import static com.no1.geniestore.constants.LoanType.TWO_DAY_LOAN;


import com.no1.geniestore.products.Item;

public class SampleItemsUtil {
    /**
     * Returns a list of sample items.
     */
    public static Item[] getSampleItems() {
        return new Item[] {
                new Item("I008-2019", "hoho", 2019,
                        GAME, TWO_DAY_LOAN, 13.99, "minecraft.png"),
                new Item("I010-1952", "Marsupilami", 1952,
                        DVD, COMEDY, ONE_WEEK_LOAN, 3.99, "marsupilami.png"),
                new Item("I002-2013", "PUBG", 2013,
                        GAME, ONE_WEEK_LOAN, 50.99, "pubg.jpg"),
                new Item("I005-2010", "Speak Now", 2010,
                        DVD, DRAMA, TWO_DAY_LOAN, 12.99, "speaknow.jpg"),
                new Item("I001-2019", "Our Beloved Summer", 2019,
                        DVD, DRAMA, ONE_WEEK_LOAN, 15.99, "ourbelovedsummer.jpg"),
                new Item("I003-1997", "Titanic", 1997,
                        VIDEO_RECORD, DRAMA, TWO_DAY_LOAN, 23.99, "lastchristmas.jpg"),
                new Item("I004-2019", "Parasite", 2019,
                        VIDEO_RECORD, DRAMA, TWO_DAY_LOAN, 12.99, "parasite.jpg"),
                new Item("I006-2022", "Stranger Things 4", 2022,
                        VIDEO_RECORD, HORROR, ONE_WEEK_LOAN, 25.99, "strangerthings4.jpg"),
                new Item("I007-2011", "Minecraft", 2011,
                        GAME, ONE_WEEK_LOAN, 19.59, "minecraft.png"),
                new Item("I009-2019", "Boys over Flowers", 2019,
                        VIDEO_RECORD, DRAMA, TWO_DAY_LOAN, 11.59, "boysoverflowers.jpg"),
        };
    }
    
    /**
     * Returns a list of number of copies in total {@copies} of sample items.
     */
    public static int[] getSampleCopies() {
        return new int[] {1, 25, 5, 10, 20, 30, 15, 5, 20, 15};
    }
    
    /**
     * Returns a list of number of copies remaining {@remaining} of sample items.
     */
    public static int[] getSampleRemaining() {
        return new int[] {0, 21, 3, 10, 18, 27, 13, 4, 19, 0};
    }
}
