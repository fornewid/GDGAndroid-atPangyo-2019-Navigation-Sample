package soup.gdg.navigation.sample.util

import androidx.recyclerview.widget.DiffUtil

class IdBasedDiffCallback<T>(
    private val id: T.() -> String
) : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.id() == newItem.id()
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }
}
