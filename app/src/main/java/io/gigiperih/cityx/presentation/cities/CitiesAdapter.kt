package io.gigiperih.cityx.presentation.cities

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import io.gigiperih.cityx.R
import io.gigiperih.cityx.data.City
import kotlinx.android.synthetic.main.item_city.view.*

class CitiesAdapter :
    RecyclerView.Adapter<CitiesAdapter.CityViewHolder>() {
    private val cities = mutableListOf<City>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CityViewHolder {
        val view =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_city, parent, false)
        return CityViewHolder(view)
    }

    override fun onBindViewHolder(holder: CityViewHolder, position: Int) {
        holder.bind(cities[position])
    }

    override fun getItemCount() = cities.size

    class CityViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        fun bind(city: City) {
            itemView.text_city.text = city.name
            itemView.text_country.text = city.country
            itemView.text_lat.text = "Lat: ${city.coord.lat}"
            itemView.text_lon.text = "Lon: ${city.coord.lat}"
        }
    }

    fun addAll(newList: List<City>) {
        cities.addAll(newList)
        notifyDataSetChanged()
    }

    fun clear() {
        cities.clear()
        notifyDataSetChanged()
    }
}