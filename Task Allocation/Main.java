import java.util.*;
import java.time.LocalDate;
import java.util.stream.Collectors;
public class Main{
    
    public static void main(String[]args){
        List<Plan> oldPlanList = new ArrayList<>();
    oldPlanList.add(new Plan(101, LocalDate.of(2019, 5, 10), LocalDate.of(2019, 5, 20)));
    oldPlanList.add(new Plan(102, LocalDate.of(2019, 5, 10), LocalDate.of(2019, 5, 20)));
    oldPlanList.add(new Plan(103, LocalDate.of(2019, 5, 10), LocalDate.of(2019, 5, 20)));
    oldPlanList.add(new Plan(104, LocalDate.of(2019, 5, 10), LocalDate.of(2019, 5, 20)));
    oldPlanList.add(new Plan(105, LocalDate.of(2019, 5, 10), LocalDate.of(2019, 5, 20)));
    oldPlanList.add(new Plan(106, LocalDate.of(2019, 5, 10), LocalDate.of(2019, 5, 20)));
    oldPlanList.add(new Plan(107, LocalDate.of(2019, 5, 10), LocalDate.of(2019, 5, 20)));

    List<Plan> newPlanList = new ArrayList<>();
    newPlanList.add(new Plan(101, LocalDate.of(2019, 5, 1), LocalDate.of(2019, 5, 5)));
    newPlanList.add(new Plan(102, LocalDate.of(2019, 5, 5), LocalDate.of(2019, 5, 15)));
    newPlanList.add(new Plan(103, LocalDate.of(2019, 5, 5), LocalDate.of(2019, 5, 25)));
    newPlanList.add(new Plan(104, LocalDate.of(2019, 5, 15), LocalDate.of(2019, 5, 18)));
    newPlanList.add(new Plan(105, LocalDate.of(2019, 5, 15), LocalDate.of(2019, 5, 25)));
    newPlanList.add(new Plan(106, LocalDate.of(2019, 5, 25), LocalDate.of(2019, 5, 30)));
    newPlanList.add(new Plan(107, LocalDate.of(2019, 5, 12), LocalDate.of(2019, 5, 13)));
    newPlanList.add(new Plan(107, LocalDate.of(2019, 5, 16), LocalDate.of(2019, 5, 18)));

    List<Plan> cancelledPlanPeriods = new ArrayList<>();
    List<Plan> cancelledPlanPeriodsForTask = new ArrayList<>();
    SortedSet<Integer> s = new TreeSet<>();
    
    for(Plan p:oldPlanList){
        s.add(p.getTaskId());
    }
    Iterator<Integer> i = s.iterator();
    while (i.hasNext()){
        
        int id = i.next();
        List<Plan> oldPlans = new ArrayList<>();
        List<Plan> newPlans = new ArrayList<>();
        for(Plan p:oldPlanList){
            if(p.getTaskId()==id)
            oldPlans.add(p);
        }
        for(Plan p:newPlanList){
            if(p.getTaskId()==id)
            newPlans.add(p);
        }
        for(Plan oldPlan:oldPlans){
            for(Plan newPlan:newPlans){
            if (oldPlan.getStartDate().isAfter(newPlan.getEndDate()) || oldPlan.getEndDate().isBefore(newPlan.getStartDate())) {
                cancelledPlanPeriodsForTask.add(new Plan(id, oldPlan.getStartDate(), oldPlan.getEndDate()));
            }
            else {
                if (newPlan.getStartDate().isAfter(oldPlan.getStartDate()) && newPlan.getStartDate().isBefore(oldPlan.getEndDate())) {
                  cancelledPlanPeriodsForTask.add(new Plan(id, oldPlan.getStartDate(), newPlan.getStartDate().minusDays(1L)));
                }
                if (newPlan.getEndDate().isAfter(oldPlan.getStartDate()) && newPlan.getEndDate().isBefore(oldPlan.getEndDate())) {
                  cancelledPlanPeriodsForTask.add(new Plan(id, newPlan.getEndDate().plusDays(1L), oldPlan.getEndDate()));
                }
          
            }
            }
        }
        List<LocalDate> dates = new ArrayList<>();
        for(Plan p:cancelledPlanPeriodsForTask){
            for(LocalDate ld:p.getStartAndEndDate()){
               dates.add(ld) ;
            }
        }
        
        Collections.sort(dates);
        for (int ind = 0; ind < dates.size(); ind += 2) {
          cancelledPlanPeriods.add(new Plan(id, dates.get(ind), dates.get(ind + 1)));
        }
        cancelledPlanPeriodsForTask.clear();
    }
    System.out.println("TaskID      StartDate       EndDate");
    for(Plan p:cancelledPlanPeriods){
        System.out.println(p.getTaskId()+"      "+p.getStartDate()+"       "+p.getEndDate());
    }

    }
}