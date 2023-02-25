package com.example.repo

class MyRepo : ApiService {
    override fun getApiUsers(): List<String> {
        return listOf("Balu","Balli")
    }
}