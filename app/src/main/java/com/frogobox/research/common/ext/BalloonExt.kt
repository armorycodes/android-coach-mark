package com.frogobox.research.common.ext

import android.content.Context
import android.util.Log
import android.widget.Button
import androidx.lifecycle.LifecycleOwner
import com.frogobox.research.R
import com.frogobox.research.ui.main.MainBalloonCallback
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation

/**
 * Created by Faisal Amir on 19/02/23
 * https://github.com/amirisback
 */


fun Context.createBalloonExt(lifecycle: LifecycleOwner?, callback: MainBalloonCallback) : Balloon {
    return Balloon.Builder(this)
        .setWidth(BalloonSizeSpec.WRAP)
        .setHeight(BalloonSizeSpec.WRAP)
        .setLayout(R.layout.coach_mark)
        .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
        .setIsVisibleArrow(false) // sets the visibility of the arrow.
        .setArrowSize(0)
        .setBackgroundColorResource(android.R.color.transparent)
        .setBalloonAnimation(BalloonAnimation.OVERSHOOT)
        .setLifecycleOwner(lifecycle)
//            .setOverlayShape(BalloonOverlayCircle(radius = 36f))
        .setIsVisibleOverlay(true) // sets the visibility of the overlay for highlighting an anchor.
        .setOverlayColorResource(R.color.overlay) // background color of the overlay using a color resource.
        .setOverlayPadding(0f) // sets a padding value of the overlay shape internally.
        .setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE) // default is fade.
        .setDismissWhenOverlayClicked(false) // disable dismissing the balloon when the overlay is clicked.
        .setOnBalloonClickListener { Log.d("Balloon", "Clicked") }
        .setOnBalloonDismissListener { Log.d("Balloon", "Dismiss") }
        .setOnBalloonOutsideTouchListener { _, _ -> Log.d("Balloon", "Outside Touch") }
        .build().apply {
            val btnSkip = this.getContentView().findViewById<Button>(R.id.btn_skip)
            val btnNext = this.getContentView().findViewById<Button>(R.id.btn_next)

            btnSkip.setOnClickListener {
                this.dismiss()
                callback.onSkip()
            }

            btnNext.setOnClickListener {
                this.dismiss()
                callback.onNext()
            }
        }

}