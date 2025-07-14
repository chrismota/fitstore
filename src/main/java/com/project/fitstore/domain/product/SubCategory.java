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
    KETTLEBELL(Category.PESOS),
    PRESILHA(Category.PESOS),
    WHEY(Category.SUPLEMENTOS),
    CREATINA(Category.SUPLEMENTOS),
    PRE_TREINO(Category.SUPLEMENTOS),
    CAMISA(Category.ROUPAS),
    SHORT(Category.ROUPAS),
    LEGGING(Category.ROUPAS);

    private final Category category; 
}
