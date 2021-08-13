package com.example.justdesserts.shared.cache

import com.example.com.justdesserts.*

data class Reviews(
    val results: List<Dessert>,
    val info: GetDessertsQuery.Info
)

data class ReviewDetail(
    val dessert: Dessert,
    val reviews: List<Review>
)

fun GetDessertQuery.Review.toReview() = Review(id, dessertId, userId, text, rating.toLong())

fun GetReviewQuery.Review.toReview() = Review(id, dessertId, userId, text, rating.toLong())

fun NewReviewMutation.CreateReview.toReview() = Review(id, dessertId, userId, text, rating.toLong())

fun UpdateReviewMutation.UpdateReview.toReview() = Review(id, dessertId, userId, text, rating.toLong())
