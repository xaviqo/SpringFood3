package tech.xavi.springfood.configuration.idgenerator;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class UUIDGenerator {

    public static String randomOrderUUID(){
        return new SimpleDateFormat("yyMMdd")
                .format(new Date())+"_"+randomUUID()
                .substring(0, 8);
    }

    public static String randomAccountUUID(){
        return randomUUID().substring(0, 14);
    }

    private static String randomUUID(){
        return UUID.randomUUID().toString().replace("-", "").toUpperCase();
    }

}
