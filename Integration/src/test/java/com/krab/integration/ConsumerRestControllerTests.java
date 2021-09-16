package com.krab.integration;

import com.krab.integration.messaging.ConsumerRestController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultActions;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ConsumerRestController.class)
@ContextConfiguration(classes = {TestConfig.class})
public class ConsumerRestControllerTests {

    @Autowired
    MockMvc mvc;

    @Autowired
    ConsumerRestController restController;

    @Test
    void test1() throws Exception {
        RequestBuilder builder = get("/consumer/api/test1/testName");
        MvcResult mvcResult = mvc.perform(builder).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Assertions.assertNotNull(response);
    }

    @Test
    void test2() throws Exception {
        RequestBuilder builder = get("/consumer/api/test1/testName");
        mvc.perform(get("/api/test1/testName"))
                .andExpect(content().string(""));
    }

    @Test
    void test3() {
        String s = restController.testPrepareInputData3();
        Assertions.assertNotNull(s);
    }
    @Test
    void test4() {
        String s = restController.testPrepareInputData3();
        Assertions.assertNotNull(s);
    }
}
