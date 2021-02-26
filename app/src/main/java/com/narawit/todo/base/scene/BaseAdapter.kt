package com.narawit.todo.base.scene

import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import java.util.*

@Suppress("UNCHECKED_CAST")
abstract class BaseAdapter<M, B : ViewBinding?> :
    RecyclerView.Adapter<BaseAdapter.ViewHolder?>(), Filterable {

    private var data: MutableList<M>? = null
    private var olddata: MutableList<M>? = null
    private var recyclerViewItemClick: ((M, Boolean) -> Unit)? = null

    init {
        data = mutableListOf()
        olddata = mutableListOf()
    }

    open fun onItemClick(model: M, isSelected: Boolean) {
        recyclerViewItemClick?.let { it(model, isSelected) }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return createView(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        onBind(holder.binding as B, data?.get(position)!!, holder.itemView)

        var isSelected = false
        holder.itemView.setOnClickListener { _ ->
            isSelected = !isSelected
            onItemClick(data!![position], isSelected)
        }
    }

    fun clear(deepClean: Boolean) {
        data = mutableListOf()
        if (deepClean) {
            olddata = mutableListOf()
        }
    }

    override fun getItemCount(): Int {
        if (data == null) return 0
        return data!!.size
    }

    fun setRecyclerViewItemClick(recyclerViewItemClick: (M, Boolean) -> Unit): BaseAdapter<M, B> {
        this.recyclerViewItemClick = recyclerViewItemClick
        return this
    }

    abstract fun onBind(binding: B, model: M, itemView: View)

    abstract fun addFilter(
        charString: String,
        data: MutableList<M>?,
        resultList: MutableList<M>
    ): MutableList<M>

    abstract fun createView(parent: ViewGroup): ViewHolder

    fun addAllList(addAll: Collection<M>?) {
        addAll?.let {
            data?.addAll(it)
            olddata?.addAll(it)
        }
        notifyDataSetChanged()
    }

    fun removeOnly(model: M) {
        data!!.remove(model)
        olddata!!.remove(model)
        notifyDataSetChanged()
    }

    fun removeOnly(pos: Int) {
        val model = data!![pos]
        data!!.remove(model)
        olddata!!.remove(model)
        notifyDataSetChanged()
    }

    @Suppress("UNCHECKED_CAST")
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charString: CharSequence?): FilterResults {
                data = if (charString.toString().isEmpty()) {
                    olddata
                } else {
                    val resultList = mutableListOf<M>()
                    val filterPattern =
                        charString.toString().toLowerCase(Locale.getDefault()).trim()
                    resultList.addAll(addFilter(filterPattern, data, resultList))
                    resultList
                }
                val filterListResults = FilterResults()
                filterListResults.values = data
                return filterListResults
            }

            override fun publishResults(charSquence: CharSequence?, filterResults: FilterResults?) {
                data = filterResults!!.values as MutableList<M>?
                notifyDataSetChanged()
            }
        }
    }

    open class ViewHolder(itemView: ViewBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ViewBinding? = itemView

    }
}
