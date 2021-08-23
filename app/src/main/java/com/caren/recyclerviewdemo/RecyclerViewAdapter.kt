package com.caren.recyclerviewdemo

import android.graphics.Paint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

// 1. Define Adapter class
// 2. Implement RecyclerView Adapter
// 3. Created ViewHolder
// 4. Created stubs for our implement methods

// Bridge the layout file and our data
class RecyclerViewAdapter(private val listOfItems: MutableList<Item>) :
    RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    // Specify which layout to use for each item inside the recyclerview
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        // Inflate the custom layout
        val view = inflater.inflate(R.layout.item_layout, parent, false)
        // Return a new holder instance
        return ViewHolder(view)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = listOfItems.get(position)

        // Strike through text in an item on the list when item is clicked
        holder.itemView.setOnClickListener {
            val textView = it.findViewById<TextView>(R.id.textView)
            textView.setPaintFlags(textView.getPaintFlags() or Paint.STRIKE_THRU_TEXT_FLAG)
        }

        // Remove item from list when item is long clicked
        holder.itemView.setOnLongClickListener {
            listOfItems.removeAt(position)
            notifyDataSetChanged()
            true
        }

        holder.myTextView.setText(item.myString)

        val shouldDisplayStar = item.shouldDisplayStar

        if (shouldDisplayStar) {
            holder.star.visibility = View.VISIBLE
        } else {
            holder.star.visibility = View.INVISIBLE
        }

        holder.star.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                "Clicked on star: " + (position + 1), Toast.LENGTH_SHORT
            ).show()
            Log.i("Caren", "Clicked on star: " + (position + 1))
        }
    }

    // Tells RecyclerView how many items it needs to lay out
    override fun getItemCount(): Int {
        return listOfItems.size
    }

    // Make layout more efficient
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val myTextView: TextView =
            itemView.findViewById(R.id.textView)

        val star: ImageView =
            itemView.findViewById(R.id.star)
    }
}
