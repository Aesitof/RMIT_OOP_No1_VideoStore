package com.no1.geniestore;

import com.no1.geniestore.products.Item;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class ItemListParserTest {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
//        List<Item> items = new ItemListParser().parse("xml/items.xml");
//        for (Item item : items) {
//            System.out.println(item);
//        }

        HashMap<Item, Integer> itemList = new ItemListParser().parseStockList("xml/items.xml");
        for (Item item : itemList.keySet()) {
            System.out.print(item);
            System.out.println(" + remaining: " + itemList.get(item));
        }
    }
}
