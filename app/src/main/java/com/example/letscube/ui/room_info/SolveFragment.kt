package com.example.letscube.ui.room_info

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.letscube.R
import com.google.android.material.textfield.TextInputLayout
import java.util.*

class SolveFragment : Fragment(), Runnable {

    var seconds: Int = 0
    var running: Boolean = false
    var usingInspection: Boolean = false
    var inspecting: Boolean = false
    var inputMethod: String? = null
    lateinit var clickableArea: View
    lateinit var timerText: TextView
    lateinit var handler: Handler
    lateinit var time: String
    lateinit var timeInput: TextInputLayout
    lateinit var sharedPrefs: SharedPreferences
    lateinit var inspectionTimer: CountDownTimer

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val v = inflater.inflate(R.layout.fragment_solve, container, false)
        clickableArea = v.findViewById(R.id.background)
        timerText = v.findViewById(R.id.tv_solve_timer)
        timeInput = v.findViewById(R.id.et_time_input)

        sharedPrefs = requireActivity().getPreferences(Context.MODE_PRIVATE)

        getPrefs()

        if (inputMethod.equals("Internal Timer"))
        {
            timerText.visibility = View.VISIBLE
            timeInput.visibility = View.INVISIBLE
        } else {
            timerText.visibility = View.INVISIBLE
            timeInput.visibility = View.VISIBLE
        }

        clickableArea.setOnClickListener {
            if (usingInspection)
            {
                if (inputMethod.equals("Internal Timer"))
                {
                    if (!inspecting && !running)
                    {
                        timerText.setTextColor(activity?.resources?.getColor(R.color.red)!!)
                        inspecting = true
                        inspect()
                    } else {
                        timerText.setTextColor(activity?.resources?.getColor(R.color.gray)!!)
                        inspectionTimer.cancel()
                        inspecting = false
                        startStopSolveTimer()
                    }
                }
            } else {
                startStopSolveTimer()
            }
        }
        return v
    }

    private fun startStopSolveTimer()
    {
        if (!running) {
            running = true
            runTimer()
        } else {
            seconds = 0
            time = ""
            running = false
            stopTimer()
        }
    }

    private fun inspect()
    {
        inspectionTimer = object : CountDownTimer(15000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerText.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {

            }
        }.start()
    }

    private fun runTimer()
    {
        handler = Handler()
        handler.postDelayed(this, 0)
    }

    private fun stopTimer()
    {
        handler.removeCallbacks(this)
    }

    override fun run()
    {
        val hours: Int = seconds / 3600
        val minutes: Int = seconds % 3600 / 60
        val secs: Int = seconds % 60

        time = java.lang.String
            .format(
                Locale.getDefault(),
                "%d:%02d.%02d", hours,
                minutes, secs
            )

        timerText.text = time
        if (running) {
            seconds++
        }
        handler.postDelayed(this, 0)
    }

    private fun getPrefs()
    {
        usingInspection = sharedPrefs.getBoolean("usingInspection", false)
        inputMethod = sharedPrefs.getString("inputMethod", "Internal Timer")
    }
}