package com.rn.livros

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.rn.livros.databinding.ActivityBookDetailsBinding
import com.rn.livros.model.Book
import org.parceler.Parcels

class BookDetailsActivity : BaseActivity() {

    private val binding: ActivityBookDetailsBinding by lazy {
        DataBindingUtil.setContentView<ActivityBookDetailsBinding>(
            this, R.layout.activity_book_details
        )
    }

    override fun init() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val book =
            Parcels.unwrap<Book>(intent.getParcelableExtra(EXTRA_BOOK))
        if(book != null){
            binding.book = book
        }
    }

    companion object{
        private const val EXTRA_BOOK = "book"

        fun start(context: Context, book: Book){
            context.startActivity(
                Intent(context,
                    BookDetailsActivity::class.java).apply {
                    putExtra(EXTRA_BOOK, Parcels.wrap(book))
                }
            )
        }

    }


}