package ua.lviv.iot.spring.rest.writer;

import com.opencsv.CSVWriter;
import lombok.Getter;
import lombok.Setter;
import ua.lviv.iot.spring.rest.model.Menu;
import ua.lviv.iot.spring.rest.model.SnackMachine;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class SnackMachineWriter extends SnackMachine {



    public void writeAllObjectsToCSV(Map<Integer, SnackMachine> SnackMachines, String folderpath) throws IOException {
        DateTimeFormatter formatterForFileNaming = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate currentDate = LocalDate.now();

        String headers = "id, address, menu, exsistingMarks, exsistingSnacks, soldedSnacks";

        for (SnackMachine snackMachine : SnackMachines.values()) {
            String filename = snackMachine.getId() + "-" + currentDate.format(formatterForFileNaming) + ".csv";
            String filepath = folderpath + "/" + filename;
            BufferedWriter writer = new BufferedWriter(new FileWriter(filepath));

            int id = snackMachine.getId();
            String address = snackMachine.getAddress();
            Menu menu = snackMachine.getMenu();
            Map<Integer, Integer> exsistingSnacks = snackMachine.getExistingSnacks();
            Map<Integer, Integer> soldedSnacks = snackMachine.getSoldedSnacks();

            String line = id + "," + address+ "," + menu + "," + exsistingSnacks + "," + soldedSnacks + ",";

            writer.write("Snack Machine");
            writer.newLine();
            writer.write(headers);
            writer.newLine();
            writer.write(line);
            writer.newLine();

            writer.close();
        }
    }
}
