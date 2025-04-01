package br.edu.ifsp.scl.sdm.photos.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import br.edu.ifsp.scl.sdm.photos.model.PhotosListItem


class PhotoAdapter(
    private val activityContext: Context,
    private val photosListItemMutableList: MutableList<PhotosListItem>
) : ArrayAdapter<PhotosListItem>(
    activityContext,
    android.R.layout.simple_list_item_1,
    photosListItemMutableList
) {

    private data class PhotosHolder(val titleTv: TextView)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val photosView = convertView ?: LayoutInflater.from(activityContext)
            .inflate(android.R.layout.simple_list_item_1, parent, false).apply {
                tag = PhotosHolder(findViewById(android.R.id.text1))
            }
        (photosView.tag as PhotosHolder).titleTv.text = photosListItemMutableList[position].title
        return photosView
    }
}

