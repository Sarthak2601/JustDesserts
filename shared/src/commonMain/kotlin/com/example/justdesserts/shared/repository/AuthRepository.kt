package com.example.justdesserts.shared.repository

import com.example.com.justdesserts.GetProfileQuery
import com.example.com.justdesserts.SignInMutation
import com.example.com.justdesserts.SignUpMutation
import com.example.com.justdesserts.type.UserInput
import com.example.justdesserts.shared.ApolloProvider
import com.example.justdesserts.shared.cache.Dessert
import com.example.justdesserts.shared.cache.UserState
import com.example.justdesserts.shared.cache.toDessert
import kotlinx.coroutines.flow.single

class AuthRepository(apolloProvider: ApolloProvider) : BaseRepository(apolloProvider) {

    suspend fun signIn(userInput: UserInput): String {
        val response = apolloClient.mutate(SignInMutation(userInput)).execute().single()
        response.data?.signIn?.let { data ->
            data.user.also {
                database.saveUserState(it.id, data.token)
            }
            return data.token
        }
        throw Exception("Couldn't sign in")
    }

    suspend fun signUp(userInput: UserInput): String {
        val response = apolloClient.mutate(SignUpMutation(userInput)).execute().single()
        response.data?.signUp?.let { data ->
            data.user.also {
                database.saveUserState(it.id, data.token)
            }
            return data.token
        }
        throw Exception("Couldn't sign up")
    }

    suspend fun getProfileDesserts(): List<Dessert> {
        val response = apolloClient.query(GetProfileQuery()).execute().single()
        return response.data?.getProfile?.desserts?.map { it.toDessert() } ?: emptyList()
    }

    fun getUserState(): UserState? {
        return database.getUserState()
    }

    fun deleteUserState() {
        database.deleteUserState()
    }
}
