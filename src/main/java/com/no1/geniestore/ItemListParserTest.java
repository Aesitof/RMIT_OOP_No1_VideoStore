package com.no1.geniestore;

import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.controllers.AccountListParser;
import com.no1.geniestore.controllers.ItemData;
import com.no1.geniestore.controllers.ItemListParser;
import com.no1.geniestore.products.Item;
import javafx.collections.ObservableList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ItemListParserTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException, TransformerException {
//        List<Item> items = new ItemListParser().parse("xml/items.xml");
//        for (Item item : items) {
//            System.out.println(item);
//        }

//        ObservableList<ItemData> itemData = new ItemListParser().parse("xml/items.xml");
//        for (ItemData item : itemData) {
//            System.out.println(item);
//        }

        List<Account> accounts = new AccountListParser().parse("xml/accounts.xml");
        for (Account account : accounts) {
            System.out.println(account);
        }
        accounts.add(new Account("C005", "traveloka", "Henry Parker", "Guest", "23A Beidei Road TX", "09232322783", "thisismypw", 0, 0));
        AccountListParser.accountsToXML(accounts);
    }
}
