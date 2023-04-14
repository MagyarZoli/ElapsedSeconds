package mz;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * This program returns the seconds since the program was started.
 * the initial value of the program can be changed with the {@code setResultIn()} method.
 * With its inherited overridden {@code run()} method linked to the {@code TimerTask run()} method, it consistently returns accurate results.
 * @see java.text.DecimalFormat
 * @see java.text.ParseException
 * @see java.text.SimpleDateFormat
 * @see java.util.Calendar
 * @see java.util.Date
 * @see java.util.TimerTask
 * @see java.util.concurrent.TimeUnit
 * @since 1.2
 * @author <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
public class ElapsedSeconds
extends TimerTask{

    /**
     * Based on the SimpleDateFormat specified as an argument, 
     * it will return the result of how many seconds have passed between two periods.
     */
    private SimpleDateFormat simpleDateFormat;

    /**
     * Separated by 3 decimal places.
     */
    private final DecimalFormat decimalFormt = new DecimalFormat("###");
    
    /**
     * Call Date class to private field.
     */
    private Date date = new Date();

    /**
     * The value based on which it calculates how many seconds have passed or must pass from this period.
     * the value must be entered in String type, the class converts it with SimpleDateFormat.
     */
    private String resultin;

    /**
     * Returns a result in String type.
     */
    private String resultout;

    /**
     * The first date is the current date, and the second date is what the user specifies afterwards. The date at the moment the program is called will be the default second date.
     * @param simpleDateFormat based on this format, it uniformly transforms the dates for further calculations.
     */
    public ElapsedSeconds(SimpleDateFormat simpleDateFormat){
        this.simpleDateFormat = simpleDateFormat;
        resultin = simpleDateFormat.format(date);
    }

    /**
     * Enter date for calculations.
     * The program start date will be the default date value.
     * @param resultin 
     */
    public void setResultIn(String resultin){
        this.resultin=resultin;
    }

    /**
     * Method for querying results.
     * @return
     */
    public String getResultOut(){
        return this.resultout;
    }

    /**
     * Method for querying an initial value.
     * @return
     */
    public String getresultIn(){
        return this.resultin;
    }

    /**
     * Inherited TimerTask method in which the date is calculated every second.
     * creates current date {@code Calendar.getInstance().getTime()}
     * {@code dateToSeconds} method counts to the dated seconds.
     * @see ElapsedSeconds#dateToSeconds(String, SimpleDateFormat)
     * @see SimpleDateFormat#format(Date)
     * @see Calendar#getInstance()
     * @throws ParseException error event generated when converting an incorrect date
     */
    @Override
    public void run(){
        try{
            resultout = (
                decimalFormt.format(
                    dateToSeconds(simpleDateFormat.format(Calendar.getInstance().getTime()), simpleDateFormat)-
                    dateToSeconds(resultin, simpleDateFormat)
                )
            );
        }
        catch(ParseException e){
            e.printStackTrace();
        }
    }

    /**
     * Method counts to the dated seconds.
     * @param str date in String type
     * @param format formatting type
     * @return date converted to seconds
     * @see TimeUnit#MILLISECONDS
     * @throws ParseException error event generated when converting an incorrect date
     */
    private long dateToSeconds(String str, SimpleDateFormat format)
    throws ParseException{
        date = format.parse(str);
        return TimeUnit.MILLISECONDS.toSeconds(date.getTime());
    }
}
