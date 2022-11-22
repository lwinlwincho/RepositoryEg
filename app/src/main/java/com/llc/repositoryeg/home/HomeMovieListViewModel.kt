package com.llc.moviebd.ui.home

import androidx.lifecycle.*
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.llc.repositoryeg.MovieRepository
import com.llc.repositoryeg.model.MovieModel
import com.llc.repositoryeg.repository.RepositoryApplication
import kotlinx.coroutines.launch
import java.lang.Exception

class HomeMovieListViewModel(private var movieRepository: MovieRepository)  : ViewModel() {

    private val _nowShowingUiEvent = MutableLiveData<MovieUpcomingEvent>()
    val nowShowingUiEvent: LiveData<MovieUpcomingEvent> = _nowShowingUiEvent

    private val _popularUiEvent = MutableLiveData<MovieUpcomingEvent>()
    val popularUiEvent: LiveData<MovieUpcomingEvent> = _popularUiEvent

    init {
        getNowShowing()
        getPopular()
    }

    private fun getNowShowing() {

        _nowShowingUiEvent.value = MovieUpcomingEvent.Loading

        viewModelScope.launch {
            try {
                //get data from web server
               // val result = MovieAPI.retrofitService.getNowPlaying().results.sortedByDescending { it.releaseDate }

               // val marsPhotosRepository = DefaultMovieRepository()

                val result = movieRepository.getNowShowingMovies().results.sortedByDescending { it.releaseDate }
                _nowShowingUiEvent.value = MovieUpcomingEvent.Success(result)
            } catch (e: Exception) {
                _nowShowingUiEvent.value = MovieUpcomingEvent.Failure(e.message.toString())
            }
        }
    }

    private fun getPopular() {
        _popularUiEvent.value = MovieUpcomingEvent.Loading

        viewModelScope.launch {
            try {
                //get data from web server
               /* val popularResult =
                    MovieAPI.retrofitService.getPopular().results.sortedByDescending { it.vote_average }*/

                val popularResult =
                    movieRepository.getPopularMovies().results.sortedByDescending { it.vote_average }
                _popularUiEvent.value = MovieUpcomingEvent.Success(popularResult)

            } catch (e: Exception) {
                _popularUiEvent.value = MovieUpcomingEvent.Failure(e.message.toString())
            }
        }
    }
}


//You can store many data class and singleton obj in sealed class
sealed class MovieUpcomingEvent {
    data class Success(val movieList: List<MovieModel>) : MovieUpcomingEvent()
    data class Failure(val message: String) : MovieUpcomingEvent()
    object Loading : MovieUpcomingEvent()
}