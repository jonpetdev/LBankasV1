


import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Main {
    public static void main(String []args) throws ParseException {


        if(args.length == 0 || args.length == 1 || args.length ==2 ){
            System.out.println("No parameters are set!");
            System.exit(0);
        }else if(args.length >3){
            System.out.println("To much parameters!");
            System.exit(0);
        }else {


            String dateFrom = args[0];
            String dateTo = args[1];
            String save = args[2];

            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calFrom = Calendar.getInstance();
            Calendar calTo = Calendar.getInstance();
            calFrom.setTime(format.parse(dateFrom));
            if ((calFrom.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (Calendar.DAY_OF_WEEK == Calendar.SUNDAY )) {
                System.out.println("dateFrom is a weekend !");
            } else if ((calTo.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) || (Calendar.DAY_OF_WEEK == Calendar.SUNDAY )) {
                System.out.println("dateTo is a weekend !");
            }else{
                FileWriteCSV fileWriteView = new FileWriteCSV();

                if (!dateFrom.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    System.out.println("Bad dateFrom format. Need using eg. \"2019-01-01\" ");
                } else if (!dateTo.matches("\\d{4}-\\d{2}-\\d{2}")) {
                    System.out.println("Bad dateTo format. Need using eg. \"2019-01-01\" ");
                } else if (save.equals("view")) {
                    fileWriteView.viewList(dateFrom, dateTo);
                    System.out.println(fileWriteView.fullString);

                } else if (save.equals("save")) {
                    fileWriteView.viewList(dateFrom, dateTo);
                    try {
                        fileWriteView.saveFileCSV(fileWriteView.fullString);
                        System.out.println("File saved");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println("Bad 3 parameter !. PARAMETER: \"save\" and \"view\"");
                }
            }
        }

    }
}
