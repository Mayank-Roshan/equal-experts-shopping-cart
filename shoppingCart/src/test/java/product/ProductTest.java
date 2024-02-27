package product;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductTest {

    @Test
    void shouldReturnAssignedProductNameAndQuantityAndUnitPrice(){
        Product cheerios = new Product("cheerios",8.43);
        var result = cheerios.getName();
        assertEquals("cheerios",result);
        assertEquals(1,cheerios.getQuantity());
        assertEquals(8.43,cheerios.getUnitPrice());
    }

    @Test
    void shouldIncreaseProductBy1Unit(){
        Product cheerios = new Product("cheerios",8.43);
        var result = cheerios.getName();
        assertEquals("cheerios",result);
        assertEquals(8.43,cheerios.getUnitPrice());
        assertEquals(1,cheerios.getQuantity());

        cheerios.increaseQuantityByOneUnit();
        assertEquals(2,cheerios.getQuantity());
        assertEquals(8.43,cheerios.getUnitPrice());
    }
}