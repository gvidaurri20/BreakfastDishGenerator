package edu.utap.breakfastdishgenerator.ui

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import edu.utap.breakfastdishgenerator.api.DishPostInfo

class ViewModelDBHelper() {
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()
    private val rootCollection = "allDishes"

    /*fun fetchPhotoMeta(sortInfo: SortInfo,
                       photoMetaList: MutableLiveData<List<PhotoMeta>>) {
        dbFetchPhotoMeta(sortInfo, photoMetaList)
    }*/
    // If we want to listen for real time updates use this
    // .addSnapshotListener { querySnapshot, firebaseFirestoreException ->
    // But be careful about how listener updates live data
    // and noteListener?.remove() in onCleared
    private fun limitAndGet(query: Query,
                            dishMetaList: MutableLiveData<List<DishPostInfo>>) {
        query
            .limit(100)
            .get()
            .addOnSuccessListener { result ->
                Log.d(javaClass.simpleName, "allDishes fetch ${result!!.documents.size}")
                // NB: This is done on a background thread
                dishMetaList.postValue(result.documents.mapNotNull {
                    println("it: " + it)
                    it.toObject(DishPostInfo::class.java)
                })
            }
            .addOnFailureListener {
                Log.d(javaClass.simpleName, "allDishes fetch FAILED ", it)
            }
    }
    /////////////////////////////////////////////////////////////
    // Interact with Firestore db
    // https://firebase.google.com/docs/firestore/query-data/order-limit-data
    fun dbFetchDishMeta(dishMetaList: MutableLiveData<List<DishPostInfo>>) {
        // XXX Write me and use limitAndGet
        var query: Query
        /*if (sortInfo.sortColumn == SortColumn.TITLE) {
            if (sortInfo.ascending == true) {*/
                query = db.collection(rootCollection) //.orderBy("nameOfDish", Query.Direction.ASCENDING)
            /*}
            else {
                query = db.collection(rootCollection).orderBy("pictureTitle", Query.Direction.DESCENDING)
            }
        }
        else { // if (sortInfo.sortColumn == SortColumn.SIZE) {
            if (sortInfo.ascending == true) {
                query = db.collection(rootCollection).orderBy("byteSize", Query.Direction.ASCENDING)
            } else {
                query = db.collection(rootCollection).orderBy("byteSize", Query.Direction.DESCENDING)
            }
        }*/
        println("query so far: " + query)
        limitAndGet(query, dishMetaList)
    }

    /*// https://firebase.google.com/docs/firestore/manage-data/add-data#add_a_document
    fun createPhotoMeta(
        sortInfo: SortInfo,
        photoMeta: PhotoMeta,
        photoMetaList: MutableLiveData<List<PhotoMeta>>
    ) {
        // You can get a document id if you need it.
        photoMeta.firestoreID = db.collection(rootCollection).document().id
        // XXX Write me: add photoMeta
        db.collection(rootCollection)
            .add(photoMeta)
            .addOnSuccessListener {
                Log.d(
                    javaClass.simpleName,
                    "PhotoMeta create id: ${photoMeta.firestoreID}"
                )
                dbFetchPhotoMeta(sortInfo, photoMetaList)
            }
            .addOnFailureListener { e ->
                Log.d(javaClass.simpleName, "PhotoMeta create FAILED")
                Log.w(javaClass.simpleName, "Error ", e)
            }
    }

    // https://firebase.google.com/docs/firestore/manage-data/delete-data#delete_documents
    fun removePhotoMeta(
        sortInfo: SortInfo,
        photoMeta: PhotoMeta,
        photoMetaList: MutableLiveData<List<PhotoMeta>>
    ) {
        // XXX Write me.  Make sure you delete the correct entry
        db.collection(rootCollection)
            .document(photoMeta.firestoreID)
            .delete()
            .addOnSuccessListener {
                Log.d(
                    javaClass.simpleName,
                    "PhotoMeta delete id: ${photoMeta.firestoreID}"
                )
                dbFetchPhotoMeta(sortInfo, photoMetaList)
            }
            .addOnFailureListener { e ->
                Log.d(javaClass.simpleName, "PhotoMeta deleting FAILED \"${photoMeta.firestoreID}\"")
                Log.w(javaClass.simpleName, "Error adding document", e)
            }
    }*/
}