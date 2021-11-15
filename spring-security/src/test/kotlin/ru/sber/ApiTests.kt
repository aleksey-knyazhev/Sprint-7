package ru.sber

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
internal class ApiTest {
    @LocalServerPort
    private var port: Int = 0

    private fun url(s: String): String {
        return "http://localhost:${port}/${s}"
    }

    @Autowired
    lateinit var mockMvc: MockMvc

    val jsonData = """{"name" : "name", "address" : "address"}"""

    @WithMockUser(username = "api", password = "api", roles = ["API"])
    @Test
    fun getListOfRecords() {
        mockMvc.perform(
            MockMvcRequestBuilders.get(url("api/list"))
                .contentType(MediaType.APPLICATION_JSON))
            .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed)
    }

    @WithMockUser(username = "api", password = "api", roles = ["API"])
    @Test
    fun addRecord() {
        mockMvc.perform(
            MockMvcRequestBuilders.post("/api/add")
                .content(jsonData)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
    }

    @WithMockUser(username = "api", password = "api", roles = ["API"])
    @Test
    fun deleteRecord() {
        mockMvc.perform(
            MockMvcRequestBuilders.get("/api/0/delete")
        )
            .andExpect(MockMvcResultMatchers.status().isMethodNotAllowed)
    }


}