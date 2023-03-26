import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * This program returns the seconds elapsed from the current time when the program is started.
 * the initial value of the program can be modified with the {@code resultInAdd()} method.
 * With its {@code runAge()} abstract method, which is connected to the {@code TimerTask run()} method, it returns continuous accurate results.
 * @see java.text.DecimalFormat
 * @see java.text.ParseException
 * @see java.text.SimpleDateFormat
 * @see java.util.Calendar
 * @see java.util.Date
 * @see java.util.TimerTask
 * @see java.util.concurrent.TimeUnit
 * @since 1.1
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public abstract class MZElapsedSeconds
extends TimerTask{
    protected SimpleDateFormat simpleDateFormat;
    private static DecimalFormat decimalFormt = new DecimalFormat("###");
    private static Date date = new Date();
    private String resultin;
    private String resultout;

    public MZElapsedSeconds(SimpleDateFormat simpleDateFormat){
        this.simpleDateFormat = simpleDateFormat;
        resultin = simpleDateFormat.format(date);
    }

    private long dateToSeconds(String str, SimpleDateFormat format)
    throws ParseException{
        date = format.parse(str);
        return TimeUnit.MILLISECONDS.toSeconds(date.getTime());
    }

    @Override
    public void run(){
        try{
            resultout = (decimalFormt.format(dateToSeconds(simpleDateFormat.format(Calendar.getInstance().getTime()), simpleDateFormat)-dateToSeconds(resultin, simpleDateFormat)));
            runAge();
        }
        catch(ParseException e){
            e.printStackTrace();
        }
    }

    public abstract void runAge();
    
    public String resultOut(){
        return resultout;
    }

    public String resultIn(){
        return resultin;
    }

    public void resultInAdd(String resultin){
        this.resultin=resultin;
    }
}
