package com.project.fitstore.domain.product;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public enum SubCategory {
    BICICLETA(Category.AEROBICOS),
    ESTEIRA(Category.AEROBICOS),
    ELIPTICOS(Category.AEROBICOS),
    BARRA(Category.ACESSORIOS),
    SUPORTE(Category.ACESSORIOS),
    PUXADOR(Category.ACESSORIOS),
    BOLA(Category.ACESSORIOS),
    COLCHONETE(Category.ACESSORIOS),
    HALTER(Category.PESOS),
    ANILHA(Category.PESOS),
    KETLEBELL(Category.PESOS),
    CANELEIRA(Category.PESOS);

    private final Category category;

    public static List<SubCategory> getByCategory(Category category) {
        List<SubCategory> categories = new ArrayList<>();

        for (SubCategory value : SubCategory.values()) {
            if (value.category.equals(category)) {
                categories.add(value);
            }
        }
        return categories;
    }
}
