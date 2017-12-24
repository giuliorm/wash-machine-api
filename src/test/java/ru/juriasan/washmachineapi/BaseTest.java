package ru.juriasan.washmachineapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.runner.RunWith;
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
import ru.juriasan.washmachineapi.service.WashMachineService;

@RunWith(SpringRunner.class)
@ComponentScan("ru.juriasan.washmachineapi")
public class BaseTest {

  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  protected WashMachineService washMachineServiceMock;

  @Autowired
  protected ObjectMapper objectMapper;

  protected WashMachine init(String modelName) {
    WashMachine machine = new WashMachine();
    machine.setModelName(modelName);
    machine.setState(WashState.NONE);
    machine.setCurrentWashMode(WashMode.NONE);
    return machine;
  }

  protected String performControllerQuery(String modelName, String apiQueryFormat) throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
        String.format(apiQueryFormat, modelName)).accept(
        MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    return result.getResponse().getContentAsString();
  }
}
