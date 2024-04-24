package com.rn.livros
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.rn.livros.databinding.ActivityMainBinding
import com.rn.livros.livedata.observeOnce
import com.rn.livros.model.Book
import com.rn.livros.model.MediaType
import com.rn.livros.model.Publisher
import com.rn.livros.viewmodels.BookListViewModel

class MainActivity : BaseActivity() {

    private val viewModel: BookListViewModel by lazy{
        ViewModelProvider(this).get(BookListViewModel::class.java)
    }

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabAdd.setOnClickListener{
            startActivity(Intent(this, BookFormActivity::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.book_list, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item?.itemId == R.id.menu_sign_out){
            FirebaseAuth.getInstance().signOut()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    override fun init() {
        try{

            viewModel.getBooks().observe(this, Observer { books ->
                updateList(books)
            })

        }catch (e: Exception){
            Toast.makeText(this, R.string.message_error_load_books, Toast.LENGTH_SHORT).show()
        }
    }

    private fun updateList(books: List<Book>){
        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        binding.rvBooks.adapter = BookAdapter(books){ book ->
            BookDetailsActivity.start(this, book)
        }

        attachSwipeToRecyclerView()
    }


    private fun attachSwipeToRecyclerView() {
        val swipe = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(recyclerView: RecyclerView,
                                viewHolder: RecyclerView.ViewHolder,
                                target: RecyclerView.ViewHolder): Boolean {
                return false
            }
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder,
                                  direction: Int) {
                val position = viewHolder.adapterPosition
                deleteBookFromPosition(position)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipe)
        itemTouchHelper.attachToRecyclerView(binding.rvBooks)
    }

    private fun deleteBookFromPosition(position: Int) {
        val adapter = binding.rvBooks.adapter as BookAdapter
        val book = adapter.books[position]
        viewModel.remove(book).observeOnce(Observer { success ->
            if (!success) {
                Toast.makeText(this,
                    R.string.message_error_delete_book, Toast.LENGTH_SHORT).show()
            }
        })
    }

}