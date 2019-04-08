package soup.gdg.navigation.sample.data.store

import soup.gdg.navigation.sample.data.model.Movie
import soup.gdg.navigation.sample.data.model.MovieId

interface MovieDataStore {

    fun getList(): List<Movie>

    fun getDetail(id: MovieId): Movie
}

class MovieDataStoreImpl : MovieDataStore {

    private val fakeMovieList = (1..12)
        .map { it.toString().padStart(2, '0') }
        .map { id ->
            Movie(
                id = "ID_$id",
                name = "Poster $id",
                posterUrl = "$BASE_POSTER_URL$id.jpg?raw=true"
            )
        }
        .toList()

    override fun getList(): List<Movie> {
        return fakeMovieList
    }

    override fun getDetail(id: MovieId): Movie {
        return fakeMovieList.find { it.id == id }!!
    }

    companion object {

        private const val BASE_POSTER_URL =
            "https://raw.githubusercontent.com/fornewid/GDGAndroid-atPangyo-2019-Navigation-Sample/master/posters/"
    }
}
