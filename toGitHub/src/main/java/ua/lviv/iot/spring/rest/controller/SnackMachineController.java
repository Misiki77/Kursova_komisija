package ua.lviv.iot.spring.rest.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ua.lviv.iot.spring.rest.model.Menu;
import ua.lviv.iot.spring.rest.model.SnackMachine;
import ua.lviv.iot.spring.rest.service.Service;
import ua.lviv.iot.spring.rest.service.SnackMachineService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/SnackMachineController")
@AllArgsConstructor
@NoArgsConstructor
public class SnackMachineController {
    @Autowired
    private SnackMachineService snackMachineService;

    @PostMapping("/create")
    public ResponseEntity<SnackMachine> CreateNewSnackMachine(@RequestBody SnackMachine snackMachine) {
        SnackMachine addedPanel = snackMachineService.createSnackMachine(snackMachine);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedPanel);
    }

    @GetMapping("/get/{id}")
    public SnackMachine getSnackMachineById(@PathVariable Integer id) {
        return snackMachineService.getSnackMachineById(id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteSnackMachineById(@PathVariable Integer id) {
        snackMachineService.deleteSnackMachineById(id);
        return ResponseEntity.ok("Snack Machine deleted successfully");
    }

    @GetMapping("/snackMachines")
    public List<SnackMachine> getAllSolarPanels() {
        return snackMachineService.getAllSnackMachines();
    }

    @Getter
    @Setter
    private static class SnackMachineUpdateRequest {
        private String address;
        private Menu menu;
        private Map<Integer, Integer>  existingSnacks;
        private Map<Integer, Integer>  soldedSnacks;

    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateSnackMachine(@PathVariable("id") Integer id, @RequestBody SnackMachineUpdateRequest request) {
        snackMachineService.updateSnackMachine(id, request.getAddress(), request.getMenu(), request.getExistingSnacks(), request.getSoldedSnacks());
        return ResponseEntity.ok("Snack machine updated successfully");
    }

    @PostMapping(path = "/export")
    public ResponseEntity<String> exportAllSnackMachines(@RequestParam("folderpath") String folderpath){
        Map<Integer, SnackMachine> snackMachineMap = snackMachineService.getSnackMachineMap();
        try {
            snackMachineService.exportAllSnackMachines(snackMachineMap, folderpath);
            return ResponseEntity.ok("All snack machines files were created successfully");
        } catch (Exception e){
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error encoding filepath");
        }
    }


    private Service service;
    public SnackMachineController(Service service) {
        this.service = service;
    }

    public void doControllerAction() {
        service.doSomething();
    }

}
