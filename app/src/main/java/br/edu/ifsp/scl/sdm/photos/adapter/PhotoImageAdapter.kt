package br.edu.ifsp.scl.sdm.photos.adapter

import android.content.Context
import android.graphics.Bitmap
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.scl.sdm.photos.databinding.TilePhotosBinding


class PhotoImageAdapter(val activityContext: Context, val photoImageList: MutableList<Bitmap>) :
    RecyclerView.Adapter<PhotoImageAdapter.PhotoImageViewHolder>() {
    inner class PhotoImageViewHolder(tpib: TilePhotosBinding) :
        RecyclerView.ViewHolder(tpib.photosIv) {
        val productIv: ImageView = tpib.photosIv
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoImageViewHolder =
        PhotoImageViewHolder(
            TilePhotosBinding.inflate(
                LayoutInflater.from(activityContext),
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: PhotoImageViewHolder, position: Int) =
        holder.productIv.setImageBitmap(photoImageList[position])

    override fun getItemCount(): Int = photoImageList.size
}

