package org.kiwix.kiwixmobile.utils

import android.app.Activity
import android.app.AlertDialog
import org.kiwix.kiwixmobile.R
import org.kiwix.kiwixmobile.settings.KiwixSettingsActivity
import javax.inject.Inject

class AlertDialogShower @Inject constructor(
  private val activity: Activity,
  private val sharedPreferenceUtil: SharedPreferenceUtil
) : DialogShower {
  override fun show(
    dialog: KiwixDialog,
    vararg clickListener: () -> Unit
  ) {

    AlertDialog.Builder(activity, dialogStyle())
        .setTitle(dialog.title)
        .setMessage(dialog.message)
        .setPositiveButton(dialog.positiveMessage) { _, _ ->
          clickListener.getOrNull(0)
              ?.invoke()
        }
        .setNegativeButton(dialog.negativeMessage) { _, _ ->
          clickListener.getOrNull(1)
              ?.invoke()
        }
        .show()
  }

  private fun dialogStyle() =
    if (KiwixSettingsActivity.nightMode(sharedPreferenceUtil)) {
      R.style.AppTheme_Dialog_Night
    } else {
      R.style.AppTheme_Dialog
    }
}