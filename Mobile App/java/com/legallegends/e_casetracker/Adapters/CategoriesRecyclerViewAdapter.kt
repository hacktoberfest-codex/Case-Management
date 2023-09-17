package com.legallegends.e_casetracker.Adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.legallegends.e_casetracker.CaseCategorieActivity

import com.legallegends.e_casetracker.R
import com.legallegends.e_casetracker.databinding.CategoriesItemsLayoutBinding

class CategoriesRecyclerViewAdapter :
    ListAdapter<CategoriesRecyclerViewModel, CategoriesRecyclerViewAdapter.ItemViewHolder>(
        DiffUtilCallback
    ) {

    private lateinit var context: Context
   private var caseNumber= listOf("101","102","103","104","105","106","107","108","109","110")
   private var state= listOf("Recent","Recent","Active","Active","Pending","Pending","Recent","Recent","Pending","Pending")


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        val binding = CategoriesItemsLayoutBinding.inflate(inflater, parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val model = getItem(position)
        holder.bind(model)

        holder.itemView.setOnClickListener {
            // Handle item click here
            val intent = Intent(context, CaseCategorieActivity::class.java)
            intent.putExtra("ITEM_TITLE", model.title)
            intent.putStringArrayListExtra("CaseNumber", ArrayList(caseNumber))
            intent.putStringArrayListExtra("state", ArrayList(state))
            context.startActivity(intent)
        }
    }

    fun setLists(caseNumber: List<String>, state: List<String>) {
        this.caseNumber = caseNumber
        this.state = state
    }

    class ItemViewHolder(private val binding: CategoriesItemsLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(model: CategoriesRecyclerViewModel) {

            Glide.with(binding.root)
                .load(model.imageId)
                .placeholder(R.drawable.logo) // Replace with your placeholder image
                .error(R.drawable.error) // Replace with your error image
                .into(binding.ImageView) // Correct the ImageView ID to match your XML
        }
    }

    object DiffUtilCallback : DiffUtil.ItemCallback<CategoriesRecyclerViewModel>() {
        override fun areItemsTheSame(
            oldItem: CategoriesRecyclerViewModel,
            newItem: CategoriesRecyclerViewModel
        ): Boolean {
            return oldItem.title == newItem.title // Use a unique identifier for your items
        }

        override fun areContentsTheSame(
            oldItem: CategoriesRecyclerViewModel,
            newItem: CategoriesRecyclerViewModel
        ): Boolean {
            return oldItem == newItem // Compare the content of your items here
        }
    }
}
