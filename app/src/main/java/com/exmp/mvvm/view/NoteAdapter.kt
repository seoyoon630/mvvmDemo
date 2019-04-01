package com.exmp.mvvm.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.exmp.mvvm.NoteID
import com.exmp.mvvm.R
import com.exmp.mvvm.contract.NoteContract
import com.exmp.mvvm.databinding.NoteItemBinding
import com.exmp.mvvm.model.NoteService
import com.exmp.mvvm.model.Notes
import com.exmp.mvvm.viewmodel.NoteItemViewModel

class NoteAdapter(private val mContext: Context) : RecyclerView.Adapter<Holder>() {
    var items = Notes.getNoteList()

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val item = items[position]
        holder.bind(item)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val bb =
            DataBindingUtil.inflate<NoteItemBinding>(LayoutInflater.from(mContext), R.layout.note_item, parent, false)
        bb.model = NoteItemViewModel(mContext as NoteContract)
        return Holder(bb.root, bb.model)
    }

    fun deleteItem(seqNo: Int) {
        for (item in items) {
            if (seqNo == item.seqNo) {
                val idx = items.indexOf(item)
                items.remove(item)
                Notes.updatePreference()
//                Notes.deleteNote(item)
                notifyItemRemoved(idx)
                break
            }
        }
    }

    fun updateItems() {
        items = Notes.getNoteList()
        notifyDataSetChanged()
    }
}

class Holder(itemView: View, val model: NoteItemViewModel?) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: NoteService.Data.Note) {
        model?.bindItem(item)
    }

}