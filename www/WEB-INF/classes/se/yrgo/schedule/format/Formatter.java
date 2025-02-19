package se.yrgo.schedule.format;

import java.util.List;

import se.yrgo.schedule.*;
import se.yrgo.schedule.domain.*;

public interface Formatter {
  public String format(List<Assignment> assignments);
}
