package com.example.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.getOrAwaitValueTest
import com.example.repo.ApiService
import com.example.repo.MyRepo
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.setMain
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
@RunWith(JUnit4::class)
class MainViewModelTest {

    private lateinit var viewModel: MainViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    var testDispatcher = StandardTestDispatcher()

    @Mock
    lateinit var apiService: ApiService


    @Before
    fun init() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(testDispatcher)
        viewModel = MainViewModel(apiService)
    }

    @Test
    fun testGetUsers(){
        runBlocking {
            Mockito.`when`(apiService.getApiUsers()).thenReturn(listOf("Balu","BK","Ram"))
            viewModel.getUsers()
            assertThat(viewModel.liveDataList.getOrAwaitValueTest()?.size).isEqualTo(3)
        }
    }
}