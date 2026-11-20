package com.ivandyshliuk.depotflow;

import com.ivandyshliuk.depotflow.inventory.ApiExeptionHandler;
import com.ivandyshliuk.depotflow.inventory.Product;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;

public class ApiExeptionHandlerTest {
    @Test
    void duplicateSkuReturnsConflict() {
        var violation = new org.hibernate.exception.ConstraintViolationException("Duplicate SKU", null, "products_sku_key");
        var exeption = new DataIntegrityViolationException("Database error", violation);
        var apiexeption = new ApiExeptionHandler();
        var response = apiexeption.handleDataIntegrity(exeption);
        assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }


    @Test
    void otherConstraintReturnsInternalServerError() {
        var violation = new org.hibernate.exception.ConstraintViolationException("Internal server error", null, "some_other_constraint");
        var exeption = new DataIntegrityViolationException("Database error", violation);
        var apiexeption = new ApiExeptionHandler();
        var response = apiexeption.handleDataIntegrity(exeption);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
