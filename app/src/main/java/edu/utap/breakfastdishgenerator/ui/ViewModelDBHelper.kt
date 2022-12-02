package edu.utap.breakfastdishgenerator.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import edu.utap.breakfastdishgenerator.api.DishPostInfo

class ViewModelDBHelper() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val rootCollection = "allDishes"

    private fun limitAndGet(query: Query,
                            dishMetaList: MutableLiveData<List<DishPostInfo>>) {
        query
            .limit(100)
            .get()
            .addOnSuccessListener { result ->
                Log.d(javaClass.simpleName, "allDishes fetch ${result!!.documents.size}")
                // NB: This is done on a background thread
                dishMetaList.postValue(result.documents.mapNotNull {
                    it.toObject(DishPostInfo::class.java)
                })
            }
            .addOnFailureListener {
                Log.d(javaClass.simpleName, "allDishes fetch FAILED ", it)
            }
    }

    fun dbFetchDishMeta(dishMetaList: MutableLiveData<List<DishPostInfo>>) {
        var query: Query
        query = db.collection(rootCollection)
        limitAndGet(query, dishMetaList)
    }
}