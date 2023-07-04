package ua.lviv.iot.spring.rest.model;

import jakarta.servlet.ServletOutputStream;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SnackMachine{
    private Integer id;
    private String address;
    private Menu menu;
    private Map<Integer, Integer> existingSnacks;
    private Map<Integer, Integer> soldedSnacks;

    public String toCsv(){
        return id +"," + address;
    }

    public void setMenu(Menu menu){
        this.menu = menu;
    }
}
