package com.example.justdesserts.shared.repository

import com.example.justdesserts.shared.ApolloProvider
import com.example.justdesserts.shared.cache.Database

open class BaseRepository(apolloProvider: ApolloProvider) {
    val apolloClient = apolloProvider.apolloClient
    val database: Database = apolloProvider.database
}
