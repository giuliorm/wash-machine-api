package ru.juriasan.washmachineapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.juriasan.washmachineapi.controllers.OnOffController;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;
import ru.juriasan.washmachineapi.repository.WashMachineRepository;
import ru.juriasan.washmachineapi.service.WashMachineService;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@ComponentScan("ru.juriasan.washmachineapi")
public abstract class BaseTest {

  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  protected WashMachineService washMachineServiceMock;

  @Autowired
  protected ObjectMapper objectMapper;

  protected List<WashMachine> initMany(int n) {
    List<WashMachine> machines = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      String name = String.format("model%d", i);
      WashMachine machine = initOne(name);
      machines.add(machine);
    }
    return machines;
  }

  protected WashMachine initOne(String modelName) {
    WashMachine machine = new WashMachine();
    machine.setModelName(modelName);
    machine.setState(WashState.NONE);
    machine.setCurrentWashMode(WashMode.NONE);
    return machine;
  }

  protected String performControllerQuery(String query) throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get(query)
        .accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    return result.getResponse().getContentAsString();
  }
}
