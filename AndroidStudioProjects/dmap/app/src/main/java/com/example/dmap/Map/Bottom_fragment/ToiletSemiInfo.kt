package com.example.dmap.Map.Bottom_fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dmap.Data.source.KakaoMapRepository
import com.example.dmap.Map.MainActivityViewModel
import com.example.dmap.Map.indivisual_review.IndividualItem
import com.example.dmap.Map.indivisual_review.IndividualReviewAdapter
import com.example.dmap.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.toilet_bottom_info.view.*

class ToiletSemiInfo : BottomSheetDialogFragment() {


    val viewModel : MainActivityViewModel by lazy {
        MainActivityViewModel(KakaoMapRepository())
    }

    val individualReviewAdapter by lazy {
        IndividualReviewAdapter()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.search_road.setOnClickListener {
           // viewModel.getRoadDataByFoot("toilet_1","37.5777969" ,"127.0820231")
        }

        view.ratingBar.rating = 3.0f

        view.write_review.setOnClickListener {

        }

        view.IndividualRecyclerView.adapter = individualReviewAdapter

        individualReviewAdapter.addItem(IndividualItem())
        individualReviewAdapter.addItem(IndividualItem())
        individualReviewAdapter.addItem(IndividualItem())
        individualReviewAdapter.addItem(IndividualItem())
        individualReviewAdapter.addItem(IndividualItem())



        view.IndividualRecyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL , false)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.toilet_bottom_info ,container,false)

    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        dialog.setOnShowListener{

            val bottomSheet =dialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
            BottomSheetBehavior.from(bottomSheet).apply {
                halfExpandedRatio = 0.25f
                state =BottomSheetBehavior.STATE_HALF_EXPANDED
                addBottomSheetCallback(bottomSheetCallback)
            }
        }

        return dialog

    }

    private val bottomSheetCallback= object : BottomSheetBehavior.BottomSheetCallback() {
        override fun onStateChanged(bottomSheet: View, newState: Int) {

            when (newState) {
                BottomSheetBehavior.STATE_COLLAPSED -> {
                    if (!isRemoving) dismiss()
                }

                else -> {

                }
            }
        }

        override fun onSlide(bottomSheet: View, slideOffset: Float) {
        }
    }

}