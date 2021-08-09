package com.example.justdesserts.shared.cache

class Database(databaseDriverFactory: DatabaseDriverFactory) {
    private val database = JustDesserts(databaseDriverFactory.createDriver())
    private val dbQuery = database.justDessertsQueries

    internal fun clearDatabase(){
        dbQuery.transaction {
            dbQuery.removeAllDesserts()
        }
    }

    internal fun getDesserts(): List<Dessert>{
        return dbQuery.selectAllDesserts().executeAsList()
    }

    internal fun getDessertById(desserId: String): Dessert?{
        return dbQuery.selectDessertById(desserId).executeAsOneOrNull()
    }

    internal fun saveDessert(dessert: Dessert){
        dbQuery.transaction {
            insertDessert(dessert)
        }
    }

    internal fun updateDessert(dessert: Dessert){
        dbQuery.transaction {
            updateDessertById(dessert)
        }
    }

    internal fun deleteDessert(dessertId: String){
        dbQuery.transaction {
            removeDessert(dessertId)
        }
    }

    internal fun getUserState(): UserState? {
        return dbQuery.selectUserState().executeAsOneOrNull()
    }

    internal fun saveUserState(userId: String, token: String){
        dbQuery.transaction {
            insertUserState(userId, token)
        }
    }

    internal fun deleteUserState(){
        dbQuery.transaction {
            removeUserState()
        }
    }

    private fun insertDessert(dessert: Dessert) {
        dbQuery.insertDessert(
            id = dessert.id,
            userId = dessert.userId,
            name = dessert.name,
            description = dessert.description,
            imageUrl = dessert.imageUrl
        )
    }

    private fun updateDessertById(dessert: Dessert) {
        dbQuery.updateDessertById(
            id = dessert.id,
            name = dessert.name,
            description = dessert.description,
            imageUrl = dessert.imageUrl
        )
    }

    private fun removeDessert(dessertId: String){
        dbQuery.removeDessertById(dessertId)
    }

    private fun insertUserState(userId: String, token: String){
        dbQuery.insertUserState(userId, token)
    }

    private fun removeUserState(){
        dbQuery.removeUserState()
    }
}
