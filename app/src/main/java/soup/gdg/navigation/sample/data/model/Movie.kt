package soup.gdg.navigation.sample.data.model

data class Movie(
    val id: MovieId,
    val name: String,
    val posterUrl: Url?,
    val favorite: Boolean = false
)
