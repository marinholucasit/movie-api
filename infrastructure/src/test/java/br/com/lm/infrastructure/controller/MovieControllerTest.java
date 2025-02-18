package br.com.lm.infrastructure.controller;

import br.com.lm.usecase.ProducersIntervalUseCase;
import br.com.lm.usecase.model.AwardIntervalModel;
import br.com.lm.usecase.model.ProducerAwardIntervalsModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProducersIntervalUseCase producersIntervalUseCase;

    @Test
    public void getProducersIntervalsTest() throws Exception {

        AwardIntervalModel minInterval = new AwardIntervalModel("Joel Silver", 1, 1990, 1991);
        AwardIntervalModel maxInterval = new AwardIntervalModel("Matthew Vaughn", 13, 2002, 2015);

        List<AwardIntervalModel> minIntervals = List.of(minInterval);
        List<AwardIntervalModel> maxIntervals = List.of(maxInterval);

        ProducerAwardIntervalsModel intervalsModel = new ProducerAwardIntervalsModel(minIntervals, maxIntervals);

        when(producersIntervalUseCase.execute()).thenReturn(intervalsModel);

        mockMvc.perform(get("/api/v1/movies/awards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min[0].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.min[0].interval").value(1))
                .andExpect(jsonPath("$.max[0].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[0].interval").value(13));
    }

    @Test
    public void getProducersIntervalsWhenEmptyListsTest() throws Exception {
        ProducerAwardIntervalsModel emptyIntervals = new ProducerAwardIntervalsModel(Collections.emptyList(), Collections.emptyList());

        when(producersIntervalUseCase.execute()).thenReturn(emptyIntervals);

        mockMvc.perform(get("/api/v1/movies/awards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min").isEmpty())
                .andExpect(jsonPath("$.max").isEmpty());
    }

    @Test
    public void getProducersIntervalsWhenHasMultipleProducersTest() throws Exception {
        ProducerAwardIntervalsModel intervalsModel = mockMultipleProducerAwardIntervalsModel();

        when(producersIntervalUseCase.execute()).thenReturn(intervalsModel);

        mockMvc.perform(get("/api/v1/movies/awards"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.min", hasSize(2)))
                .andExpect(jsonPath("$.max", hasSize(2)))
                .andExpect(jsonPath("$.min[0].producer").value("Joel Silver"))
                .andExpect(jsonPath("$.min[1].producer").value("Jerry Bruckheimer"))
                .andExpect(jsonPath("$.max[0].producer").value("Matthew Vaughn"))
                .andExpect(jsonPath("$.max[1].producer").value("George Lucas"));
    }

    private static ProducerAwardIntervalsModel mockMultipleProducerAwardIntervalsModel() {
        AwardIntervalModel minInterval1 = new AwardIntervalModel("Joel Silver", 1, 1990, 1991);
        AwardIntervalModel minInterval2 = new AwardIntervalModel("Jerry Bruckheimer", 1, 2005, 2006);

        AwardIntervalModel maxInterval1 = new AwardIntervalModel("Matthew Vaughn", 13, 2002, 2015);
        AwardIntervalModel maxInterval2 = new AwardIntervalModel("George Lucas", 13, 1980, 1993);

        List<AwardIntervalModel> minIntervals = Arrays.asList(minInterval1, minInterval2);
        List<AwardIntervalModel> maxIntervals = Arrays.asList(maxInterval1, maxInterval2);

        return new ProducerAwardIntervalsModel(minIntervals, maxIntervals);
    }

}