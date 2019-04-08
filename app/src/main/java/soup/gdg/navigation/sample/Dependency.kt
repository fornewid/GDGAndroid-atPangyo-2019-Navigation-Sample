package soup.gdg.navigation.sample

import soup.gdg.navigation.sample.data.SampleRepository
import soup.gdg.navigation.sample.data.SampleRepositoryImpl
import soup.gdg.navigation.sample.data.store.BookmarkDataStore
import soup.gdg.navigation.sample.data.store.BookmarkDataStoreImpl
import soup.gdg.navigation.sample.data.store.MovieDataStore
import soup.gdg.navigation.sample.data.store.MovieDataStoreImpl

object Dependency {

    val repository: SampleRepository by lazy {
        SampleRepositoryImpl(movieDataStore, bookmarkDataStore)
    }

    private val movieDataStore: MovieDataStore by lazy {
        MovieDataStoreImpl()
    }

    private val bookmarkDataStore: BookmarkDataStore by lazy {
        BookmarkDataStoreImpl()
    }
}
