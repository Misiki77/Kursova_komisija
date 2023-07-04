package ua.lviv.iot.spring.rest.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Menu {

    private List<Snack> snacks;

    public String toCsv() {
        StringBuilder stringBuilder = new StringBuilder();

        for (Snack snack : snacks) {
            stringBuilder.append(snack);
        }

        return stringBuilder.toString();
    }

    public String getHeaders() {
        return "snacks";
    }

    public void setSnacks(List<Snack> snacks){
        this.snacks = snacks;
    }

    public void fromCsv(String filepath){
        Snack snack = new Snack();
        snack.fromCsv(filepath);
        this.snacks.add(snack);
    }

}
