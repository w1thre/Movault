package com.codewithre.movault

import com.codewithre.core.data.Resource
import com.codewithre.core.domain.model.Movie
import com.codewithre.core.domain.repository.IMovieRepository
import com.codewithre.core.domain.usecase.MovieInteractor
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MovieInteractorTest {
    
    @Mock
    private lateinit var movieRepository: IMovieRepository
    
    @InjectMocks
    private lateinit var movieInteractor: MovieInteractor
    
    @Before
    fun setUp() {
        movieInteractor = MovieInteractor(movieRepository)
    }
    
    @Test
    fun `should return all movies when getAllMovie is called`() {
        // Given
        val movieList = listOf(
            Movie(
                id = 1,
                title = "Movie 1",
                overview = "Overview 1",
                posterPath = "/path1.jpg",
                voteAverage = 8.0,
                isFavorite = false
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                overview = "Overview 2",
                posterPath = "/path2.jpg",
                voteAverage = 7.5,
                isFavorite = true
            )
        )
        val resourceFlow = flowOf(Resource.Success(movieList))
        given(movieRepository.getAllMovie()).willReturn(resourceFlow)
        
        // When
        movieInteractor.getAllMovie()
        
        // Then
        then(movieRepository).should().getAllMovie()
    }
    
    @Test
    fun `should return favorite movies when getFavMovie is called`() {
        // Given
        val movieList = listOf(
            Movie(
                id = 2,
                title = "Movie 2",
                overview = "Overview 2",
                posterPath = "/path2.jpg",
                voteAverage = 7.5,
                isFavorite = true
            )
        )
        val resourceFlow = flowOf(movieList)
        given(movieRepository.getFavMovie()).willReturn(resourceFlow)
        
        // When
        movieInteractor.getFavMovie()
        
        // Then
        then(movieRepository).should().getFavMovie()
    }
    
    @Test
    fun `should set movie as favorite when setFavMovie is called`() {
        // Given
        val movie = Movie(
            id = 1,
            title = "Movie 1",
            overview = "Overview 1",
            posterPath = "/path1.jpg",
            voteAverage = 8.0,
            isFavorite = false
        )
        val isFavorite = true
        
        // When
        movieInteractor.setFavMovie(movie, isFavorite)
        
        // Then
        then(movieRepository).should().setFavMovie(movie, isFavorite)
    }
}