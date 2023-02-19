package com.frogobox.research.ui.main

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import com.frogobox.research.R
import com.frogobox.research.common.base.BaseBindActivity
import com.frogobox.research.common.delegate.PreferenceDelegates
import com.frogobox.research.databinding.ActivityMainBinding
import com.skydoves.balloon.*
import com.skydoves.balloon.overlay.BalloonOverlayAnimation
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : BaseBindActivity<ActivityMainBinding>() {

    companion object {
        private val TAG: String = MainActivity::class.java.simpleName
    }

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var preferenceDelegates: PreferenceDelegates

    override fun initBinding(): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            // Call View Model Here
            viewModel.getData()
            Log.d(TAG, "View Model : ${viewModel::class.java.simpleName}")
        }
        Log.d("SampleDelegates Output", getTagMainDelegate())
        // TODO : Add your code here

    }

    override fun initView() {
        super.initView()

        val balloon = Balloon.Builder(this)
            .setWidthRatio(1.0f)
            .setHeight(BalloonSizeSpec.WRAP)
            .setText("Edit your profile here!")
            .setTextSize(15f)
            .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR)
            .setArrowSize(10)
            .setArrowPosition(0.5f)
            .setPadding(8)
            .setCornerRadius(8f)
            .setBackgroundColorResource(R.color.teal_200)
            .setBalloonAnimation(BalloonAnimation.ELASTIC)
            .setLifecycleOwner(this)
            .setIsVisibleOverlay(true) // sets the visibility of the overlay for highlighting an anchor.
            .setOverlayColorResource(R.color.purple_200) // background color of the overlay using a color resource.
            .setOverlayPadding(0f) // sets a padding value of the overlay shape internally.
            .setOverlayPaddingColorResource(R.color.white) // sets color of the overlay padding using a color resource.
            .setBalloonOverlayAnimation(BalloonOverlayAnimation.FADE) // default is fade.
            .setDismissWhenOverlayClicked(false) // disable dismissing the balloon when the overlay is clicked.
            .build()

        binding.apply {

            tvTop.showAlignBottom(balloon)
//            tvCenter.showAlignBottom(balloon)
//            tvBottom.showAlignBottom(balloon)

        }
    }

    override fun initObserver() {
        super.initObserver()
        viewModel.apply {

        }
    }

}