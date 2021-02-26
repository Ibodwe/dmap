package com.example.dmap.ToiletReviewDialog

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.dmap.Map.CustomLocationData.DmapLocationData
import com.example.dmap.Map.MainActivityViewModel
import com.example.dmap.Map.indivisual_review.IndividualItem
import com.example.dmap.Map.indivisual_review.IndividualReviewAdapter
import com.example.dmap.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.toilet_bottom_info.view.*

class ToiletSemiInfo(val data : DmapLocationData) : BottomSheetDialogFragment() {



    val viewModel : MainActivityViewModel by lazy {
        MainActivityViewModel()
    }

    val individualReviewAdapter by lazy {
        IndividualReviewAdapter()
    }

    val toiletSemiInfoViewModel : ToiletSemiInfoViewModel by lazy{
        ToiletSemiInfoViewModel(individualReviewAdapter)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.search_road.setOnClickListener {

        }

        setViewData(view)

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


    private val recyclerViewOnScrollListener = object : RecyclerView.OnScrollListener(){
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val visibleItem = recyclerView.childCount
            val totalItemCount = individualReviewAdapter.itemCount
            val firstVisibleItem = (recyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()

            if( (firstVisibleItem + visibleItem) >= totalItemCount-3){
                //load More Data

            }

        }
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

    fun setViewData(view : View){
        view.toilet_name.setText(data.data.name)
        val addressData = data.data
        val address = "${addressData.city_name} ${addressData.goo_name} ${addressData.street_num_main} ${addressData.street_num_sub}"

        view.toilet_address.setText(address)


    }

}