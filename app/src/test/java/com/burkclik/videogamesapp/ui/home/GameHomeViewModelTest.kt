package com.burkclik.videogamesapp.ui.home

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.burkclik.videogamesapp.GameFactory
import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.domain.GameUseCase
import com.google.common.truth.Truth
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.MockK
import io.mockk.verify
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class GameHomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var gameUseCase: GameUseCase

    private lateinit var gameHomeViewModel: GameHomeViewModel

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        gameHomeViewModel = GameHomeViewModel(gameUseCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }


    @Test
    fun `given success response, when fetchGame called, then should games triggered`() {

        // Given
        val gameDetail = GameFactory.createGame(id = 1, name = "Borderlands 2")
        val successResource = Resource.Success(listOf(gameDetail))

        every { gameUseCase.fetchGames() } answers {
            flow { emit(successResource) }
        }

        // When
        gameHomeViewModel.fetchGames()

        // Then
        Truth.assertThat(gameHomeViewModel.games.value?.get(0)).isEqualTo(gameDetail)

        verify {
            gameUseCase.fetchGames()
        }
    }

    @Test
    fun `given error response, when fetchGame called, then should errorLiveData trigger`() {

        // Given
        val exception = Throwable("Bir hata oluştu")
        val errorResource = Resource.Error(exception)

        every { gameUseCase.fetchGames() } answers {
            flow { emit(errorResource) }
        }

        // When
        gameHomeViewModel.fetchGames()

        // Then
        Truth.assertThat(gameHomeViewModel.errorLiveData.value).isEqualTo(exception.message)

        verify { gameUseCase.fetchGames() }
    }
}