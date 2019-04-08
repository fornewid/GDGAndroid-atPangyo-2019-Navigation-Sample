package soup.gdg.navigation.sample.data.store

import androidx.collection.ArraySet
import soup.gdg.navigation.sample.data.model.MovieId

interface BookmarkDataStore {

    fun getAllBookmarks(): List<MovieId>

    fun addBookmark(movieId: MovieId)

    fun removeBookmark(movieId: MovieId)

    fun isBookmark(movieId: MovieId): Boolean
}

class BookmarkDataStoreImpl : BookmarkDataStore {

    private val inMemoryBookmarkSet = ArraySet<MovieId>()

    override fun addBookmark(movieId: MovieId) {
        inMemoryBookmarkSet.add(movieId)
    }

    override fun removeBookmark(movieId: MovieId) {
        inMemoryBookmarkSet.remove(movieId)
    }

    override fun getAllBookmarks(): List<MovieId> {
        return inMemoryBookmarkSet.toList()
    }

    override fun isBookmark(movieId: MovieId): Boolean {
        return inMemoryBookmarkSet.contains(movieId)
    }
}
