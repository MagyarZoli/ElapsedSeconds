package github.magyarzoli;

import lombok.Data;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

/**
 * {@code ElapsedSeconds} that extends {@link java.util.TimerTask TimerTask}. This class to measure the elapsed time
 * in seconds between two points in time. Calculating the difference between two time points using
 * {@link java.text.SimpleDateFormat SimpleDateFormat} and {@link java.util.Calendar Calendar} classes, and it's
 * using a {@link java.text.DecimalFormat DecimalFormat} object to format the result. The ElapsedSeconds class extends
 * {@code TimerTask}, which implies that it can be used as a task scheduled to run at specific intervals using a
 * {@link java.util.Timer Timer}.
 * @since       1.0
 * @author      <a href=https://github.com/MagyarZoli>Magyar Zoltán</a>
 */
@Data
public class ElapsedSeconds
extends TimerTask {

    /**
     * This holds a reference to a SimpleDateFormat object, which is used to format and parse dates.
     */
    private SimpleDateFormat simpleDateFormat;

    /**
     * This holds a reference to a DecimalFormat object used for formatting numeric values.
     */
    private DecimalFormat decimalFormat = new DecimalFormat("###");

    /**
     * Date object representing a specific point in time.
     */
    private Date date = new Date();

    /**
     * This string holds the formatted timestamp when the ElapsedSeconds instance was created.
     */
    private String resultIn;

    /**
     * This string holds the formatted difference in seconds between the timestamp when the instance was created
     * and the current time.
     */
    private String resultOut;

    /**
     * Create an instance of the class. It initializes the {@code simpleDateFormat} instance variable and sets the
     * {@code resultIn} string to the formatted timestamp of the current time.
     * run Method (Override)
     * @param       simpleDateFormat which is used to format and parse dates.
     */
    public ElapsedSeconds(SimpleDateFormat simpleDateFormat) {
        this.simpleDateFormat = simpleDateFormat;
        resultIn = simpleDateFormat.format(date);
    }

    /**
     * {@code run} method is an overridden method from {@link java.util.TimerTask TimerTask}. It calculates the
     * difference in seconds between the current time and the timestamp stored in {@code resultIn} using the
     * {@code dateToSeconds} method. The difference is then formatted using the {@code decimalFormat} object and
     * stored in the {@code resultOut} variable.
     * <ul>
     *     <li>{@code run} method is executed, presumably within a class that implements the
     *     {@link java.lang.Runnable Runnable} interface or extends a thread class.</li>
     *     <li>Inside the {@code run} method, there's a {@code try} block to catch potential
     *     {@link java.text.ParseException ParseException} exceptions that might be thrown during the execution
     *     of the code.</li>
     *     <li>{@code decimalFormat} and {@code simpleDateFormat} are instances of
     *     {@link java.text.DecimalFormat DecimalFormat} and
     *     {@link java.text.SimpleDateFormat SimpleDateFormat}, respectively.</li>
     *     <li>First, the current date and time obtained from {@link java.util.Calendar Calendar}.
     *     {@link java.util.Calendar#getInstance() getInstance()}.{@link java.util.Calendar#getTime() getTime()}
     *     is formatted using {@code simpleDateFormat} and then passed to {@code dateToSeconds} to get the number
     *     of seconds since the Unix epoch.</li>
     *     <li>Second, the value of resultIn is passed to {@code dateToSeconds} to get the number of seconds since
     *     the Unix epoch.</li>
     *     <li>The difference between the two calculated values (seconds since Unix epoch) is computed.</li>
     *     <li>The result of the difference is formatted using {@code decimalFormat}.</li>
     *     <li>If a {@code ParseException} occurs during the execution of the code in the try block, it's caught,
     *     and the exception stack trace is printed using {@code e.printStackTrace()}.</li>
     * </ul>
     * @see         github.magyarzoli.ElapsedSeconds#dateToSeconds(String) dateToSeconds(String)
     */
    @Override
    public void run() {
        try {
            resultOut = decimalFormat.format(
                    dateToSeconds(simpleDateFormat.format(Calendar.getInstance().getTime()))
                           - dateToSeconds(resultIn));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    /**
     * {@code dateToSeconds} that takes a {@code String} representing a date as input, parses it using a
     * {@link java.text.SimpleDateFormat SimpleDateFormat}, and then converts the resulting {@link java.util.Date Date}
     * object to the number of seconds since the Unix epoch.
     * <ul>
     *     <li>The {@code SimpleDateFormat} class is used to parse the input str into a {@code Date} object.
     *     The initialization of the {@code SimpleDateFormat} instance is crucial, as it determines how the input
     *     string should be formatted for successful parsing.</li>
     *     <li>The parsed {@code Date} object is stored in a variable named date.</li>
     *     <li>The {@link java.util.Date#getTime() getTime()} method is called to get the time in milliseconds
     *     since the Unix epoch.</li>
     *     <li>{@link java.util.concurrent.TimeUnit TimeUnit}.
     *     {@link java.util.concurrent.TimeUnit#MILLISECONDS MILLISECONDS}.
     *     {@link  java.util.concurrent.TimeUnit#toSeconds(long) toSeconds()}
     *     method is used to convert the milliseconds into seconds. The result is returned as a long value.</li>
     * </ul>
     * @param       str which represents the input date.
     * @return      The result is displayed in seconds.
     * @throws      ParseException indicating that there might be an issue parsing the input string into a date.
     */
    private long dateToSeconds(String str)
    throws ParseException {
        date = simpleDateFormat.parse(str);
        return TimeUnit.MILLISECONDS.toSeconds(date.getTime());
    }
}