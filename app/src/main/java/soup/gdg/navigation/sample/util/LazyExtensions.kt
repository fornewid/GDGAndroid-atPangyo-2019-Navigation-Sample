package soup.gdg.navigation.sample.util

fun <T> lazyUnsafe(initializer: () -> T): Lazy<T> {
    return lazy(LazyThreadSafetyMode.NONE, initializer)
}
