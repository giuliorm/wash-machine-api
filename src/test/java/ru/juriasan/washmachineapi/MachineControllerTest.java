package ru.juriasan.washmachineapi;

import java.util.List;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import ru.juriasan.washmachineapi.controllers.MachineController;
import ru.juriasan.washmachineapi.domain.WashMachine;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;

@WebMvcTest(value = MachineController.class, secure = false)
public class MachineControllerTest  extends  BaseTest {

  private static final String HELLO = "/";
  private static final String ALL = "/machine/all";
  private static final String CREATE_FORMAT = "/machine/%s/create";
  private static final String GET_FORMAT = "/machine/%s/get";
  private static final String REMOVE_FORMAT = "/machine/%s/remove";

  @Test
  public void helloTest_ShouldReturnHelloString() throws  Exception {
    String result = performControllerQuery(HELLO);
    String expected = MachineController.HELLO;
    assertEquals(expected, result);
  }

  @Test
  public void findAll_ShouldReturnAllMachines() throws Exception {
    List<WashMachine> machines =  initMany(2);
    Mockito.doReturn(machines).when(washMachineServiceMock).findAll();
    String result = performControllerQuery(ALL);
    String expected = objectMapper.writeValueAsString(machines);
    Mockito.verify(washMachineServiceMock, times(1)).findAll();
    assertEquals(expected, result);
  }

  @Test
  public void create_ShouldCreateNewMachine() throws Exception {
    String modelName = "model";
    WashMachine machine = initOne(modelName);
    Mockito.doAnswer(i -> i.getArguments()[0]).when(washMachineServiceMock).save(Mockito.anyObject());
    String result = performControllerQuery(String.format(CREATE_FORMAT, modelName));
    String expected = objectMapper.writeValueAsString(machine);
    Mockito.verify(washMachineServiceMock, times(1)).save(Mockito.anyObject());
    assertEquals(expected, result);
  }

  @Test
  public void get_ShouldThrowExceptionIfModelNotFound() throws Exception {
    String modelName = "model3";
    Mockito.doReturn(null).when(washMachineServiceMock).findByModelName(modelName);
    Exception e = null;
    try {
      String result = performControllerQuery(String.format(GET_FORMAT, modelName));
    } catch (Exception ex) {
     e = ex;
    }
    if ( e == null ) {
      Assert.fail();
    }
  }

  @Test
  public void get_ShouldFindMachineByModelName() throws Exception {
    List<WashMachine> machines = initMany(2);
    WashMachine first = machines.get(0);
    Mockito.doReturn(first).when(washMachineServiceMock).findByModelName(first.getModelName());
    String result = performControllerQuery(String.format(GET_FORMAT, first.getModelName()));
    String expected = objectMapper.writeValueAsString(first);
    Mockito.verify(washMachineServiceMock, times(1)).findByModelName(first.getModelName());
    assertEquals(expected, result);
  }

  @Test
  public void remove_ShouldRemoveMachine() throws Exception {
    List<WashMachine> machines = initMany(2);
    WashMachine first = machines.get(0);

    Mockito.doReturn(first).when(washMachineServiceMock).findByModelName(first.getModelName());
    Mockito.doNothing().when(washMachineServiceMock).remove(first);
    String result = performControllerQuery(String.format(REMOVE_FORMAT, first.getModelName()));
    String expected = MachineController.MACHINE_REMOVED_SUCCESSFULLY;
    Mockito.verify(washMachineServiceMock, times(1)).remove(first);
    assertEquals(expected, result);
  }
}
