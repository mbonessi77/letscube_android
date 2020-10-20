package com.example.letscube.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.example.letscube.R
import java.lang.IllegalStateException

class LoginDialog(var listener: LoginListener) : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        super.onCreateDialog(savedInstanceState)

        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater.inflate(R.layout.login_dialog, null)

            builder.setView(inflater)
                .setPositiveButton("Sign in") { _, _ ->
                    listener.getEmailAndPassword(inflater.findViewById<EditText>(R.id.username).text.toString(),
                        inflater.findViewById<EditText>(R.id.password).text.toString())

                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface LoginListener {
        fun getEmailAndPassword(email: String, password: String)
    }
}