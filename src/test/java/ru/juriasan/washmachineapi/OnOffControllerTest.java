package ru.juriasan.washmachineapi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import ru.juriasan.washmachineapi.controllers.OnOffController;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;

@WebMvcTest(value = OnOffController.class, secure = false)
public class OnOffControllerTest extends BaseControllerTest {

  private static final String TURN_ON_QUERY_FORMAT = "/turn/%s/on";
  private static final String TURN_OFF_QUERY_FORMAT = "/turn/%s/off";

  private void turnOnOffTest(boolean turnOff, String format) throws Exception {
    String modelName = "model";

    WashMachine machine = new WashMachine();
    machine.setModelName(modelName);
    if ( turnOff ) {
      machine.turnOn();
    } else {
      machine.turnOff();
    }
    machine.setState(WashState.NONE);
    machine.setCurrentWashMode(WashMode.NONE);

    Mockito.doReturn(machine).when(washMachineServiceMock).findByModelName(modelName);
    Mockito.doAnswer(i -> i.getArguments()[0]).when(washMachineServiceMock).save(Mockito.anyObject());

    String result = performControllerQuery(modelName, turnOff ? TURN_OFF_QUERY_FORMAT : TURN_ON_QUERY_FORMAT);

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
