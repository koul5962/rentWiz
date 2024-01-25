package com.rentwiz.app.compose.model

class PersonData {
    fun getAllData() : List<Person> {
        return listOf(
            Person(
                id = 0,
                name = "Sunil Koul",
                age = 20
            ),
            Person(
                id = 1,
                name = "Rishab Raina",
                age = 30
            ),
            Person(
                id = 2,
                name = "Kartik Garg",
                age = 40
            ),
            Person(
                id = 3,
                name = "Akshay Bhat",
                age = 50
            )
        )
    }
}