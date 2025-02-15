package se.yrgo.schedule;

import java.util.*;

/**
 * A class implementing the Formatter interface. Formats a List of Assignment
 * to Json.
 *
 */
public class JsonFormatter implements Formatter {
  public String format(List<Assignment> assignments) {
    return "[ { \"some-key\": \"some-value\" } ]";
    // String jsonString = "";
    // JSONArray JSON = new JSONArray();

    // if (assignments.size() == 0) {
    // return "No assignments found";
    // }

    // for (Assignment assignment : assignments) {
    // JSON.put(JSONSchedule(assignment));
    // }

    // return JSON.toString(2);
  }
}
