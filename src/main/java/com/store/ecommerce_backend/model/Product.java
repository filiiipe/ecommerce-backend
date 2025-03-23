package com.store.ecommerce_backend.model;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Product {

    @ManyToOne
    @JoinColumn(name = "user_id,", nullable = false)
    private User user;
}