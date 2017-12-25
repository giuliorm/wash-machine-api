package ru.juriasan.washmachineapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;
import ru.juriasan.washmachineapi.service.WashMachineService;

@RunWith(SpringRunner.class)
@ComponentScan("ru.juriasan.washmachineapi")
public abstract class BaseTest {

  private static final int MAX_MACHINES_NUM = 100;

  @Autowired
  protected MockMvc mockMvc;

  @MockBean
  protected WashMachineService washMachineServiceMock;

  @Autowired
  protected ObjectMapper objectMapper;

  /**
   * Returns a list of WashMachine entities with initial parameters;
   * The modelNames are formed in the following way:
   * - model1
   * - model2
   * - ...
   *
   * @param n the number of the machines, which will be created. If n is greater than
   *          MAX_MACHINES_NUM then it's set to the MAX_MACHINES_NUM value.
   * @return a list of WashMachine objects.
   */
  protected List<WashMachine> initMany(int n) {
    if ( n > MAX_MACHINES_NUM ) {
      n = MAX_MACHINES_NUM;
    }
    List<WashMachine> machines = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      String name = String.format("model%d", i);
      WashMachine machine = initOne(name);
      machines.add(machine);
    }
    return machines;
  }

  /**
   * Returns the new instance of the WashMachine with initial parameters.
   * - isTurnedOn: false
   * - washState: WashState.NONE
   * - washMode: WashMode.NONE
   * @param modelName a WashMachine model name
   * @return WashMachine entity
   */
  protected WashMachine initOne(String modelName) {
    WashMachine machine = new WashMachine();
    machine.setModelName(modelName);
    machine.setState(WashState.NONE);
    machine.setCurrentWashMode(WashMode.NONE);
    return machine;
  }

  /**
   * Performs the query to the mock controller.
   *
   * @param query a REST query. Example:
   *              /root/val?param1=value1
   * @return the result string
   * @throws Exception if one of the
   *  - mockMvc.perform(...)
   *  - result.getResponse().getContentAsString() (throws java.io.UnsupportedEncodingException)
   *
   *  are failed.
   */
  protected String performControllerQuery(String query) throws Exception {
    RequestBuilder requestBuilder = MockMvcRequestBuilders
        .get(query)
        .accept(MediaType.APPLICATION_JSON);
    MvcResult result = mockMvc.perform(requestBuilder).andReturn();
    return result.getResponse().getContentAsString();
  }
}
