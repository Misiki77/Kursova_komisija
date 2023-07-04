package ua.lviv.iot.spring.rest.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.lviv.iot.spring.rest.model.Menu;
import ua.lviv.iot.spring.rest.model.SnackMachine;
import ua.lviv.iot.spring.rest.writer.SnackMachineWriter;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

@RequestMapping("/SnackMachine")
@Service
@Getter
public class SnackMachineService implements ua.lviv.iot.spring.rest.service.Service {
    private  Map<Integer, SnackMachine> snackMachineMap = new HashMap<>();

    private final AtomicInteger snackMachineIdCounter = new AtomicInteger();



    public SnackMachine createSnackMachine(SnackMachine snackMachine) {
        snackMachine.setId(snackMachineIdCounter.incrementAndGet());
        snackMachineMap.put(snackMachine.getId(), snackMachine);
        return snackMachine;
    }

    public SnackMachine getSnackMachineById(Integer id) {
        return snackMachineMap.getOrDefault(id, null);
    }

    public void deleteSnackMachineById(Integer id) {
        SnackMachine snackMachine = snackMachineMap.get(id);
        if (snackMachine != null) {
            snackMachineMap.remove(id);
        } else {
            throw new IllegalArgumentException("Snack machine not found with ID: " + id);
        }
    }

    public List<SnackMachine> getAllSnackMachines() {
        return new LinkedList<>(snackMachineMap.values());
    }

    public void updateSnackMachine(Integer id, String address, Menu menu, Map<Integer, Integer>  existingSnacks, Map<Integer, Integer>  soldedSnacks) {
        SnackMachine snackMachine = snackMachineMap.get(id);
        if (snackMachine != null) {
            snackMachine.setAddress(address);
            snackMachine.setMenu(menu);
            snackMachine.setExistingSnacks(existingSnacks);
            snackMachine.setSoldedSnacks(soldedSnacks);
            snackMachineMap.put(id, snackMachine);
        } else {
            throw new IllegalArgumentException("Snack machine not found with ID: " + id);
        }
    }

    public void exportAllSnackMachines(Map<Integer, SnackMachine> snackMachineMap, String folderpath){
        try {
            SnackMachineWriter snackMachineWriter = new SnackMachineWriter();
            snackMachineWriter.writeAllObjectsToCSV(snackMachineMap, folderpath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void doSomething() {

    }
}
