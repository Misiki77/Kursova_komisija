package ua.lviv.iot.spring.rest.Manager;

import ua.lviv.iot.spring.rest.controller.SnackMachineController;
import ua.lviv.iot.spring.rest.reader.Reader;
import ua.lviv.iot.spring.rest.service.Service;
import ua.lviv.iot.spring.rest.service.SnackMachineService;

public class Manager {
    public static void main(String[] args) {
        Service service = new SnackMachineService();

        SnackMachineController controller = new SnackMachineController(service);

        controller.doControllerAction();

    }

}
