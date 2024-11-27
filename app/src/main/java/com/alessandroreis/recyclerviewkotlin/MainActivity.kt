package com.alessandroreis.recyclerviewkotlin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alessandroreis.recyclerviewkotlin.model.fakeEmails

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerViewMain = findViewById<RecyclerView>(R.id.recycler_view_main)
        recyclerViewMain.adapter = EmailAdapter(fakeEmails())
        recyclerViewMain.layoutManager = LinearLayoutManager(this)
    }
}