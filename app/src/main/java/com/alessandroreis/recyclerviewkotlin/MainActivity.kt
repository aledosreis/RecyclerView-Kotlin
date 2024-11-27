package com.alessandroreis.recyclerviewkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alessandroreis.recyclerviewkotlin.model.email
import com.alessandroreis.recyclerviewkotlin.model.fakeEmails
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.mooveit.library.Fakeit
import java.text.SimpleDateFormat
import java.util.Locale

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: EmailAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Fakeit.init()
        setContentView(R.layout.activity_main)

        adapter = EmailAdapter(fakeEmails())

        val recyclerViewMain = findViewById<RecyclerView>(R.id.recycler_view_main)
        recyclerViewMain.adapter = adapter
        recyclerViewMain.layoutManager = LinearLayoutManager(this)

        val fab = findViewById<FloatingActionButton>(R.id.fab)
        fab.setOnClickListener {
            addEmail()
            recyclerViewMain.scrollToPosition(0)
        }
    }

    private fun addEmail() {
        val sdf = SimpleDateFormat("dd/MM/yyyy", Locale("pt", "BR")).parse(
            Fakeit.dateTime().dateFormatter()
        )

        adapter.emails.add(0,
            email {
                stared = false
                unread = true
                user = Fakeit.name().firstName()
                subject = Fakeit.company().name()
                date = SimpleDateFormat("d MMM", Locale("pt", "BR")).format(sdf)
                preview = mutableListOf<String>().apply {
                    repeat(10) {
                        add(Fakeit.lorem().words())
                    }
                }.joinToString(" ")
        })

        adapter.notifyItemInserted(0)
    }
}