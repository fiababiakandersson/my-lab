package se.yrgo.schedule.format;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

import org.w3c.dom.*;

import se.yrgo.schedule.domain.*;

public class XMLFormatter implements Formatter {

  @Override
  public String format(List<Assignment> assignments) {

    try {

      DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
      DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
      Document doc = docBuilder.newDocument();
      Element rootElement = doc.createElement("schedules");
      doc.appendChild(rootElement);

      for (Assignment assignment : assignments) {
        Element schedule = doc.createElement("schedule");
        schedule.setAttribute("date", assignment.date());

        // Create school element
        Element school = doc.createElement("school");

        Element schoolName = doc.createElement("school_name");
        schoolName.appendChild(doc.createTextNode(assignment.school().name()));

        Element schoolAddress = doc.createElement("address");
        schoolAddress.appendChild(doc.createTextNode(assignment.school().address()));

        // Append school details
        school.appendChild(schoolName);
        school.appendChild(schoolAddress);
        schedule.appendChild(school);

        // Create substitute element
        Element substitute = doc.createElement("substitute");

        Element name = doc.createElement("name");
        name.appendChild(doc.createTextNode(assignment.teacher().name())); 

        // Append substitute details
        substitute.appendChild(name);
        schedule.appendChild(substitute);

        // Append to root
        rootElement.appendChild(schedule);

      }

      StringWriter xml = new StringWriter();
      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      transformer
          .setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
              "2");
      DOMSource source = new DOMSource(doc);
      StreamResult result = new StreamResult(xml);
      transformer.transform(source, result);
      return xml.toString();

    } catch (ParserConfigurationException | TransformerException e) {
      return "XML Error";
    }

  }

}