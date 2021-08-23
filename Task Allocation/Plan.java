import java.time.LocalDate;

import java.util.*;

public class Plan {

  private Integer taskId;

  private LocalDate startDate;

  private LocalDate endDate;

  public Plan(Integer taskId, LocalDate startDate, LocalDate endDate) {
    this.taskId = taskId;
    this.startDate = startDate;
    this.endDate = endDate;
  }

  public Integer getTaskId() {
    return taskId;
  }

  public void setTaskId(Integer taskId) {
    this.taskId = taskId;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }
  public List<LocalDate> getStartAndEndDate() {
    return Arrays.asList(this.getStartDate(), this.getEndDate());
  }
 
}
