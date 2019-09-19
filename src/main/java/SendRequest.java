import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
public class SendRequest {
    public String request(String dateFrom, String dateTo) {
        try {
            String url = "http://www.lb.lt/webservices/fxrates/FxRates.asmx?op=getFxRatesForCurrency";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("POST");
            con.setRequestProperty("Content-Type","application/soap+xml; charset=utf-8");


            String tp="EU";
            String ccy="";
            String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
                    "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
                    "  <soap12:Body>\n" +
                    "    <getFxRatesForCurrency xmlns=\"http://www.lb.lt/WebServices/FxRates\">\n" +
                    "      <tp>"+tp+"</tp>\n" +
                    "      <ccy>"+ccy+"</ccy>\n" +
                    "      <dtFrom>"+dateFrom+"</dtFrom>\n" +
                    "      <dtTo>"+dateTo+"</dtTo>\n" +
                    "    </getFxRatesForCurrency>\n" +
                    "  </soap12:Body>\n" +
                    "</soap12:Envelope>";
            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());
            wr.writeBytes(xml);
            wr.flush();
            wr.close();
            String responseStatus = con.getResponseMessage();
            System.out.println(responseStatus);
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }
}
