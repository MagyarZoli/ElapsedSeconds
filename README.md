# ElapsedSeconds

## Description
Convert date to seconds.

## Features
This program returns the seconds since the program was started.
the initial value of the program can be changed with the `setResultIn()` method.
With its inherited overridden `run()` method linked to the `TimerTask run()` method, it consistently returns accurate results.

## Example
Inherited TimerTask method in which the date is calculated every second.
creates current date `Calendar.getInstance().getTime()`
`dateToSeconds` method counts to the dated seconds.
```java
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
```

Method counts to the dated seconds.
`ParseException` error event generated when converting an incorrect date
```java 
    private long dateToSeconds(String str, SimpleDateFormat format)
    throws ParseException{
        date = format.parse(str);
        return TimeUnit.MILLISECONDS.toSeconds(date.getTime());
    }
```

### Authors
Magyar Zoltán

### Contact
magyarz95@gmail.com