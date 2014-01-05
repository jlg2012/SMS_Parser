package Model;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: lester
 * Date: 05/01/14
 * Time: 19:23
 * To change this template use File | Settings | File Templates.
 */
public class SMS {
    String number;
    private String messageBody;
    private String date;

    public void setNumber(String number) {
        this.number = number;
    }

    public String getNumber() {
        return number;
    }

    public void setMessageBody(String messageBody) {
        this.messageBody = messageBody;
    }

    public String getMessageBody() {
        return messageBody;
    }

    public void appendMessageBody(String vmgLine) {
        if (messageBody == null) {
            messageBody = vmgLine;
        }

        else {
            messageBody = messageBody + vmgLine;
        }
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        String pattern = "dd.MM.yyyy HH:mm:ss";
        Date date;
        try {
            date = new SimpleDateFormat(pattern).parse(this.date.substring(5));

            return date.toString();
        }

        catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }
}
