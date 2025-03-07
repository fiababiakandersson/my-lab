package se.yrgo.schedule.data;

import java.sql.*;
import java.util.*;

import se.yrgo.schedule.domain.*;
import se.yrgo.schedule.exceptions.*;

/**
 * An implementation of the Assignments interface
 */
public class DatabaseAssignments implements Assignments {

  private static final String SELECT_ALL = new StringBuilder(
      "select day, name, school_name, address from schedule")
      .append(" join substitute on schedule.substitute_id=substitute.substitute_id")
      .append(" join school on schedule.school_id = school.school_id")
      .toString();
  private static final String SELECT_WITH_SUBSTITUTE_ID = new StringBuilder(
      "select day, name, school_name, address from schedule")
      .append(" join substitute on schedule.substitute_id=substitute.substitute_id")
      .append(" join school on schedule.school_id = school.school_id WHERE substitute.substitute_id=")
      .toString();

  DBHelper db;

  DatabaseAssignments() {
    db = new DBHelper();
  }

  public List<Assignment> all() throws AccessException {
    List<Assignment> result = new ArrayList<>();
    try {
      ResultSet rs = db.fetch(SELECT_ALL);
      while (rs.next()) {
        result
            .add(new Assignment(new Substitute(rs.getString("name")),
                rs.getString("day"),
                new School(rs.getString("school_name"),
                    rs.getString("address"))));
      }
      return result;
    } catch (SQLException sqle) {
      throw new AccessException("Problem fetching all assignments", sqle);
    }
  }

  public List<Assignment> forTeacher(String teacherId) throws AccessException {
    List<Assignment> result = new ArrayList<>();
    try {
      ResultSet rs = db.fetch(SELECT_WITH_SUBSTITUTE_ID + teacherId);
      while (rs.next()) {
        result.add(new Assignment(new Substitute(rs.getString("name")),
            rs.getString("day"),
            new School(rs.getString("school_name"),
                rs.getString("address"))));
      }
      return result;
    } catch (SQLException sqle) {
      throw new AccessException("Problem fetching all assignments", sqle);
    }
  }

  public List<Assignment> at(String date) throws AccessException {
    List<Assignment> result = new ArrayList<>();
    try {
      ResultSet rs = db.fetch(SELECT_ALL + " where schedule.day = '" + date + " 08:00:00'");
      while (rs.next()) {
        result.add(new Assignment(new Substitute(rs.getString("name")),
            rs.getString("day"),
            new School(rs.getString("school_name"),
                rs.getString("address"))));
      }
      return result;
    } catch (SQLException sqle) {
      throw new AccessException("Problem fetching all assignments", sqle);
    }
  }

  public List<Assignment> forTeacherAt(String teacherId, String date) throws AccessException {
    List<Assignment> result = new ArrayList<>();
    try {
      ResultSet rs = db.fetch(SELECT_WITH_SUBSTITUTE_ID + teacherId + " and schedule.day='" + date + " 08:00:00'");
      System.out.println(SELECT_WITH_SUBSTITUTE_ID + teacherId + " and schedule.day='" + date + " 08:00:00'");
      while (rs.next()) {
        result.add(new Assignment(new Substitute(rs.getString("name")),
            rs.getString("day"),
            new School(rs.getString("school_name"),
                rs.getString("address"))));
      }
      return result;
    } catch (SQLException sqle) {
      throw new AccessException("Problem fetching all assignments", sqle);
    }
  }

}
