package io.gigiperih.cityx.presentation.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.model.LatLng
import io.gigiperih.cityx.R
import kotlinx.android.synthetic.main.fragment_city.*


class CityFragment : Fragment() {
    // default is Jakarta :)
    private var lat: Double = -6.121435
    private var lon: Double = 106.774124

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_city, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let { bundle ->
            lat = bundle.getDouble("lat")
            lon = bundle.getDouble("lon")
        }

        setupMapView(savedInstanceState)
    }

    private fun setupMapView(savedInstanceState: Bundle?) {
        map_view.onCreate(savedInstanceState)
        map_view.onResume()
        map_view.getMapAsync { googleMap ->
            googleMap.moveCamera(
                CameraUpdateFactory.newLatLngZoom(
                    LatLng(lat, lon),
                    12f
                )
            )

        }
    }
}