package com.example.ninhaoshijie.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.example.ninhaoshijie.logic.Cangku
import com.example.ninhaoshijie.logic.model.Place

class PlaceViewModel: ViewModel() {
  private val searchLiveData = MutableLiveData<String>()
  val placeList = ArrayList<Place>()
  val placeLiveData = Transformations.switchMap(searchLiveData) { query ->
    Cangku.searchPlaces(query)
  }
  fun searchPlaces(query: String) {
    searchLiveData.value = query
  }
  fun savePlace(place: Place) = Cangku.savePlace(place)
  fun getSavedPlace() = Cangku.getSavedPlace()
  fun isPlaceSaved() = Cangku.isPlaceSaved()

}