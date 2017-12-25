package ru.juriasan.washmachineapi;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import ru.juriasan.washmachineapi.controllers.StateController;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.domain.WashMode;
import ru.juriasan.washmachineapi.domain.WashState;

@WebMvcTest(value = StateController.class, secure = false)
public class StateControllerTest extends BaseTest {

  private String GET_STATE_FORMAT = "/state/%s/getState";
  private String SET_STATE_FORMAT = "/state/%s/setState?state=%s";
  private String GET_MODE_FORMAT = "/state/%s/getMode";
  private String SET_MODE_FORMAT = "/state/%s/setMode?mode=%s";

  @Test
  public void getState_ShouldReturnMachineState() throws Exception {
    WashMachine machine = initOne("model");
    machine.setState(WashState.SPINNING);
    Mockito.doReturn(machine).when(washMachineServiceMock).findByModelName(machine.getModelName());
    String result = performControllerQuery(String.format(GET_STATE_FORMAT, machine.getModelName()));
    String expected = objectMapper.writeValueAsString(machine.getState());
    Assert.assertEquals(expected, result);
  }

  @Test
  public void getState_ShouldReturnNoneIfStateIsNull() throws Exception {
    WashMachine machine = initOne("model");
    machine.setState(null);
    Mockito.doReturn(machine).when(washMachineServiceMock).findByModelName(machine.getModelName());
    String result = performControllerQuery(String.format(GET_STATE_FORMAT, machine.getModelName()));
    String expected = objectMapper.writeValueAsString(WashState.NONE);
    Assert.assertEquals(expected, result);
  }

  @Test
  public void setState_ShouldSetMachineState() throws Exception {
    WashMachine machine = initOne("model");
    Mockito.doReturn(machine).when(washMachineServiceMock).findByModelName(machine.getModelName());
    Mockito.doAnswer(i -> i.getArguments()[0]).when(washMachineServiceMock).save(Mockito.anyObject());
    String result = performControllerQuery(String.format(SET_STATE_FORMAT, machine.getModelName(), WashState.READY));
    machine.setState(WashState.READY);
    String expected = objectMapper.writeValueAsString(machine);
    Assert.assertEquals(expected, result);
  }

  @Test
  public void setMode_ShouldSetMachineMode() throws Exception {
    WashMachine machine = initOne("model");
    Mockito.doReturn(machine).when(washMachineServiceMock).findByModelName(machine.getModelName());
    Mockito.doAnswer(i -> i.getArguments()[0]).when(washMachineServiceMock).save(machine);
    String result = performControllerQuery(String.format(SET_MODE_FORMAT, machine.getModelName(), WashMode.NORMAL));
    machine.setCurrentWashMode(WashMode.NORMAL);
    String expected = objectMapper.writeValueAsString(machine);
    Assert.assertEquals(expected, result);
  }

  @Test
  public void getMode_ShouldReturnMachineMode() throws Exception {
    WashMachine machine = initOne("model");
    machine.setState(WashState.SPINNING);
    Mockito.doReturn(machine).when(washMachineServiceMock).findByModelName(machine.getModelName());
    String result = performControllerQuery(String.format(GET_MODE_FORMAT, machine.getModelName()));
    String expected = objectMapper.writeValueAsString(machine.getCurrentWashMode());
    Assert.assertEquals(expected, result);
  }

  @Test
  public void getMode_ShouldReturnNoneIfModeIsNull() throws Exception {
    WashMachine machine = initOne("model");
    machine.setCurrentWashMode(null);
    Mockito.doReturn(machine).when(washMachineServiceMock).findByModelName(machine.getModelName());
    String result = performControllerQuery(String.format(GET_MODE_FORMAT, machine.getModelName()));
    String expected = objectMapper.writeValueAsString(WashMode.NONE);
    Assert.assertEquals(expected, result);
  }
}
