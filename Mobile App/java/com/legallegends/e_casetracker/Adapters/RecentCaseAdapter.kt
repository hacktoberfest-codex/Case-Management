package com.legallegends.e_casetracker.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.legallegends.e_casetracker.R
import com.legallegends.e_casetracker.databinding.RecentCasesItemLayoutBinding

class RecentCaseAdapter(
    private val items: List<String>,
    private val state: List<String>,
    private val context: Context,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<RecentCaseAdapter.RecentViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(position: Int)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecentViewHolder {
        return RecentViewHolder(RecentCasesItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: RecentViewHolder, position: Int) {
        val item = items[position]
        val caseStatus = state[position]
        holder.bind(item, caseStatus)

        holder.itemView.setOnClickListener {
            onItemClickListener.onItemClick(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class RecentViewHolder(private val recentBinding: RecentCasesItemLayoutBinding) : RecyclerView.ViewHolder(recentBinding.root) {

        fun bind(item: String, caseStatus: String) {
            recentBinding.textViewCaseNumber.text = item

            // Set background color based on caseStatus
            val backgroundColorRes = when (caseStatus) {
                "Recent" -> R.color.RecentCase
                "Pending" -> R.color.PendingCase
                else -> R.color.ActiveCase
            }

            val backgroundColor = ContextCompat.getColor(recentBinding.root.context, backgroundColorRes)
            recentBinding.CaseStatus.setCardBackgroundColor(backgroundColor)
        }
    }
}
