package com.no1.geniestore;

import com.no1.geniestore.controllers.ItemData;
import com.no1.geniestore.controllers.ItemListParser;
import com.no1.geniestore.products.Item;
import javafx.collections.ObservableList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;

public class ItemListParserTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
//        List<Item> items = new ItemListParser().parse("xml/items.xml");
//        for (Item item : items) {
//            System.out.println(item);
//        }

        ObservableList<ItemData> itemData = new ItemListParser().parse("xml/items.xml");
        for (ItemData item : itemData) {
            System.out.println(item);
        }
    }
}
