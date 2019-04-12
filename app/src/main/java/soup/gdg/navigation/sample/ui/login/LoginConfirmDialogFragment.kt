package soup.gdg.navigation.sample.ui.login

import android.app.Activity
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatDialogFragment
import androidx.fragment.app.Fragment

class LoginConfirmDialogFragment : AppCompatDialogFragment() {

    private val buttonClickListener = DialogInterface.OnClickListener { _, which ->
        when (which) {
            Dialog.BUTTON_POSITIVE -> setResultAndDismiss(Activity.RESULT_OK)
            Dialog.BUTTON_NEUTRAL,
            Dialog.BUTTON_NEGATIVE -> setResultAndDismiss(Activity.RESULT_CANCELED)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return AlertDialog.Builder(requireContext())
            .setMessage("로그인이 필요한 서비스입니다")
            .setPositiveButton("로그인", buttonClickListener)
            .setNegativeButton("취소", buttonClickListener)
            .create()
    }

    override fun onCancel(dialog: DialogInterface) {
        super.onCancel(dialog)
        setResultAndDismiss(Activity.RESULT_CANCELED)
    }

    private fun setResultAndDismiss(resultCode: Int) {
        targetFragment?.onActivityResult(targetRequestCode, resultCode, null)
        dismiss()
    }

    companion object {

        fun show(hostFragment: Fragment, requestCode: Int) {
            return LoginConfirmDialogFragment()
                .apply { setTargetFragment(hostFragment, requestCode) }
                .show(hostFragment.requireFragmentManager(), "dialog")
        }
    }
}
