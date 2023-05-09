package com.gestion.scolaire.controller;

import com.gestion.scolaire.TestUnitaire;
import com.gestion.scolaire.model.Etudiant;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class EtudiantControllerTest extends TestUnitaire {
    @Test
    void test_recherche_etudiant_par_classe_ou_enseignant() throws Exception {
        MvcResult result =
                mvc.perform(MockMvcRequestBuilders.get("/etudiant/rechercher")
                                        .param("classe", "CP2")
                                        .param("enseignant", "Isabelle marine")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .accept(MediaType.APPLICATION_JSON))
                        .andExpect(status().isOk())
                        .andReturn();

        List<Etudiant> content =
                JsonPath.read(result.getResponse().getContentAsString(), "$.content");
        assertTrue(content.size() > 0);
    }
}