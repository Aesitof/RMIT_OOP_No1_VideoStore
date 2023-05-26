package com.no1.geniestore.controllers;

import com.no1.geniestore.Parser;
import com.no1.geniestore.accounts.Account;
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
import java.util.ArrayList;
import java.util.List;

public class AccountListParser {
    private DocumentBuilder builder;

    /**
     * Construct a parser that can parse account lists
     */
    public AccountListParser() throws ParserConfigurationException
    {
        builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
    }

    /**
     * Parses an XML file containing an account list. returns an array list
     * containing all accounts in the XML file
     */
    public List<Account> parse(String fileName) throws SAXException, IOException
    {
        // get the <items> root element
        Element root = builder.parse(new File(fileName)).getDocumentElement();
        return getAccounts(root);
    }

    /**
     * Obtains an array of accounts from a DOM element e an <accounts> element returns
     * the children of e, an array of all account
     */
    private List<Account> getAccounts(Element e)
    {
        List<Account> accounts = new ArrayList<Account>();
        NodeList children = e.getChildNodes();

        for (int i = 0; i < children.getLength(); i++)
        {
            Node childNode = children.item(i);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                if (childElement.getTagName().equals("account"))
                    accounts.add(getAccount(childElement));
            }
        }
        return accounts;
    }

    /**
     * Obtains an item from a DOM element. Argument passed e an <account> element
     * returns the order described by the given element
     */
    public Account getAccount(Element e)
    {
        NodeList children = e.getChildNodes();
        String id = null;
        String username = null;
        String name = null;
        String accountType = null;
        String address = null;
        String phone = null;
        String password = null;
        int totalReturnedItems = 0;
        int rewardPoints = 0;
        for (int j = 0; j < children.getLength(); j++)
        {
            Node childNode = children.item(j);
            if (childNode instanceof Element)
            {
                Element childElement = (Element) childNode;
                String tagName = childElement.getTagName();
                if (tagName.equals("id")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    id = data;
                }
                else if (tagName.equals("username"))
                {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    username = data;
                }
                else if (tagName.equals("name")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    name = data;
                }
                else if (tagName.equals("accountType")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    accountType = data;
                }
                else if (tagName.equals("address")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    address = data;
                }
                else if (tagName.equals("phone")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    phone = data;
                }
                else if (tagName.equals("password")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    password = data;
                }
                else if (tagName.equals("totalReturnedItems")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    totalReturnedItems = Integer.parseInt(data);
                }
                else if (tagName.equals("rewardPoints")) {
                    String data = ((Text) childElement.getFirstChild()).getData();
                    rewardPoints = Integer.parseInt(data);
                }
            }
        }
        return new Account(id, username, name, accountType, address, phone, password, totalReturnedItems, rewardPoints);
    }

    /**
     * Save the in-use list of Account to the XML file when needed
     * @param accounts
     * @throws ParserConfigurationException
     */
    public static void accountsToXML(List<Account> accounts) throws ParserConfigurationException, FileNotFoundException, TransformerException {
        // new DOM
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.newDocument();
        // root Element
        Element root = document.createElement("accounts");
        document.appendChild(root);

        // Create child nodes
        for (Account account : accounts) {
            root.appendChild(accountToXML(account, document, "account"));
        }

        Parser.writeXml(document, new FileOutputStream("xml/accounts.xml"));
    }

    public static Element accountToXML(Account newAccount, Document document, String tagName) {
        Element account = document.createElement(tagName);

        Element id = document.createElement("id");
        id.setTextContent(newAccount.getId());
        account.appendChild(id);

        Element username = document.createElement("username");
        username.setTextContent(newAccount.getUsername());
        account.appendChild(username);

        Element name = document.createElement("name");
        name.setTextContent(newAccount.getName());
        account.appendChild(name);

        Element accountType = document.createElement("accountType");
        accountType.setTextContent(newAccount.getAccountType());
        account.appendChild(accountType);

        Element address = document.createElement("address");
        address.setTextContent(newAccount.getAddress());
        account.appendChild(address);

        Element phone = document.createElement("phone");
        phone.setTextContent(newAccount.getPhone());
        account.appendChild(phone);

        Element password = document.createElement("password");
        password.setTextContent(newAccount.getPassword());
        account.appendChild(password);

        Element totalReturnedItems = document.createElement("totalReturnedItems");
        totalReturnedItems.setTextContent(String.valueOf(newAccount.getTotalReturnedItems()));
        account.appendChild(totalReturnedItems);

        Element rewardPoints = document.createElement("rewardPoints");
        rewardPoints.setTextContent(String.valueOf(newAccount.getRewardPoints()));
        account.appendChild(rewardPoints);

        return account;
    }


}
