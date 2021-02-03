package com.example.letscube.ui

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Spinner
import androidx.fragment.app.DialogFragment
import com.example.letscube.R
import java.lang.IllegalStateException

class TimerOptionsDialog(val listener: SaveTimerOptionsListener) : DialogFragment(), AdapterView.OnItemSelectedListener
{
    lateinit var usingInspection: CheckBox
    lateinit var  inputOptionsSpinner: Spinner
    lateinit var selectedInput: String
    lateinit var sharedPreferences: SharedPreferences
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val inflater = requireActivity().layoutInflater.inflate(R.layout.dialog_timer_options, null)
            sharedPreferences = requireActivity().getPreferences(Context.MODE_PRIVATE)

            inputOptionsSpinner = inflater.findViewById(R.id.input_spinner)
            usingInspection = inflater.findViewById(R.id.inspection_checkbox)

           val adapter: ArrayAdapter<CharSequence> = ArrayAdapter.createFromResource(
                requireContext(),
                R.array.input_spinner_array,
                android.R.layout.simple_spinner_item)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            inputOptionsSpinner.adapter = adapter

            inputOptionsSpinner.onItemSelectedListener = this

            selectedInput = inputOptionsSpinner.getItemAtPosition(0).toString()
            usingInspection.isChecked = sharedPreferences.getBoolean("usingInspection", false)

            builder.setView(inflater)
                .setPositiveButton("Save") { _, _ ->
                    listener.saveTimerOptions(usingInspection.isChecked, selectedInput)
                }
                .setNegativeButton("Cancel") { dialog, _ ->
                    dialog.cancel()
                }

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    interface SaveTimerOptionsListener
    {
        fun saveTimerOptions(isUsingInspection: Boolean, inputMethod: String)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long)
    {
        selectedInput = parent?.getItemAtPosition(position).toString()
    }

}