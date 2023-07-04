package ua.lviv.iot.spring.rest.model;

import com.sun.jdi.IntegerValue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Snack extends Menu {
    private int id;
    private String name;

    public String toCsv(){
        return id+ "," + name;
    }
    public String getHeaders(){
        return "id" + "," + "name";
    }
    public void fromCsv(String line){
        String[] fields = line.split(",");
        this.id = Integer.valueOf(fields[0].trim());
        this.name = fields[1].trim();
    }

}
