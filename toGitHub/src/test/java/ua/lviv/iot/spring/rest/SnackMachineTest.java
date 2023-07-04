package ua.lviv.iot.spring.rest;

import org.junit.jupiter.api.Test;
import ua.lviv.iot.spring.rest.model.Menu;
import ua.lviv.iot.spring.rest.model.Snack;
import ua.lviv.iot.spring.rest.model.SnackMachine;
import ua.lviv.iot.spring.rest.reader.Reader;
import ua.lviv.iot.spring.rest.service.SnackMachineService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SnackMachineTest {
    private final SnackMachineService snackMachineService = new SnackMachineService();

    @Test
    void testCreateSnackMachine(){
        SnackMachine snackMachine = new SnackMachine();
        List<Snack> snacks = new LinkedList<>();
        Snack snack1 = new Snack(1, "Twix");
        Snack snack2 = new Snack(2, "Bounty");
        Snack snack3 = new Snack(3, "Mars");
        Snack snack4 = new Snack(4, "Orbit");
        snacks.add(snack1);snacks.add(snack2);snacks.add(snack3);snacks.add(snack4);

        snackMachine.setAddress("V. Velykogo 28");
        snackMachine.setMenu(snack1);
        Map<Integer, Integer> paramsMap = new HashMap<>();
        paramsMap.put(1, 5);
        paramsMap.put(2, 5);
        paramsMap.put(3, 4);
        snackMachine.setExistingSnacks(paramsMap);

        Map<Integer, Integer> paramsMap2 = new HashMap<>();
        paramsMap.put(1, 2);
        paramsMap.put(2, 3);
        paramsMap.put(3, 1);
        snackMachine.setSoldedSnacks(paramsMap2);
        SnackMachine createdMachine = snackMachineService.createSnackMachine(snackMachine);

        assertNotNull(createdMachine.getId());
        assertEquals(1, snackMachineService.getSnackMachineMap().size());
        assertEquals(createdMachine, snackMachineService.getSnackMachineMap().get(createdMachine.getId()));
    }

    @Test
    void testGetAllMachines() {
        SnackMachine snackMachine = new SnackMachine();
        List<Snack> snacks = new LinkedList<>();
        Snack snack1 = new Snack(1, "Twix");
        Snack snack2 = new Snack(2, "Bounty");
        Snack snack3 = new Snack(3, "Mars");
        Snack snack4 = new Snack(4, "Orbit");
        snacks.add(snack1);snacks.add(snack2);snacks.add(snack3);snacks.add(snack4);

        snackMachine.setAddress("V. Velykogo 28");
        snackMachine.setMenu(snack1);
        Map<Integer, Integer> paramsMap = new HashMap<>();
        paramsMap.put(1, 5);
        paramsMap.put(2, 5);
        paramsMap.put(3, 4);
        snackMachine.setExistingSnacks(paramsMap);

        Map<Integer, Integer> paramsMap2 = new HashMap<>();
        paramsMap.put(1, 2);
        paramsMap.put(2, 3);
        paramsMap.put(3, 1);
        snackMachine.setSoldedSnacks(paramsMap2);
        snackMachineService.createSnackMachine(snackMachine);
        List<SnackMachine> listWithMachines = snackMachineService.getAllSnackMachines();

        assertNotNull(listWithMachines);
        assertEquals(1, listWithMachines.size());
    }

    @Test
    void testGetSnackMachineById() {
        SnackMachine snackMachine = new SnackMachine();
        List<Snack> snacks = new LinkedList<>();
        Snack snack1 = new Snack(1, "Twix");
        Snack snack2 = new Snack(2, "Bounty");
        Snack snack3 = new Snack(3, "Mars");
        Snack snack4 = new Snack(4, "Orbit");
        snacks.add(snack1);snacks.add(snack2);snacks.add(snack3);snacks.add(snack4);

        snackMachine.setAddress("V. Velykogo 28");
        snackMachine.setMenu(snack1);
        Map<Integer, Integer> paramsMap = new HashMap<>();
        paramsMap.put(1, 5);
        paramsMap.put(2, 5);
        paramsMap.put(3, 4);
        snackMachine.setExistingSnacks(paramsMap);

        Map<Integer, Integer> paramsMap2 = new HashMap<>();
        paramsMap.put(1, 2);
        paramsMap.put(2, 3);
        paramsMap.put(3, 1);
        snackMachine.setSoldedSnacks(paramsMap2);
        SnackMachine createdMachine = snackMachineService.createSnackMachine(snackMachine);

        SnackMachine anotherMachine = snackMachineService.getSnackMachineById(createdMachine.getId());

        assertNotNull(anotherMachine);
        assertEquals(createdMachine, anotherMachine);
    }

    @Test
    void testDeleteMachineById() {
        SnackMachine snackMachine = new SnackMachine();
        List<Snack> snacks = new LinkedList<>();
        Snack snack1 = new Snack(1, "Twix");
        Snack snack2 = new Snack(2, "Bounty");
        Snack snack3 = new Snack(3, "Mars");
        Snack snack4 = new Snack(4, "Orbit");
        snacks.add(snack1);snacks.add(snack2);snacks.add(snack3);snacks.add(snack4);

        snackMachine.setAddress("V. Velykogo 28");
        snackMachine.setMenu(snack1);
        Map<Integer, Integer> paramsMap = new HashMap<>();
        paramsMap.put(1, 5);
        paramsMap.put(2, 5);
        paramsMap.put(3, 4);
        snackMachine.setExistingSnacks(paramsMap);

        Map<Integer, Integer> paramsMap2 = new HashMap<>();
        paramsMap.put(1, 2);
        paramsMap.put(2, 3);
        paramsMap.put(3, 1);
        snackMachine.setSoldedSnacks(paramsMap2);
        SnackMachine createdMachine = snackMachineService.createSnackMachine(snackMachine);

        snackMachineService.deleteSnackMachineById(createdMachine.getId());

        SnackMachine anotherMachine = snackMachineService.getSnackMachineById(createdMachine.getId());

        assertNull(anotherMachine);
        assertEquals(0, snackMachineService.getSnackMachineMap().size());
    }

    @Test
    void testUpdateMachine() {
        SnackMachine snackMachine = new SnackMachine();
        List<Snack> snacks = new LinkedList<>();
        Snack snack1 = new Snack(1, "Twix");
        Snack snack2 = new Snack(2, "Bounty");
        Snack snack3 = new Snack(3, "Mars");
        Snack snack4 = new Snack(4, "Orbit");
        snacks.add(snack1);snacks.add(snack2);snacks.add(snack3);snacks.add(snack4);

        snackMachine.setAddress("V. Velykogo 28");
        snackMachine.setMenu(snack1);
        Map<Integer, Integer> paramsMap = new HashMap<>();
        paramsMap.put(1, 5);
        paramsMap.put(2, 5);
        paramsMap.put(3, 4);
        snackMachine.setExistingSnacks(paramsMap);

        Map<Integer, Integer> paramsMap2 = new HashMap<>();
        paramsMap.put(1, 2);
        paramsMap.put(2, 3);
        paramsMap.put(3, 1);
        snackMachine.setSoldedSnacks(paramsMap2);
        SnackMachine createdMachine = snackMachineService.createSnackMachine(snackMachine);

        String newAddress = "S. Bandery 4";

        List<Snack> newSnacks = new LinkedList<>();
        Snack newSnack1 = new Snack(1, "Twix");
        Snack newSnack2 = new Snack(2, "Bounty");
        Menu newMenu = new Menu(snacks);

        Map<Integer, Integer> newExistingSnacks = new HashMap<Integer, Integer>() {{
            put(1, 3); put(2, 7);
        }};
        Map<Integer, Integer> newSoldedSnacks = new HashMap<Integer, Integer>() {{
            put(1, 6); put(2, 5);
        }};

        snackMachineService.updateSnackMachine(createdMachine.getId(),newAddress, newMenu, newExistingSnacks, newSoldedSnacks);

        SnackMachine updatedMachine = snackMachineService.getSnackMachineById(createdMachine.getId());

        assertNotNull(updatedMachine);
        assertEquals(newAddress, updatedMachine.getAddress());
        assertEquals(newMenu, updatedMachine.getMenu());
        assertEquals(newExistingSnacks, updatedMachine.getExistingSnacks());
        assertEquals(newSoldedSnacks, updatedMachine.getSoldedSnacks());
    }

    @Test
    public void testReadFromCsv() {
        String testFilePath = "toRead";

        List<Snack> snacks = new LinkedList<>();
        Menu menu = new Menu();
        Reader reader = new Reader();
        reader.readFromCsv(testFilePath, snacks);

        // Отримуємо список снеків і перевіряємо, чи відповідають вони очікуваним значенням

        assertEquals(3, snacks.size());

        Snack snack1 = snacks.get(0);
        assertEquals("mars", snack1.getName());

        Snack snack2 = snacks.get(1);
        assertEquals("snickers", snack2.getName());

        Snack snack3 = snacks.get(2);
        assertEquals("bounty", snack3.getName());

    }
}
