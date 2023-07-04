package ua.lviv.iot.spring.rest.reader;

import ua.lviv.iot.spring.rest.model.Menu;
import ua.lviv.iot.spring.rest.model.Snack;
import ua.lviv.iot.spring.rest.model.SnackMachine;
import java.io.BufferedReader;
import java.io.FileReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Reader {
    public void readFromCsv(String filepath, List<Snack> snacks){

        try (BufferedReader reader = new BufferedReader(new FileReader(filepath))) {
            String line;
            Menu menu = new Menu();
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 2) {
                    int id = Integer.valueOf(data[0].trim());
                    String name = data[1].trim();
                    Snack snack = new Snack(id, name);
                    snacks.add(snack);


                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
