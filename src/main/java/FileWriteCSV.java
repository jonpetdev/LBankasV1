import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

public class FileWriteCSV {
   public String fullString;

    public void viewList(String dateFrom, String dateTo){
        SendRequest sr=new SendRequest();
        ParseXMLString pxmls=new ParseXMLString();
        pxmls.parseXML(sr.request(dateFrom,dateTo));


        String headeris= "Valiutos kodas; "+dateFrom+"; "+dateTo+"; Pokytis";
        fullString=headeris+"\n";
        for (Map.Entry me : pxmls.mapFirst.entrySet()) {
            if(pxmls.mapLast.containsKey(me.getKey())){
                float first= (float) me.getValue();
                float last= pxmls.mapLast.get(me.getKey());
                String line = me.getKey()+"; "+ me.getValue() + "; "+ pxmls.mapLast.get(me.getKey())+"; "+(last-first)+"\n";
                fullString = fullString+line;

            }
        }

    }

    public void saveFileCSV(String irasas) throws IOException {


        File file = new File(LocalDateTime.now() +".csv");

        FileWriter writer = new FileWriter(file);
        writer.write(irasas);
        writer.flush();

    }
}
