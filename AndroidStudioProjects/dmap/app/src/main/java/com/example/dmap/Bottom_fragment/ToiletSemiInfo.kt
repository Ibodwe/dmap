package com.example.dmap.Bottom_fragment

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.dmap.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.toilet_bottom_info.view.*

class ToiletSemiInfo : BottomSheetDialogFragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.search_road.setOnClickListener {
            Toast.makeText(requireContext(), "길찾기" ,Toast.LENGTH_SHORT).show()
        }

        view.ratingBar.rating = 3.0f

        view.write_review.setOnClickListener {

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
                state =BottomSheetBehavior.STATE_EXPANDED
                peekHeight =30
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