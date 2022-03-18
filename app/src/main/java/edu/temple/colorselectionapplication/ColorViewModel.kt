package edu.temple.colorselectionapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ColorViewModel : ViewModel() {
    val selectedColor : MutableLiveData<String> by lazy {
        MutableLiveData<String>()
    }

    fun setSelectedColor(color: String) {
        selectedColor.value = color
    }

    fun getSelectedColor() : LiveData<String> {
        return selectedColor
    }
}