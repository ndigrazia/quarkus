package org.acme.data;

import io.vertx.mutiny.sqlclient.Row;

public class Fruit {
    
    public String name;
    public int calories;

    public Fruit() {}

    public static Fruit from(Row r) {
        Fruit f= new Fruit();

        String n = r.getString("name");
        System.out.println("------------------------->" + n);
        f.name = n;

        Integer c =  r.getInteger("calories");
        System.out.println("------------------------->" + c);
        f.calories = c;

        return f;
    }
    
}
