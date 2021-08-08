package com.example.justdesserts.shared

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.network.http.ApolloHttpNetworkTransport

class ApolloProvider {
    internal val apolloClient: ApolloClient = ApolloClient(
        networkTransport = ApolloHttpNetworkTransport(
            serverUrl = "https://sarthak-ktor-graphql.herokuapp.com",
            headers = mapOf(
                "Accept" to "application/json",
                "Content-Type" to "application/json"
            )
        )
    )
}
