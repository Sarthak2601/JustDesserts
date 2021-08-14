package com.example.justdesserts.shared.repository

import com.example.com.justdesserts.*
import com.example.com.justdesserts.type.DessertInput
import com.example.justdesserts.shared.ApolloProvider
import com.example.justdesserts.shared.cache.*
import kotlinx.coroutines.flow.single

class DessertRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {

    suspend fun getDesserts(page: Int, size: Int): Desserts? {
        val response = apolloClient.query(GetDessertsQuery(page, size)).execute().single()
        return response.data?.desserts?.toDesserts()
    }

    suspend fun getDessert(dessertId: String): DessertDetail? {
        val response = apolloClient.query(GetDessertQuery(dessertId)).execute().single()
        return response.data?.dessert?.toDessertDetail()
    }

    suspend fun newDessert(dessertInput: DessertInput): Dessert? {
        val response = apolloClient.mutate(NewDessertMutation(dessertInput)).execute().single()
        return response.data?.createDessert?.toDessert()
    }

    suspend fun updateDessert(dessertId: String, dessertInput: DessertInput): Dessert? {
        val response = apolloClient.mutate(UpdateDessertMutation(dessertId, dessertInput)).execute().single()
        return response.data?.updateDessert?.toDessert()
    }

    suspend fun deleteDessert(dessertId: String): Boolean? {
        val response = apolloClient.mutate(DeleteDessertMutation(dessertId)).execute().single()
        return response.data?.deleteDessert
    }

    fun saveFavourite(dessert: Dessert){
        database.saveDessert(dessert)
    }

    fun removeFavourite(dessertId: String){
        database.deleteDessert(dessertId)
    }

    fun updateFavourite(dessert: Dessert){
        database.updateDessert(dessert)
    }

    fun getFavouriteDessert(dessertId: String): Dessert? = database.getDessertById(dessertId)

    fun getFavouriteDesserts(): List<Dessert> = database.getDesserts()
}
