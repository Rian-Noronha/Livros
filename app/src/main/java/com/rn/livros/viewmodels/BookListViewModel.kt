package com.rn.livros.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.rn.livros.firebase.FbRepository
import com.rn.livros.model.Book

class BookListViewModel : ViewModel(){

    private val repo = FbRepository()
    private var bookList: LiveData<List<Book>>? = null

    fun getBooks(): LiveData<List<Book>>{
        var list = bookList
        if(list == null){
            list = repo.loadBooks()
            bookList = list
        }

        return list
    }

    fun remove(book: Book) = repo.remove(book)

}