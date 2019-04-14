package soup.gdg.navigation.sample.navigation

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.util.Log
import androidx.annotation.CallSuper
import androidx.annotation.IdRes
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import androidx.navigation.NavigatorProvider
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.R

@Navigator.Name("dialog_fragment")
class DialogFragmentNavigator(
    private val context: Context,
    private val fragmentManager: FragmentManager
) : Navigator<DialogFragmentNavigator.Destination>() {

    companion object {

        private const val TAG = "DialogFragmentNavigator"
    }

    override fun navigate(
        destination: Destination,
        args: Bundle?,
        navOptions: NavOptions?,
        navigatorExtras: Extras?
    ): NavDestination? {
        if (fragmentManager.isStateSaved) {
            Log.i(TAG, "Ignoring navigate() call: FragmentManager has already saved its state")
            return null
        }
        var className = destination.getClassName()
        if (className[0] == '.') {
            className = context.packageName + className
        }
        val frag = instantiateDialogFragment(context, fragmentManager, className)
        frag.arguments = args

        @IdRes val destId = destination.id
        frag.setTargetFragment(
            fragmentManager.primaryNavigationFragment,
            destId /* requestCode */
        )
        frag.show(fragmentManager, "dialog-$destId")
        return null
    }

    private fun instantiateDialogFragment(
        context: Context,
        fragmentManager: FragmentManager,
        className: String
    ): DialogFragment {
        return fragmentManager.fragmentFactory.instantiate(context.classLoader, className)
                as DialogFragment
    }

    override fun createDestination(): Destination {
        return Destination(this)
    }

    override fun popBackStack(): Boolean {
        return true
    }

    @NavDestination.ClassType(Fragment::class)
    class Destination : NavDestination {

        private var className: String? = null

        constructor(navigatorProvider: NavigatorProvider) :
                super(navigatorProvider.getNavigator(FragmentNavigator::class.java))

        constructor(fragmentNavigator: Navigator<out Destination>) :
                super(fragmentNavigator)

        @CallSuper
        override fun onInflate(context: Context, attrs: AttributeSet) {
            super.onInflate(context, attrs)
            val a = context.resources.obtainAttributes(attrs, R.styleable.FragmentNavigator)
            val className = a.getString(R.styleable.FragmentNavigator_android_name)
            if (className != null) {
                setClassName(className)
            }
            a.recycle()
        }

        fun setClassName(className: String): Destination {
            this.className = className
            return this
        }

        fun getClassName(): String {
            return className ?: throw IllegalStateException("Fragment class was not set")
        }
    }
}
