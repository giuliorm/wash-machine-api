package ru.juriasan.washmachineapi;

import static org.junit.Assert.assertEquals;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.juriasan.washmachineapi.controllers.OnOffController;
import ru.juriasan.washmachineapi.domain.WashMachine;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OnOffController.class, secure = false)
public class OnOffControllerTest extends BaseTest {

  private static final String TURN_ON_QUERY_FORMAT = "/turn/%s/on";
  private static final String TURN_OFF_QUERY_FORMAT = "/turn/%s/off";

  /**
   * Checks, if the machine turns off (or turns on) via /turn/{modelName}/off or /turn/{modelName}/on
   * query respectively.
   *
   * @param turnOff the parameter, indicating, which method - turnOff or turnOn - will be checked.
   * @param apiQueryFormat a format for an API query. Should be on of the following formats:
   *                       - /turn/%s/on
   *                       - /turn/%s/off
   * @throws Exception if the query to a controller has failed.
   */
  private void turnOnOffTest(boolean turnOff, String apiQueryFormat) throws Exception {
    String modelName = "model";
    WashMachine machine = initOne(modelName);

    if ( turnOff ) {
      machine.turnOn();
    }

    Mockito.doReturn(machine).when(washMachineServiceMock).findByModelName(modelName);
    Mockito.doAnswer(i -> i.getArguments()[0]).when(washMachineServiceMock).save(Mockito.anyObject());

    String result = performControllerQuery(String.format(apiQueryFormat, modelName));

    if ( turnOff ) {
      machine.turnOff();
    } else {
      machine.turnOn();
    }
    assertEquals(objectMapper.writeValueAsString(machine), result);
  }

  @Test
  public void turnOnTest_ShouldSetIsTurnedOnTrue() throws Exception {
    turnOnOffTest(false, TURN_ON_QUERY_FORMAT);
  }

  @Test
  public void turnOffTest_ShouldSetIsTurnedOnFalse() throws Exception {
    turnOnOffTest(true, TURN_OFF_QUERY_FORMAT);
  }
}
