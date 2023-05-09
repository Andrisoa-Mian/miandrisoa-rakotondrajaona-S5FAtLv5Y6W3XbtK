package com.gestion.scolaire.controller;

import com.gestion.scolaire.TestUnitaire;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClasseControllerTest extends TestUnitaire {

    @Test
    void testConfig() {
    }
    @Test
    void test_afficher_list_classe() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/classe/list")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("[0].id").value("4"))
                .andExpect(jsonPath("[0].nom").value("CM1"))
                .andExpect(jsonPath("[0].enseignant.id").value("1"))
                .andExpect(jsonPath("[0].enseignant.nom").value("marine"))
                .andExpect(jsonPath("[0].enseignant.prenom").value("Isabelle"));
    }

    @Test
    void test_creer_classe() throws Exception {
        final String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqYWNvYiIsInJvbGVzIjpbIlJPTEVfVVNFUiJdLCJpYXQiOjE2ODM1NDY5MDUsImV4cCI6MTY4MzU1MDUwNX0.iyKb1AZa24fYh4I14DVLuU2RpsS_jIg77pGYLBq1PN0";
        final String body = "{\"nom\": \"CP1\", \"enseignant\": {\"id\": 2,\"nom\":\"Jacob\",\"prenom\":\"stewart\"}}";
        mvc.perform(MockMvcRequestBuilders.post("/classe/creer")
                        .header("Authorization", "Bearer " + token)
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("nom").value("CP1"))
                .andExpect(jsonPath("enseignant.id").value("2"))
                .andExpect(jsonPath("enseignant.nom").value("Jacob"))
                .andExpect(jsonPath("enseignant.prenom").value("stewart"));
    }
}