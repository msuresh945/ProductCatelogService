package com.example.productcatelogservice.controllers;

import com.example.productcatelogservice.services.IProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = ProductController.class)
public class ProductControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private IProductService productService;



    @Test
    public void TestGetProducts_RunSuccessfully() throws Exception {
        mockMvc.perform(get("/products"))
                .andExpect(status().isOk());
    }
}
