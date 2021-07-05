package io.gigiperih.cityx.presentation.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import io.gigiperih.cityx.R
import kotlinx.android.synthetic.main.fragment_city.*

class CityFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().title = "A lot of cities"

        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync {

        }
    }
}