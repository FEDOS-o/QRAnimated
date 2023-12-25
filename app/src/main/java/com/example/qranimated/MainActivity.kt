package com.example.qranimated

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.SeekBar
import android.widget.TextView
import com.example.qranimated.databinding.ActivityMainBinding
import kotlin.math.max

class MainActivity : AppCompatActivity(), SeekBar.OnSeekBarChangeListener {

    private lateinit var binding: ActivityMainBinding

    var srcBitmap: Bitmap? = null
    var dstBitmap: Bitmap? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        srcBitmap = BitmapFactory.decodeResource(this.resources, R.drawable.cover)

        dstBitmap = srcBitmap!!.copy(srcBitmap!!.config, true)

        binding.imageView.setImageBitmap(dstBitmap)

        binding.SlideSigma.setOnSeekBarChangeListener(this)

    }

    fun ButtonFlipOnClick(view: View) {
        myFlip(srcBitmap!!, srcBitmap!!)
        doBlur()
    }

    fun doBlur() {
        val sigma = max(0.1F, binding.SlideSigma.progress / 10F)
        myBlur(srcBitmap!!, dstBitmap!!, sigma)
    }


    /**
     * A native method that is implemented by the 'qranimated' native library,
     * which is packaged with this application.
     */
    external fun stringFromJNI(): String

    external fun myFlip(bitmapIn: Bitmap, bitmapOut: Bitmap)
    external fun myBlur(bitmapIn: Bitmap, bitmapOut: Bitmap, sigma : Float)

    companion object {
        // Used to load the 'qranimated' library on application startup.
        init {
            System.loadLibrary("qranimated")
        }
    }

    override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
        doBlur()
    }

    override fun onStartTrackingTouch(seekBar: SeekBar?) {}

    override fun onStopTrackingTouch(seekBar: SeekBar?) {}
}