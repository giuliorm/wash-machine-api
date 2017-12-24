package ru.juriasan.washmachineapi;

import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import ru.juriasan.washmachineapi.controllers.OnOffController;

@WebMvcTest(value = OnOffController.class, secure = false)
public class MachineControllerTest {
}
