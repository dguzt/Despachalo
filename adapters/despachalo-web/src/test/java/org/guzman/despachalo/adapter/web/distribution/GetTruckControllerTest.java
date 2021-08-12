package org.guzman.despachalo.adapter.web.distribution;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.guzman.despachalo.core.distribution.application.TruckNotFoundException;
import org.guzman.despachalo.core.distribution.application.port.in.GetTruckUseCase;
import org.guzman.despachalo.core.distribution.domain.Truck;
import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = GetTruckController.class)
class GetTruckControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private GetTruckUseCase getTruckUseCase;

    private Truck truck() {
        return new Truck(1L, "123456");
    }

    @Test
    void whenRequestGetATruck_andExists_shouldReturnItWithOk() throws Exception {
        var truckId = 1L;
        var truckFound = truck();
        var truckExpected = truck();
        when(getTruckUseCase.execute(truckId)).thenReturn(truckFound);

        var res = mockMvc.perform(get("/trucks/{truckId}", truckId))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var expectedJson = objectMapper.writeValueAsString(truckExpected);
        assertThat(res).isEqualToIgnoringWhitespace(expectedJson);
    }

    @Test
    void whenRequestGetATruck_andNoExist_shouldReturnNotFoundException() throws Exception {
        var truckId = 1L;
        when(getTruckUseCase.execute(truckId)).thenThrow(new TruckNotFoundException(truckId));

        var res = mockMvc.perform(get("/trucks/{truckId}", truckId))
                .andExpect(status().isNotFound())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var currentTruckId = ((JSONObject) JSONParser.parseJSON(res))
                .getJSONObject("data")
                .getLong("truckId");

        assertEquals(truckId, currentTruckId);
    }
}
