package com.example.justdesserts.shared.cache

import com.example.com.justdesserts.*

data class Desserts(
    val results: List<Dessert>,
    val info: GetDessertsQuery.Info
)

data class DessertDetail(
    val dessert: Dessert,
    val reviews: List<Review>
)

fun GetDessertsQuery.Result.toDessert() = Dessert(id, userId, name, description, imageUrl)

fun GetDessertsQuery.Desserts.toDesserts() = Desserts(
    results.map { it.toDessert() }, info
)

fun GetDessertQuery.Dessert.toDessert() = Dessert(id, userId, name, description, imageUrl)

fun GetDessertQuery.Dessert.toDessertDetail() = DessertDetail(
    this.toDessert(),
    reviews.map { it.toReview() }
)

fun GetProfileQuery.Dessert.toDessert() = Dessert(id, userId, name, description, imageUrl)

fun NewDessertMutation.CreateDessert.toDessert() = Dessert(id, userId, name, description, imageUrl)

fun UpdateDessertMutation.UpdateDessert.toDessert() =
    Dessert(id, userId, name, description, imageUrl)
