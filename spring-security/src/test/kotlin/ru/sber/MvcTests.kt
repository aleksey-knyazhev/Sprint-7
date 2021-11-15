package ru.sber

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@SpringBootTest
@AutoConfigureMockMvc
class MvcTests {
    @Autowired
    lateinit var mockMvc: MockMvc

    @WithMockUser(username = "user", password = "user", roles = ["USER"])
    @Test
    fun addNote() {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/list"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("list"))
        mockMvc.perform(
            MockMvcRequestBuilders.post("/app/add")
                .param("name", "name")
                .param("address", "address")
        )
            .andExpect(MockMvcResultMatchers.status().isFound)
    }

    @WithMockUser(username = "user", password = "user", roles = ["USER"])
    @Test
    fun deleteNoteUser() {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/list"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("list"))
        mockMvc.perform(
            MockMvcRequestBuilders.post("/app/add")
                .param("name", "name")
                .param("address", "address")
        )
            .andExpect(MockMvcResultMatchers.status().isFound)
        mockMvc.perform(MockMvcRequestBuilders.get("/app/0/delete"))
            .andExpect(MockMvcResultMatchers.status().isForbidden)
    }

    @WithMockUser(username = "admin", password = "admin", roles = ["ADMIN"])
    @Test
    fun deleteNoteAdmin() {
        mockMvc.perform(MockMvcRequestBuilders.get("/app/list"))
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andExpect(MockMvcResultMatchers.view().name("list"))
        mockMvc.perform(
            MockMvcRequestBuilders.post("/app/add")
                .param("name", "name")
                .param("address", "address")
        )
            .andExpect(MockMvcResultMatchers.status().isFound)
        mockMvc.perform(MockMvcRequestBuilders.get("/app/0/delete"))
    }

}
