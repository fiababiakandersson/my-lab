package se.yrgo.schedule.format;

import java.util.*;

import org.json.*;

import se.yrgo.schedule.domain.*;

public class JsonFormatter implements Formatter {

  /**
   * 
   * A class implementing the Formatter interface. Formats a List of Assignment
   * to JSON.
   **/

  private JSONObject JSONAssignment(Assignment assignment) {
    JSONObject JSONAssignment = new JSONObject();
    JSONAssignment.put("date", assignment.date());

    JSONObject JSONSubstitute = new JSONObject();
    JSONSubstitute.put("name", assignment.teacher().name());
    JSONAssignment.put("substitute", JSONSubstitute);

    JSONObject JSONSchool = new JSONObject();
    JSONSchool.put("school_name", assignment.school().name());
    JSONSchool.put("address", assignment.school().address());

    JSONAssignment.put("school", JSONSchool);

    return JSONAssignment;

  }

  public String format(List<Assignment> assignments) {
    if (assignments.size() == 0) {
      return "[]";
    } else {
      JSONArray JSON = new JSONArray();
      for (Assignment assignment : assignments) {
        JSON.put(JSONAssignment(assignment));
      }
      return JSON.toString(2);
    }
  }
}