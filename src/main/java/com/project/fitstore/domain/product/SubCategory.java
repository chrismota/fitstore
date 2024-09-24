package com.project.fitstore.domain.product;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum SubCategory {
    BICICLETA(Category.AEROBICOS),
    ESTEIRA(Category.AEROBICOS),
    ELIPTICO(Category.AEROBICOS),
    BARRA(Category.ACESSORIOS),
    SUPORTE(Category.ACESSORIOS),
    PUXADOR(Category.ACESSORIOS),
    BOLA(Category.ACESSORIOS),
    COLCHONETE(Category.ACESSORIOS),
    CANELEIRA(Category.ACESSORIOS),
    CORDA(Category.ACESSORIOS),
    HALTER(Category.PESOS),
    ANILHA(Category.PESOS),
    KETLEBELL(Category.PESOS),
    PRESILHA(Category.PESOS);

    private final Category category;
}
