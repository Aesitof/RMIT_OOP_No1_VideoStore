package com.no1.geniestore;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.controllers.AccountListParser;
import com.no1.geniestore.controllers.ItemData;
import com.no1.geniestore.controllers.ItemListParser;
import com.no1.geniestore.controllers.OrderListParser;
import com.no1.geniestore.products.Item;
import com.no1.geniestore.products.Order;
import javafx.collections.ObservableList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;

public class ItemListParserTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException, ParseException {
//        List<Item> items = new ItemListParser().parse("xml/items.xml");
//        for (Item item : items) {
//            System.out.println(item);
//        }

        ObservableList<ItemData> itemData = new ItemListParser().parse("xml/items.xml");
        for (ItemData item : itemData) {
            System.out.println(item);
        }

        ObservableList<ItemData> allItem = new ItemListParser().parse("xml/items.xml");
        for (ItemData item : itemData) {
            System.out.println(item);
        }

//        List<Account> accounts = new AccountListParser().parse("xml/accounts1.xml");
//        for (Account account : accounts) {
//            System.out.println(account);
//        }
//        Account account = new Account();
//        accounts.add(account);
//        AccountListParser.accountsToXML(accounts);

        List<Order> orders = new OrderListParser().parse("xml/orders.xml");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
