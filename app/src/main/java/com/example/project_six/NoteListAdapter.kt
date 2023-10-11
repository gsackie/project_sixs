package com.example.project_six

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.project_six.Note
import com.example.project_six.databinding.ItemsNoteBinding

class NoteListAdapter(private val onDeleteClick: (Note) -> Unit) : ListAdapter<Note, NoteListAdapter.NoteViewHolder>(NoteDiffCallback()) {

    inner class NoteViewHolder(private val binding: ItemsNoteBinding) : RecyclerView.ViewHolder(binding.root) {
        val titleTextView: TextView = binding.noteTitleTextView
        val deleteButton: ImageButton = binding.deleteNoteButton

        fun bind(note: Note) {
            binding.note = note
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemsNoteBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val currentNote = getItem(position)
        holder.bind(currentNote)

        holder.deleteButton.setOnClickListener {
            val noteToDelete = getItem(position)

            val dialogFragment = DeleteDialogFragment()
            dialogFragment.setOnDeleteClickListener {
                viewModel.deleteNote(noteToDelete)
            }

            dialogFragment.show(fragmentManager, "DeleteDialog")
        }

        holder.itemView.setOnClickListener {
            val action = NoteListFragmentDirections.actionNoteListFragmentToNoteFragment(id = currentNote.id)
            findNavController().navigate(action)
        }
    }

    class NoteDiffCallback : DiffUtil.ItemCallback<Note>() {
        override fun areItemsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Note, newItem: Note): Boolean {
            return oldItem.title == newItem.title && oldItem.description == newItem.description
        }
    }
}
