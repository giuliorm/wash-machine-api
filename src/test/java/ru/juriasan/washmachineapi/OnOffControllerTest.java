package ru.juriasan.washmachineapi;

import java.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.juriasan.washmachineapi.controllers.OnOffController;
import ru.juriasan.washmachineapi.domain.WashMachine;
import ru.juriasan.washmachineapi.repository.WashMachineRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(value = OnOffController.class, secure = false)
public class OnOffControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WashMachineRepository repository;

  @Autowired
  private OnOffController onOffControllerMock;
//  String exampleCourseJson = "{\"name\":\"Spring\",\"description\":\"10 Steps\",\"steps\":[\"Learn Maven\",\"Import Project\",\"First Example\",\"Second Example\"]}";

  @Test
  public void turnOffTest_ShouldSetIsTurnedOnFalse() throws Exception {

    WashMachine machine = new TodoBuilder()
        .id(1L)
        .description("Lorem ipsum")
        .title("Foo")
        .build();

    Mockito.when(
        studentService.retrieveCourse(Mockito.anyString(),
            Mockito.anyString())).thenReturn(mockCourse);

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
        "/students/Student1/courses/Course1").accept(
        MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andReturn();

    System.out.println(result.getResponse());
    String expected = "{id:Course1,name:Spring,description:10 Steps}";

    // {"id":"Course1","name":"Spring","description":"10 Steps, 25 Examples and 10K Students","steps":["Learn Maven","Import Project","First Example","Second Example"]}

    JSONAssert.assertEquals(expected, result.getResponse()
        .getContentAsString(), false);
  }

}
