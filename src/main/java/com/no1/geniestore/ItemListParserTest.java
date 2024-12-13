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

        List<Order> orders = new OrderListParser().parse("xml/orders.xml");
        for (Order order : orders) {
            System.out.println(order);
        }
    }
}
