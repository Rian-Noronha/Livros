package com.rn.livros.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.rn.livros.firebase.FbRepository
import com.rn.livros.model.Book

class BookDetailsViewModel : ViewModel(){
    private val repo = FbRepository()
    private val selectedBookId = MutableLiveData<String>()
    private var selectedBook = selectedBookId.switchMap { bookId ->
        repo.loadBook(bookId)
    }

    fun getBook(id:String): LiveData<Book>{
        selectedBookId.value = id
        return selectedBook
    }
}