package soup.gdg.navigation.sample.data

import soup.gdg.navigation.sample.data.model.Movie
import soup.gdg.navigation.sample.data.model.MovieId
import soup.gdg.navigation.sample.data.store.BookmarkDataStore
import soup.gdg.navigation.sample.data.store.MovieDataStore

interface SampleRepository {

    fun getMovieList(): List<Movie>

    fun getMovieDetail(movieId: MovieId): Movie

    fun getBookmarkMovieList(): List<Movie>

    fun addBookmark(movie: Movie)

    fun removeBookmark(movie: Movie)
}

class SampleRepositoryImpl(
    private val movieStore: MovieDataStore,
    private val bookmarkStore: BookmarkDataStore
) : SampleRepository {

    override fun getMovieList(): List<Movie> {
        return movieStore.getList()
    }

    override fun getMovieDetail(movieId: MovieId): Movie {
        return movieStore.getDetail(movieId)
    }

    override fun getBookmarkMovieList(): List<Movie> {
        return movieStore.getList()
            .filter { bookmarkStore.isBookmark(it.id) }
    }

    override fun addBookmark(movie: Movie) {
        bookmarkStore.addBookmark(movie.id)
    }

    override fun removeBookmark(movie: Movie) {
        bookmarkStore.removeBookmark(movie.id)
    }
}
