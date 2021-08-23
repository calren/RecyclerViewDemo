package com.caren.recyclerviewdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Build a recyclerview with a list of items
        // 1, 2, 3, 4, 5, 6

        // Lookup the recyclerview in activity layout
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        // Initialize list
        val listOfItems = mutableListOf<Item>()
        // Create adapter passing in the sample user data
        val adapter = RecyclerViewAdapter(listOfItems)
        // Attach the adapter to the recyclerview to populate items
        recyclerView.adapter = adapter
        // Set layout manager to position the items
        recyclerView.layoutManager = LinearLayoutManager(this)
        // That's all!

        findViewById<FloatingActionButton>(R.id.button).setOnClickListener {
            // Add a new item to the list
            // Create the new item
            // String for the new item, is going to be the user inputted string
            // from @id/stringToInput
            val stringForItem = findViewById<EditText>(R.id.stringToInput).text.toString()
            Log.i("Caren", "stringForItem: " + stringForItem)
            // boolean for the new item, is going to be whether the checkbox is checked

            val shouldDisplayStar = findViewById<CheckBox>(R.id.checkBox).isChecked
            Log.i("Caren", "is checkbox checked: " + shouldDisplayStar)

            listOfItems.add(Item(stringForItem, shouldDisplayStar))
//            // Notify adapter
            adapter.notifyDataSetChanged()
        }

        // Remove an item based on user input
        findViewById<Button>(R.id.removeButton).setOnClickListener {
            // Grab the number the user inputted in @id/editTextNumber
            val itemToRemove = findViewById<EditText>(R.id.editTextNumber).text.toString().toInt()

            // Remove that item from the list: listOfItems
            listOfItems.removeAt(itemToRemove - 1)

            // Notify adapter
            adapter.notifyDataSetChanged()
        }
    }
}