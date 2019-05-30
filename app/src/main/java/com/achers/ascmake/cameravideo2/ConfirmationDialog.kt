package com.achers.ascmake.cameravideo2

/**
 * Created on 2019/4/22 18:48
 * <p>
 * author lhm
 * <p>
 * Description:
 * <p>
 * Remarks:
 */
import android.Manifest
import android.app.AlertDialog
import android.os.Bundle
import android.support.v4.app.DialogFragment
import com.achers.ascmake.R

/**
 * Shows OK/Cancel confirmation dialog about camera permission.
 */
class ConfirmationDialog : DialogFragment() {

    val VIDEO_PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO)
    val REQUEST_VIDEO_PERMISSIONS = 1

    override fun onCreateDialog(savedInstanceState: Bundle?) =
            AlertDialog.Builder(activity)
                    .setMessage("permission_request")
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        parentFragment?.requestPermissions(VIDEO_PERMISSIONS,
                                REQUEST_VIDEO_PERMISSIONS)
                    }
                    .setNegativeButton(android.R.string.cancel) { _,_ ->
                        parentFragment?.activity?.finish()
                    }
                    .create()

}