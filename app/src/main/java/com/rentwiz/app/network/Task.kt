package com.rentwiz.app.network

data class Task(
    val description: String,
    val isComplete: Boolean,
    val priority: Int
)

class DataSource {
    companion object {
        @JvmStatic
        fun createTaskList(): List<Task> {
            return listOf(
                Task( "Take out the trash", true, 3),
                Task("Walk the dog", false, 2),
                Task("Make my bed", true, 1),
                Task("Unload the dishwasher", false, 0),
                Task("Make dinner", true, 5)
            )
        }
    }
}
