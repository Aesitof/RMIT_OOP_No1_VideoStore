package com.no1.geniestore.model.util;

import static com.no1.geniestore.constants.Genre.DRAMA;
import static com.no1.geniestore.constants.ItemType.DVD;
import static com.no1.geniestore.constants.ItemType.VIDEO_RECORD;
import static com.no1.geniestore.constants.LoanType.TWO_DAY_LOAN;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.products.Item;
import com.no1.geniestore.products.Order;
import com.no1.geniestore.products.OrderDetails;

public class SampleOrdersUtil {
    
    /**
     * Returns a list of sample orders.
     */
    public static Order[] getSampleOrders() {
        Account[] sampleAccounts = SampleAccountsUtil.getSampleAccounts();
        Order[] sampleOrders = new Order[5];
        // order 1
        Item order1Item = new Item("I004-2019", "Parasite", 2019, VIDEO_RECORD, DRAMA, TWO_DAY_LOAN, 12.99, "parasite.jpg");
        OrderDetails order1OrderDetails = new OrderDetails("13-01-2023", "06-01-2023", 2, false, 0.0);
        HashMap<Item, OrderDetails> hashMap1 = new HashMap<>();
        hashMap1.put(order1Item, order1OrderDetails);
        sampleOrders[0] = new Order("1", sampleAccounts[3], hashMap1, 25.98, 0.0);
        
        // order 2
        Item order2Item = new Item("I003-1997", "Titanic", 1997, VIDEO_RECORD, DRAMA, TWO_DAY_LOAN, 12.59, "titanic.jpg");
        OrderDetails order2OrderDetails = new OrderDetails("29-11-2023", "27-11-2023", 3, true, 0.0);
        HashMap<Item, OrderDetails> hashMap2 = new HashMap<>();
        hashMap2.put(order2Item, order2OrderDetails);
        sampleOrders[1] = new Order("2", sampleAccounts[1], hashMap2, 37.77, 0.0);
        
        // order 3
        Item order3Item = new Item("I001-2001", "Last Christmas", 2001, DVD, DRAMA, TWO_DAY_LOAN, 23.99, "lastchristmas.jpg");
        OrderDetails order3OrderDetails = new OrderDetails("19-04-2023", "12-04-2023", 2, true, 0.0);
        HashMap<Item, OrderDetails> hashMap3 = new HashMap<>();
        hashMap3.put(order3Item, order3OrderDetails);
        sampleOrders[2] = new Order("3", sampleAccounts[3], hashMap3, 73.96, 0.0);
     
        // order 4
        Item order4Item = new Item("I003-1997", "Titanic", 1997, VIDEO_RECORD, DRAMA, TWO_DAY_LOAN, 12.59, "titanic.jpg");
        OrderDetails order4OrderDetails = new OrderDetails("08-07-2023", "01-07-2023", 1, true, 0.0);
        HashMap<Item, OrderDetails> hashMap4 = new HashMap<>();
        hashMap4.put(order4Item, order4OrderDetails);
        sampleOrders[3] = new Order("4", sampleAccounts[3], hashMap4, 12.59, 0.0);
        
        // order 5
        Item order5Item = new Item("I004-2019", "Parasite", 2019, VIDEO_RECORD, DRAMA, TWO_DAY_LOAN, 12.59, "parasite.jpg");
        OrderDetails order5OrderDetails = new OrderDetails("12-05-2023", "05-05-2023", 5, true, 0.0);
        HashMap<Item, OrderDetails> hashMap5 = new HashMap<>();
        hashMap5.put(order5Item, order5OrderDetails);
        sampleOrders[4] = new Order("5", sampleAccounts[1], hashMap5, 64.95, 0.0);
        
        return sampleOrders;
    }
    
    /**
     * Returns a {@code List} of sample orders.
     */
    public static ArrayList<Order> getSampleOrderList() {
        return new ArrayList<>(Arrays.asList(getSampleOrders()));
    }
    
}
