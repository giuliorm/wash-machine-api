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

  private void turnOnOffTest(boolean turnOff, String format) throws Exception {
    String modelName = "model";
    WashMachine machine = initOne(modelName);

    if ( turnOff ) {
      machine.turnOn();
    }

    Mockito.doReturn(machine).when(washMachineServiceMock).findByModelName(modelName);
    Mockito.doAnswer(i -> i.getArguments()[0]).when(washMachineServiceMock).save(Mockito.anyObject());

    String result = performControllerQuery(String.format(format, modelName));

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
