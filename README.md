[![Apache License, Version 2.0, January 2004](https://img.shields.io/github/license/apache/maven.svg?label=License)][license]

# ElapsedSeconds

## Description
Convert date to seconds.

## Dependency
**Maven:**
```xml
<dependency>
    <groupId>github.magyarzoli</groupId>
    <artifactId>ElapsedSeconds</artifactId>
    <version>1.0.0</version>
</dependency>
```

## Features
**ElapsedSeconds** that extends **TimerTask**. This class to measure the elapsed time
in seconds between two points in time. Calculating the difference between two time points using
**SimpleDateFormat** and **Calendar** classes, and it's
using a **DecimalFormat** object to format the result. The **ElapsedSeconds** class extends
**TimerTask**, which implies that it can be used as a task scheduled to run at specific intervals using a
**Timer**.

## Example
**run** method is an overridden method from **TimerTask**. It calculates the
difference in seconds between the current time and the timestamp stored in **resultIn** using the
**dateToSeconds** method. The difference is then formatted using the **decimalFormat** object and
stored in the **resultOut** variable.

### Authors
Magyar Zoltán

### Contact
magyarz95@gmail.com

[license]: https://www.apache.org/licenses/LICENSE-2.0