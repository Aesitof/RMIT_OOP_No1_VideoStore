/* Acknowledgment:
- OOP - INTE2512 - week 10 Files and Streams
- write to XML: https://mkyong.com/java/how-to-create-xml-file-in-java-dom/
* */

package com.no1.geniestore.controllers;

import com.no1.geniestore.storage.Parser;
import com.no1.geniestore.accounts.Account;
import com.no1.geniestore.products.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static com.no1.geniestore.products.ManagementSystem.orderList;
import static com.no1.geniestore.storage.Storage.ORDERS_FILE_PATH;
import static java.util.Objects.requireNonNull;

public class OrderListParser {
    private DocumentBuilder builder;

    /**
     * Construct a parser that can parse order lists
     */
    public OrderListParser() throws ParserConfigurationException {
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }
    
    /**
     * Reads an accounts file from the given file path and returns an {@code Optional} containing the parsed accounts list.
     * If the file is not found or an error occurs during parsing, {@code Optional.empty()} is returned.
     *
     * @param filePath the path to the accounts file; cannot be null.
     */
    public Optional<ArrayList<Order>> readOrdersFile(String filePath) {
        requireNonNull(filePath);
        
        try {
            ArrayList<Order> orders = parse(ORDERS_FILE_PATH);
            return Optional.of(orders);
        } catch (ParserConfigurationException | SAXException | IOException | ParseException e) {
            return Optional.empty();
        }
    }
    
    /**
     * Parses an XML file containing an order list. returns an array list
     * containing all orders in the XML file
     */
    public ArrayList<Order> parse(String fileName) throws SAXException, IOException, ParserConfigurationException, ParseException {
        // get the <items> root element
        Element root = builder.parse(new File(fileName)).getDocumentElement();
        if (root == null) {
            throw new SAXException(fileName + " is empty");
        }
        return (ArrayList<Order>) getOrders(root);
    }

    /**
     * Obtains an array of orders from a DOM element e an <orders> element returns
     * the children of e, an array of all orders
     */
    private List<Order> getOrders(Element e) throws ParserConfigurationException, ParseException {
        List<Order> orders = new ArrayList<Order>();
        NodeList children = e.getChildNodes();

        for (int i = 0; i < children.getLength(); i++)
        {
            Node childNode = children.item(i);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                if (childElement.getTagName().equals("order"))
                    orders.add(getOrder(childElement));
            }
        }
        return orders;
    }

    /**
     * Obtains an order from a DOM element. Argument passed e an <order> element
     * returns the order described by the given element
     */
    private Order getOrder(Element e) throws ParserConfigurationException, ParseException {
        NodeList children = e.getChildNodes();
        String orderID = null;
        Account owner = null;
        HashMap<Item, OrderDetails> itemList = null;
        double total = 0.0;
        double totalDiscount = 0.0;
        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();

                if (tagName.equals("orderID")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    orderID = data;
                }
                else if (tagName.equals("owner")) {
                    owner = new AccountListParser().getAccount(childElement);
                }
                else if (tagName.equals("itemList")) {
                    itemList = getItemList(childElement);
                }
                else if (tagName.equals("total")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    total = Double.parseDouble(data);
                }
                else if (tagName.equals("totalDiscount")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    totalDiscount = Double.parseDouble(data);
                }
            }
        }
        return new Order(orderID, owner, itemList, total, totalDiscount);
    }

    /**
     * Obtains HashMap<Item, OrderDetails> for the getOrder method
     * @param e
     * @return
     */
    public HashMap<Item, OrderDetails> getItemList(Element e) throws ParserConfigurationException, ParseException {
        HashMap<Item, OrderDetails> itemList = new HashMap<>();
        NodeList children = e.getChildNodes(); // list of singleItem

        for (int i = 0; i < children.getLength(); i++)
        {
            Node childNode = children.item(i); //
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                if (childElement.getTagName().equals("singleItem")) {
                    Item item = getOrderItem(childElement);
                    OrderDetails orderDetails = getOrderDetails(childElement);
                    itemList.put(item, orderDetails);
                }

            }
        }

        return itemList;
    }

    public Item getOrderItem(Element e) throws ParserConfigurationException {
        Item item = null;
        NodeList children = e.getChildNodes();

        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();
                if (tagName.equals("item"))
                    item = new ItemListParser().getItem(childElement);
            }
        }

        return item;
    }




    public OrderDetails getOrderDetails(Element e) throws ParseException {
        NodeList children = e.getChildNodes();
        Date returnDate = null;
        Date loanDate = null;
        int amount = 0;
        boolean isReturned = false;
        double discount = 0.0;

        //Create the Date format using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();
                if (tagName.equals("orderDetail")) {
                    NodeList orderDetailChildren = childElement.getChildNodes();

                    for (int i = 0; i < orderDetailChildren.getLength(); i++) {
                        Node orderDetailChildNode = orderDetailChildren.item(i);
                        if (orderDetailChildNode instanceof Element) {
                            Element orderDetailChildElement = (Element) orderDetailChildNode;
                            String childTagName = orderDetailChildElement.getTagName();
                            String data = ((Text) orderDetailChildElement.getFirstChild()).getData();
                            if (childTagName.equals("returnDate")) {
                                returnDate = dateFormat.parse(data);
                            }
                            else if (childTagName.equals("loanDate")) {
                                loanDate = dateFormat.parse(data);
                            }
                            else if (childTagName.equals("amount")) {
                                amount = Integer.parseInt(data);
                            }
                            else if (childTagName.equals("isReturned")) {
                                isReturned = (data.equals("true")) ? true : false;
                            }
                            else if (childTagName.equals("discount")) {
                                discount = Double.parseDouble(data);
                            }
                        }
                    }

                }

            }
        }

        return new OrderDetails(returnDate, loanDate, amount, isReturned, discount);
    }

    /**
     * Save the in-use list of Order to the XML file when needed
     * @param
     * @throws ParserConfigurationException
     */
    public static void saveOrderFile() throws ParserConfigurationException, FileNotFoundException, TransformerException {
        // new DOM
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        // root Element
        Element root = document.createElement("orders");
        document.appendChild(root);

        // Create child nodes
        for (Order order : orderList) {
            root.appendChild(orderToXML(order, document));
        }

        Parser.writeXml(document, new FileOutputStream(ORDERS_FILE_PATH));
    }

    public static Element orderToXML(Order newOrder, Document document) {
        Element order = document.createElement("order");

        Element orderID = document.createElement("orderID");
        orderID.setTextContent(newOrder.getOrderID());
        order.appendChild(orderID);

        // owner (Account)
        order.appendChild(AccountListParser.accountToXML(newOrder.getOwner(), document, "owner"));

        Element itemList = document.createElement("itemList");
        for (Item orderItem : newOrder.getOrder().keySet()) {
            itemList.appendChild(itemListToXML(orderItem, newOrder.getOrder().get(orderItem), document));
        }
        order.appendChild(itemList);

        Element total = document.createElement("total");
        total.setTextContent(String.valueOf(newOrder.getTotal()));
        order.appendChild(total);

        Element totalDiscount = document.createElement("totalDiscount");
        totalDiscount.setTextContent(String.valueOf(newOrder.getTotalDiscount()));
        order.appendChild(totalDiscount);

        return order;
    }

    public static Element itemListToXML(Item orderItem, OrderDetails orderDetails, Document document) {
        Element singleItem = document.createElement("singleItem");

        // orderItem (Item)
        singleItem.appendChild(ItemListParser.orderItemToXML(orderItem, document));

        // orderDetails (OrderDetails)
        singleItem.appendChild(orderDetailsToXML(orderDetails, document));

        return singleItem;
    }

    public static Element orderDetailsToXML(OrderDetails newOrderDetail, Document document) {
        Element orderDetail = document.createElement("orderDetail");

        //Create the Date format using SimpleDateFormat
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

        Element returnDate = document.createElement("returnDate");
        returnDate.setTextContent(dateFormat.format(newOrderDetail.getReturnDate()));
        orderDetail.appendChild(returnDate);

        Element loanDate = document.createElement("loanDate");
        loanDate.setTextContent(dateFormat.format(newOrderDetail.getLoanDate()));
        orderDetail.appendChild(loanDate);

        Element amount = document.createElement("amount");
        amount.setTextContent(String.valueOf(newOrderDetail.getAmount()));
        orderDetail.appendChild(amount);

        Element isReturned = document.createElement("isReturned");
        isReturned.setTextContent(newOrderDetail.isReturned() == true ? "true" : "false");
        orderDetail.appendChild(isReturned);

        Element discount = document.createElement("discount");
        discount.setTextContent(String.valueOf(newOrderDetail.getDiscount()));
        orderDetail.appendChild(discount);


        return orderDetail;
    }


}
