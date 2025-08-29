package com.hts.vegetabiancalendar.view_model

import android.content.Context
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hts.vegetabiancalendar.model.ModelChapterDhammapada
import com.hts.vegetabiancalendar.model.ModelDhammapada
import com.hts.vegetabiancalendar.model.ModelVerseDhammapada
import com.hts.vegetabiancalendar.util.ConvertUtil
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import java.time.LocalTime

val TAG = "MyViewModelTAG"
@RequiresApi(Build.VERSION_CODES.O)
class MyViewModel(private val context: Context) : ViewModel() {

    private val _currentDayIndex = mutableStateOf(LocalDateTime.now())
    private val _currentVerseIndex = mutableIntStateOf(0)
    private var _dhammapadaData = mutableStateOf<ModelDhammapada?>(null)

    init {
        getDhammapadaData()
    }
    val currentDay: LocalDateTime get() = _currentDayIndex.value

    val currentVerseIndex: Int
        get() = _currentVerseIndex.intValue
    val dhammapadaData: ModelDhammapada?
        get() = _dhammapadaData.value
    val numberChapterDhammapada: Int?
        get() = dhammapadaData?.chapter?.size
    val numberVerseDhammapada: Int?
        get() = dhammapadaData?.chapter?.sumOf { it.verses.size }

    fun getCurrentVerseValue(index:Int): ModelVerseDhammapada?{
         return dhammapadaData?.getVerseByIndex(index)
    }
    fun getChapterByVerseIndex(index:Int): ModelChapterDhammapada?{
        return dhammapadaData?.getChapterByVerseIndex(index)
    }

    fun getDhammapadaData() {
        viewModelScope.launch {
            val data = ConvertUtil().parseDhammapadaJsonToModel(context)!!
            Log.d(TAG, "getDhammapadaData: $data")// data is no null
            _dhammapadaData.value = data // error here , why
        }
    }
}

//package com.hts.vegetabiancalendar.view_model
//
//import android.content.Context
//import android.util.Log
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.hts.vegetabiancalendar.model.ModelDhammapada
//import com.hts.vegetabiancalendar.util.ConvertUtil
//import kotlinx.coroutines.launch
//
//val TAG = "MyViewModelTAG"
//
//class MyViewModel(private val context: Context) : ViewModel() {
//
//    private val _dhammapadaData = mutableStateOf<ModelDhammapada?>(null)
//    private val _currentVerseIndex = mutableIntStateOf(0)
//
//    val dhammapadaData: ModelDhammapada? get() = dhammapadaDataState.value
//    val dhammapadaDataState: MutableState<ModelDhammapada?> get() = _dhammapadaData
//
//
//    val currentVerseIndex: Int get() = _currentVerseIndex.intValue
//
//    val numberChapterDhammapada: Int?
//        get() = dhammapadaData?.chapter?.size
//
//    val numberVerseDhammapada: Int?
//        get() = dhammapadaData?.chapter?.sumOf { it.verses.size }
//
//    init {
//        getDhammapadaData()
//    }
//
//    fun getDhammapadaData() {
//        viewModelScope.launch {
//            val data = ConvertUtil().parseDhammapadaJsonToModel(context)
//            Log.d("MyViewModelTAG", "Loaded data: $data")
//            _dhammapadaData.value = data
//        }
//    }
//}
