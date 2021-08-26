package com.example.bombx

import android.content.Intent
import android.graphics.Color
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.example.bombx.databinding.ActivityHomeBinding

class Home : AppCompatActivity() {

    lateinit var binding : ActivityHomeBinding

    override fun onBackPressed() {
        finishAffinity()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = Color.parseColor("#FFFFFFFF")
        }

        binding.canva.isVisible = false


        binding.start.setOnClickListener(){
            binding.canva.isVisible = true
            countdown()
            binding.start.isVisible = false
            binding.viii.animate().translationY(-2400f).setDuration(1550).setStartDelay(12500)
        }


    }

    private fun countdown() {
        val countDown: CountDownTimer
        countDown = object : CountDownTimer(11000, 1000) {
            override fun onTick(millisecsToFinish: Long) {
                binding.timer.text = "${(millisecsToFinish/1000).toInt()}"
                binding.desc.text = "Solve Before the Time Runs Out"

                if(win){
                    binding.desc.text = "You Win"
                }

            }
            override fun onFinish() {

                    binding.timer.text = "10"
                    binding.canva.isVisible = false
                    binding.start.isVisible = true

                if(!win){
                    binding.desc.text = "Game Over"
                    vibrateNow()
                }

                val intent = Intent(this@Home, Home::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.up, R.anim.nothing)


            }


        }
        countDown.start()
    }

    private fun vibrateNow() {
        val v = getSystemService(VIBRATOR_SERVICE) as Vibrator

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            v.vibrate(500)
        }

    }
}