package com.example.testexercices.numbers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class NumberControllerTest {
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private NumberController numberController;

    @BeforeEach
    void setUp() {
        numberController.getNumberObjects().clear();
        numberController.getNumberObjects().add(new NumberObject(11, 22));
        numberController.getNumberObjects().add(new NumberObject(6, 84));
        numberController.getNumberObjects().add(new NumberObject(55, 12));
        numberController.getNumberObjects().add(new NumberObject(48, 13));
        numberController.getNumberObjects().add(new NumberObject(45, 98));
        numberController.getNumberObjects().add(new NumberObject(8, 99));
        numberController.getNumberObjects().add(new NumberObject(48, 34));
        numberController.getNumberObjects().add(new NumberObject(87, 3));
        numberController.getNumberObjects().add(new NumberObject(48, 98));
        numberController.getNumberObjects().add(new NumberObject(87, 12));

    }

    @Test
    void numberAdditionMissingData() throws Exception {
        mockMvc.perform(get("/add"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void numberAdditionNormal() throws Exception {
        mockMvc.perform(get("/add")
                        .param("number1", "1")
                        .param("number2", "2"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number1").value(1))
                .andExpect(jsonPath("$.number2").value(2))
                .andExpect(jsonPath("$.sum").value(3));
    }

    @Test
    void numberAdditionNum1Lower0() throws Exception {
        mockMvc.perform(get("/add")
                        .param("number1", "-1")
                        .param("number2", "5"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void numberAdditionNum1Higher100() throws Exception {
        mockMvc.perform(get("/add")
                        .param("number1", "110")
                        .param("number2", "5"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void numberAdditionNum2Lower0() throws Exception {
        mockMvc.perform(get("/add")
                        .param("number1", "15")
                        .param("number2", "-3"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void numberAdditionNum2Higher100() throws Exception {
        mockMvc.perform(get("/add")
                        .param("number1", "75")
                        .param("number2", "148"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void numberAdditionNumberAre0() throws Exception {
        mockMvc.perform(get("/add")
                        .param("number1", "0")
                        .param("number2", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number1").value(0))
                .andExpect(jsonPath("$.number2").value(0))
                .andExpect(jsonPath("$.sum").value(0));
    }

    @Test
    void numberAdditionNumberAre100() throws Exception {
        mockMvc.perform(get("/add")
                        .param("number1", "100")
                        .param("number2", "100"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.number1").value(100))
                .andExpect(jsonPath("$.number2").value(100))
                .andExpect(jsonPath("$.sum").value(200));
    }

    @Test
    void numberSearchAllDesc() throws Exception {
        mockMvc.perform(get("/find")
                        .param("sortDesc", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number1").value(48))
                .andExpect(jsonPath("$[0].number2").value(98))
                .andExpect(jsonPath("$[0].sum").value(146))
                .andExpect(jsonPath("$[1].number1").value(45))
                .andExpect(jsonPath("$[1].number2").value(98))
                .andExpect(jsonPath("$[1].sum").value(143))
                .andExpect(jsonPath("$[2].number1").value(8))
                .andExpect(jsonPath("$[2].number2").value(99))
                .andExpect(jsonPath("$[2].sum").value(107))
                .andExpect(jsonPath("$[3].number1").value(87))
                .andExpect(jsonPath("$[3].number2").value(12))
                .andExpect(jsonPath("$[3].sum").value(99))
                .andExpect(jsonPath("$[4].number1").value(6))
                .andExpect(jsonPath("$[4].number2").value(84))
                .andExpect(jsonPath("$[4].sum").value(90))
                .andExpect(jsonPath("$[5].number1").value(87))
                .andExpect(jsonPath("$[5].number2").value(3))
                .andExpect(jsonPath("$[5].sum").value(90))
                .andExpect(jsonPath("$[6].number1").value(48))
                .andExpect(jsonPath("$[6].number2").value(34))
                .andExpect(jsonPath("$[6].sum").value(82))
                .andExpect(jsonPath("$[7].number1").value(55))
                .andExpect(jsonPath("$[7].number2").value(12))
                .andExpect(jsonPath("$[7].sum").value(67))
                .andExpect(jsonPath("$[8].number1").value(48))
                .andExpect(jsonPath("$[8].number2").value(13))
                .andExpect(jsonPath("$[8].sum").value(61))
                .andExpect(jsonPath("$[9].number1").value(11))
                .andExpect(jsonPath("$[9].number2").value(22))
                .andExpect(jsonPath("$[9].sum").value(33));
    }

    @Test
    void numberSearchAllAsc() throws Exception {
        mockMvc.perform(get("/find")
                        .param("sortDesc", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number1").value(11))
                .andExpect(jsonPath("$[0].number2").value(22))
                .andExpect(jsonPath("$[0].sum").value(33))
                .andExpect(jsonPath("$[1].number1").value(48))
                .andExpect(jsonPath("$[1].number2").value(13))
                .andExpect(jsonPath("$[1].sum").value(61))
                .andExpect(jsonPath("$[2].number1").value(55))
                .andExpect(jsonPath("$[2].number2").value(12))
                .andExpect(jsonPath("$[2].sum").value(67))
                .andExpect(jsonPath("$[3].number1").value(48))
                .andExpect(jsonPath("$[3].number2").value(34))
                .andExpect(jsonPath("$[3].sum").value(82))
                .andExpect(jsonPath("$[4].number1").value(6))
                .andExpect(jsonPath("$[4].number2").value(84))
                .andExpect(jsonPath("$[4].sum").value(90))
                .andExpect(jsonPath("$[5].number1").value(87))
                .andExpect(jsonPath("$[5].number2").value(3))
                .andExpect(jsonPath("$[5].sum").value(90))
                .andExpect(jsonPath("$[6].number1").value(87))
                .andExpect(jsonPath("$[6].number2").value(12))
                .andExpect(jsonPath("$[6].sum").value(99))
                .andExpect(jsonPath("$[7].number1").value(8))
                .andExpect(jsonPath("$[7].number2").value(99))
                .andExpect(jsonPath("$[7].sum").value(107))
                .andExpect(jsonPath("$[8].number1").value(45))
                .andExpect(jsonPath("$[8].number2").value(98))
                .andExpect(jsonPath("$[8].sum").value(143))
                .andExpect(jsonPath("$[9].number1").value(48))
                .andExpect(jsonPath("$[9].number2").value(98))
                .andExpect(jsonPath("$[9].sum").value(146));
    }

    @Test
    void numberSearchNum1Desc() throws Exception {
        mockMvc.perform(get("/find")
                        .param("number", "48")
                        .param("sortDesc", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number1").value(48))
                .andExpect(jsonPath("$[0].number2").value(98))
                .andExpect(jsonPath("$[0].sum").value(146))
                .andExpect(jsonPath("$[1].number1").value(48))
                .andExpect(jsonPath("$[1].number2").value(34))
                .andExpect(jsonPath("$[1].sum").value(82))
                .andExpect(jsonPath("$[2].number1").value(48))
                .andExpect(jsonPath("$[2].number2").value(13))
                .andExpect(jsonPath("$[2].sum").value(61));
    }

    @Test
    void numberSearchNum1Asc() throws Exception {
        mockMvc.perform(get("/find")
                        .param("number", "48")
                        .param("sortDesc", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number1").value(48))
                .andExpect(jsonPath("$[0].number2").value(13))
                .andExpect(jsonPath("$[0].sum").value(61))
                .andExpect(jsonPath("$[1].number1").value(48))
                .andExpect(jsonPath("$[1].number2").value(34))
                .andExpect(jsonPath("$[1].sum").value(82))
                .andExpect(jsonPath("$[2].number1").value(48))
                .andExpect(jsonPath("$[2].number2").value(98))
                .andExpect(jsonPath("$[2].sum").value(146));
    }

    @Test
    void numberSearchNum2Desc() throws Exception {
        mockMvc.perform(get("/find")
                        .param("number", "12")
                        .param("sortDesc", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number1").value(87))
                .andExpect(jsonPath("$[0].number2").value(12))
                .andExpect(jsonPath("$[0].sum").value(99))
                .andExpect(jsonPath("$[1].number1").value(55))
                .andExpect(jsonPath("$[1].number2").value(12))
                .andExpect(jsonPath("$[1].sum").value(67));
    }

    @Test
    void numberSearchNum2Asc() throws Exception {
        mockMvc.perform(get("/find")
                        .param("number", "12")
                        .param("sortDesc", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number1").value(55))
                .andExpect(jsonPath("$[0].number2").value(12))
                .andExpect(jsonPath("$[0].sum").value(67))
                .andExpect(jsonPath("$[1].number1").value(87))
                .andExpect(jsonPath("$[1].number2").value(12))
                .andExpect(jsonPath("$[1].sum").value(99));
    }

    @Test
    void numberSearchSumDesc() throws Exception {
        mockMvc.perform(get("/find")
                        .param("number", "90")
                        .param("sortDesc", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number1").value(6))
                .andExpect(jsonPath("$[0].number2").value(84))
                .andExpect(jsonPath("$[0].sum").value(90))
                .andExpect(jsonPath("$[1].number1").value(87))
                .andExpect(jsonPath("$[1].number2").value(3))
                .andExpect(jsonPath("$[1].sum").value(90));
    }

    @Test
    void numberSearchSumAsc() throws Exception {
        mockMvc.perform(get("/find")
                        .param("number", "90")
                        .param("sortDesc", "0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].number1").value(6))
                .andExpect(jsonPath("$[0].number2").value(84))
                .andExpect(jsonPath("$[0].sum").value(90))
                .andExpect(jsonPath("$[1].number1").value(87))
                .andExpect(jsonPath("$[1].number2").value(3))
                .andExpect(jsonPath("$[1].sum").value(90));
    }

    @Test
    void numberSearchNumHigher100() throws Exception {
        mockMvc.perform(get("/find")
                        .param("number", "101")
                        .param("sortDesc", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void numberSearchNumLower0() throws Exception {
        mockMvc.perform(get("/find")
                        .param("number", "-2")
                        .param("sortDesc", "0"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void numberSearchSortMissing() throws Exception {
        mockMvc.perform(get("/find")
                        .param("number", "101"))
                .andExpect(status().isBadRequest());
    }
}