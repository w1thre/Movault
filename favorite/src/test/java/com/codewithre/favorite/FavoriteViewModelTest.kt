package com.codewithre.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.codewithre.core.domain.model.Movie
import com.codewithre.core.domain.usecase.MovieUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class FavoriteViewModelTest {
    
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    
    @Mock
    private lateinit var movieUseCase: MovieUseCase
    
    private lateinit var favoriteViewModel: FavoriteViewModel
    
    @Mock
    private lateinit var observer: Observer<List<Movie>>
    
    private val testDispatcher = UnconfinedTestDispatcher()
    
    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(testDispatcher)
        
        val mockMovies = listOf(
            Movie(
                id = 2,
                title = "Movie 2",
                overview = "Overview 2",
                posterPath = "/path2.jpg",
                voteAverage = 7.5,
                isFavorite = true
            )
        )
        
        // Mock behavior
        Mockito.`when`(movieUseCase.getFavMovie()).thenReturn(flowOf(mockMovies))
        
        // Instantiate ViewModel
        favoriteViewModel = FavoriteViewModel(movieUseCase)
    }
    
    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
    
    @Test
    fun `when getFavMovie should return favorite movies`() = runTest {
        // Observe LiveData
        favoriteViewModel.favMovie.observeForever(observer)
        
        // Verify observer is triggered
        Mockito.verify(observer).onChanged(
            listOf(
                Movie(
                    id = 2,
                    title = "Movie 2",
                    overview = "Overview 2",
                    posterPath = "/path2.jpg",
                    voteAverage = 7.5,
                    isFavorite = true
                )
            )
        )
        
        // Clean up observer
        favoriteViewModel.favMovie.removeObserver(observer)
    }
}
