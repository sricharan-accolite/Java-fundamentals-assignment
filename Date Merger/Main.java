import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.io.*;
public class Main{
    
    public static List<DateRange> mergeDateRange(List<DateRange> dateRanges){
        Stack<DateRange> st = new Stack<>();
        List<DateRange> res = new ArrayList<>();
        st.push(dateRanges.get(0));
        int i=1;
        while(i<dateRanges.size()){
            DateRange dt1 = dateRanges.get(i);
            DateRange dt2 = st.peek();
            if(dt1.getStartDate().isAfter(dt2.getStartDate()) && dt1.getStartDate().isBefore(dt2.getEndDate())){
                dt2.setEndDate( dt1.getEndDate().isAfter(dt2.getEndDate()) ? dt1.getEndDate() : dt2.getEndDate());
                st.pop();
                st.push(dt2);
                i++;
            }
            else if(dt1.getStartDate().isAfter(dt2.getEndDate())){
                res.add(st.pop());
                st.push(dt1);
                i++;
            }
            
        }
        res.add(st.pop());
        return res;
        
    }
    public static void main(String []args) throws Exception{
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String startdate = "01/01/2019";
        String enddate = "20/01/2019";
        
        List<DateRange> l =new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int n;
        System.out.println("Enter number of date ranges");
        n = Integer.parseInt(br.readLine());
        
        while(n-- > 0){
            System.out.println("Enter start date and end date with a space in between");
             String s = br.readLine();
  
            l.add(new DateRange( LocalDate.parse(s.split(" ")[0], formatter),LocalDate.parse(s.split(" ")[1], formatter)));
        }

        Collections.sort(l, DateRange.StartDateComparator);
        l = mergeDateRange(l);
        System.out.println("StartDate   EndDate");
        for(DateRange dr:l){
            System.out.println(dr.getStartDate()+"  "+dr.getEndDate());
        }
        
        
    }
}
