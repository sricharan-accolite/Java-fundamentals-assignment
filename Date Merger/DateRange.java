import java.time.LocalDate;
import java.util.Comparator;
public class DateRange {

  private LocalDate startDate;

  private LocalDate endDate;

  public DateRange(LocalDate startDate, LocalDate endDate) {
    this.startDate = startDate;
    this.endDate = endDate;
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
  static final Comparator<DateRange> StartDateComparator = (DateRange o1, DateRange o2) -> {
    if (o1.getStartDate() != null && o2.getStartDate() != null) {
      if (o1.getStartDate().isBefore(o2.getStartDate())) {
        return -1;
      }
      else {
        return o1.getStartDate().isAfter(o2.getStartDate()) ? 1 : 0;
      }
    }
    else if (o1.getStartDate() != null && o2.getStartDate() == null) {
      return -1;
    }
    else if (o1.getStartDate() == null && o2.getStartDate() != null) {
      return 1;
    }
    else {
      return 0;
    }
  };

}