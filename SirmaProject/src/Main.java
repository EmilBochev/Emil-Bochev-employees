import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ArrayList<Employee> employees=new ArrayList<Employee>();
        try {
            File myObj = new File("EmployeesData.txt");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String[] data = myReader.nextLine().split(", ");
                int empID=Integer.parseInt(data[0]);
                int projectID=Integer.parseInt(data[1]);
                LocalDate dateFrom=LocalDate.parse(data[2]);
                LocalDate dateTo;
                if(data[3].equals("NULL"))
                {
                    dateTo=LocalDate.now();
                }
                else {
                    dateTo = LocalDate.parse(data[3]);
                }
                long days= ChronoUnit.DAYS.between(dateFrom,dateTo);
                employees.add(new Employee(empID,projectID,days));

            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        employees.sort(new projectSorter());
        int count = 0;
        for (int i = 0; i < employees.size(); i++) {
            if (count < 2) {
                System.out.println(employees.get(i));
            }
            count++;

            if (employees.get(i).getProjectID() != employees.get(i + 1).getProjectID()) {
                count = 0;
                System.out.println();
            }
        }
        }


    }

class Employee
{
    int ID;
    int projectID;
    long daysWorking;
    public Employee(int ID,int projectID,long daysWorking)
    {
        this.ID=ID;
        this.projectID=projectID;
        this.daysWorking=daysWorking;
    }

    public int getID() {
        return ID;
    }

    public int getProjectID() {
        return projectID;
    }

    public long getDaysWorking() {
        return daysWorking;
    }
    @Override
    public String toString() {
        return ("Employee ID:"+this.getID()+
                " Project ID: "+ this.getProjectID() +
                " Days: "+ this.getDaysWorking());
    }
}
class projectSorter implements Comparator<Employee>
{
    @Override
    public int compare(Employee o1, Employee o2) {
        final int projectComparison = o1.getProjectID()- o2.getProjectID();
        if (projectComparison != 0) {
            return projectComparison;
        }
        return Long.compare(o2.getDaysWorking(), o1.getDaysWorking()) ;
    }
}