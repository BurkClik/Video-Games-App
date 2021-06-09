package com.burkclik.videogamesapp.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.burkclik.videogamesapp.GameDetailFactory
import com.burkclik.videogamesapp.common.Resource
import com.burkclik.videogamesapp.domain.GameDetailUseCase
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
class GameDetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @MockK
    lateinit var gameDetailUseCase: GameDetailUseCase

    var savedStateHandle: SavedStateHandle = SavedStateHandle()

    private lateinit var gameDetailViewModel: GameDetailViewModel

    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        Dispatchers.setMain(testDispatcher)
        savedStateHandle.set("gameId", 2)
        gameDetailViewModel = GameDetailViewModel(gameDetailUseCase, savedStateHandle)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `given success response, when fetchGame called, then should game triggered`() {

        // Given
        val gameDetail = GameDetailFactory.createGameDetail(name = "Borderlands 2")
        val successResource = Resource.Success(gameDetail)


        every { gameDetailUseCase.fetchDetailGame(savedStateHandle.get("gameId")!!) } answers {
            flow { emit(successResource) }
        }

        // When
        gameDetailViewModel.fetchGame()

        // Then
        Truth.assertThat(gameDetailViewModel.game.value).isEqualTo(gameDetail)

        verify {
            gameDetailUseCase.fetchDetailGame(savedStateHandle.get("gameId")!!)
        }
    }

    @Test
    fun `given error response, when fetchGame called, then should errorLiveData trigger`() {

        // Given
        val exception = Throwable("Bir hata oluştu")
        val errorResource = Resource.Error(exception)

        every { gameDetailUseCase.fetchDetailGame(savedStateHandle.get("gameId")!!) } answers {
            flow { emit(errorResource) }
        }

        // When
        gameDetailViewModel.fetchGame()

        // Then
        Truth.assertThat(gameDetailViewModel.errorLiveData.value).isEqualTo(exception.message)

        verify { gameDetailUseCase.fetchDetailGame(savedStateHandle.get("gameId")!!) }
    }
}