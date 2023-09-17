import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.legallegends.e_casetracker.R
import com.legallegends.e_casetracker.databinding.CustomfragmentItemLayoutBinding
import com.legallegends.e_casetracker.Model.CustomItems

class CustomFragmentAdapter(private val customItems: List<CustomItems>) :
    RecyclerView.Adapter<CustomFragmentAdapter.DetailsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DetailsViewHolder {
        val binding =
            CustomfragmentItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DetailsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DetailsViewHolder, position: Int) {
        val customItem = customItems[position]
        holder.bind(customItem)
    }

    override fun getItemCount(): Int {
        return customItems.size
    }

    inner class DetailsViewHolder(private val binding: CustomfragmentItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(customItem: CustomItems) {
            binding.textViewCaseNumber.text = customItem.caseNumber
            // Set other views accordingly

            // Set background color based on caseStatus
            val backgroundColorRes = when (customItem.caseStatus) {
                "Recent" -> R.color.RecentCase
                "Pending" -> R.color.PendingCase
                else -> R.color.ActiveCase
            }

            val backgroundColor =
                ContextCompat.getColor(binding.root.context, backgroundColorRes)
            binding.CaseStatus.setCardBackgroundColor(backgroundColor)
        }
    }
}
