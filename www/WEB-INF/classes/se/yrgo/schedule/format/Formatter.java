package se.yrgo.schedule.format;

import java.util.*;

import se.yrgo.schedule.domain.*;

public interface Formatter {
  public String format(List<Assignment> assignments);
}
