package com.rn.livros
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.auth.FirebaseAuth
import com.rn.livros.databinding.ActivityMainBinding
import com.rn.livros.model.Book
import com.rn.livros.model.MediaType
import com.rn.livros.model.Publisher

class MainActivity : BaseActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun init() {
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val books = listOf(
            Book().apply {
                id = "1"
                title = "Dominando o Android com Kotlin"
                author = "Rian Noronha"
                coverUrl =
                    "https://s3.novatec.com.br/capas-ampliadas/capa-ampliada-9788575224632.jpg"
                pages = 954
                year = 2018
                publisher = Publisher("1", "Novatec")
                available = true
                mediaType = MediaType.PAPER
                rating = 5.0f
            }
        )

        binding.rvBooks.layoutManager = LinearLayoutManager(this)
        binding.rvBooks.adapter = BookAdapter(books){book ->
            BookDetailsActivity.start(this, book)
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
}