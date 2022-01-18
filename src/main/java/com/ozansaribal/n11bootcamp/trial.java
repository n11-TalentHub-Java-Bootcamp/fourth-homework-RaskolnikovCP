package com.ozansaribal.n11bootcamp;

import com.ozansaribal.n11bootcamp.entity.Debt;
import com.ozansaribal.n11bootcamp.enums.EnumDebtType;
import org.hibernate.type.EnumType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.GregorianCalendar;

@SpringBootApplication
public class trial {

    @Temporal(TemporalType.DATE)
    Date date = new Date();

    private final static Date lateFeeDate1 = new Date(2018-1900,1,1);

    public static void main(String[] args){

        Debt debt = new Debt();

        EnumDebtType enumDebtType = EnumDebtType.LATE_FEE;

        //String str = enumDebtType.name();

        Date lateFeeDate = new Date(2018,1,3);

        Date firstDate = new Date(2018,1,3);

        Date secondDate = new Date(2018,1,5);

        Double diff = (double) ChronoUnit.DAYS.between(firstDate.toInstant(), secondDate.toInstant());

        System.out.println(diff);

        if(firstDate.before(lateFeeDate)){
            System.out.println("first before late fee");
        }
        else {
            System.out.println("first after late fee");
        }

        //debt.setDebtType(enumDebtType);

        System.out.println(enumDebtType);

        for(EnumDebtType c : EnumDebtType.values()){
            System.out.println(c);
        }

        double result = 8;

        System.out.println(ResponseEntity.ok(result));

        debt.setDebtType(EnumDebtType.NORMAL);

        EnumDebtType enm = debt.getDebtType();

        if(enm == EnumDebtType.NORMAL){System.out.println("normal");}
        else {System.out.println("late fee");}

        LocalDate date1 = LocalDate.now();

        int year = date1.getYear();

        int month = date1.getMonthValue();

        int day = date1.getDayOfMonth();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        System.out.println(date1.format(formatter));

        System.out.println("year: " + year + " month: " + month + " day: " + day);

        trial trial = new trial();

        Date date2 = trial.date;

        System.out.println(trial.date);

        Date date = Date.from(date1.atStartOfDay(ZoneId.systemDefault()).toInstant());

        Date date3 = new Date();

        System.out.println("date3: " + date3);

        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy-MM-dd");

        String strDate= formatter1.format(date3);

        System.out.println("str date: " + strDate);

        System.out.println("static date: " + lateFeeDate1);

        Debt debt1 = new Debt();

        EnumDebtType enumDebtType1 = debt1.getDebtType();

        System.out.println("enum debt type: " + enumDebtType1);

    }

}
