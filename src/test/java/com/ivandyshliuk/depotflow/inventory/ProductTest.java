package com.ivandyshliuk.depotflow.inventory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    Product product;

    void setUp() {
        product = new Product("iphone-17-pro", "Iphone 17 pro");
    }

    @Test
    void createsProductWithSkuAndName(){
        product = new Product("iphone-17-pro", "Iphone 17 pro");
        assertEquals("iphone-17-pro", product.getSku());
        assertEquals("Iphone 17 pro", product.getName());
    }

    @Test
    void cannotCreateProductWithNullSku(){
        assertThrows(IllegalArgumentException.class, () -> new Product(null, "Iphone 17 pro"));
    }

    @Test
    void cannotCreateProductWithBlankSku(){
        assertThrows(IllegalArgumentException.class, () -> new Product("", "Iphone 17 pro"));
    }

    @Test
    void cannotCreateProductWithNullName(){
        assertThrows(IllegalArgumentException.class, () -> new Product("iphone-17-pro", null));
    }

    @Test
    void cannotCreateProductWithBlankName(){
        assertThrows(IllegalArgumentException.class, () -> new Product("iphone-17-pro", ""));
    }
}
