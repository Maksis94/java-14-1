package org.example;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ShopRepositoryTest {
    Product product1 = new Product(1, "Продукт 1", 1000);
    Product product2 = new Product(2, "Продукт 2", 2000);
    Product product3 = new Product(3, "Продукт 3", 3000);

    ShopRepository repository = new ShopRepository();

    @Test
    public void shouldDeleteExistingElement() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        repository.remove(2);
        Assertions.assertArrayEquals(repository.findAll(), new Product[]{product1, product3});
    }

    @Test
    public void shouldThrowNotFoundException() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Assertions.assertThrows(NotFoundException.class, () -> {
            repository.remove(100);
        });
    }

    @Test
    public void shouldAddElements() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Assertions.assertArrayEquals(repository.findAll(), new Product[]{product1, product2, product3});
    }

    @Test
    public void shouldThrowIfElementAlreadyExists() {
        repository.add(product1);
        repository.add(product2);
        repository.add(product3);

        Product product = new Product(3, "Тестовый продукт", 10000);

        Assertions.assertThrows(AlreadyExistsException.class, () -> {
            repository.add(product);
        });
    }
}
