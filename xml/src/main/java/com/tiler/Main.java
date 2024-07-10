package com.tiler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static List<Employee> parseXML(String fileName) {
        List<Employee> employees = new ArrayList<>();
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(fileName);

            Element rootElement = document.getDocumentElement();
            NodeList nodeList = rootElement.getChildNodes();

            for (int i = 0; i < nodeList.getLength(); i++) {
                if (nodeList.item(i) instanceof Element) {
                    Element employeeElement = (Element) nodeList.item(i);

                    long id = Long.parseLong(employeeElement.getElementsByTagName("id").item(0).getTextContent());
                    String firstName = employeeElement.getElementsByTagName("firstName").item(0).getTextContent();
                    String lastName = employeeElement.getElementsByTagName("lastName").item(0).getTextContent();
                    String country = employeeElement.getElementsByTagName("country").item(0).getTextContent();
                    int age = Integer.parseInt(employeeElement.getElementsByTagName("age").item(0).getTextContent());

                    Employee employee = new Employee(id, firstName, lastName, country, age);
                    employees.add(employee);
                }
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }

        return employees;
    }

    public static <T> String listToJson(List<T> list) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<T>>() {}.getType();
        return gson.toJson(list, listType);
    }

    public static void writeString(String filename, String content) {
        try (FileWriter writer = new FileWriter(filename)) {
            writer.write(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        String fileName = "data.xml";
        List<Employee> employees = parseXML(fileName);

        String json = listToJson(employees);
        writeString("json.json", json);
    }
}
