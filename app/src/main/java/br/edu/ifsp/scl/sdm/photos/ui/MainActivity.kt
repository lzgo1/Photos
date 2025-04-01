package br.edu.ifsp.scl.sdm.photos.ui

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import br.edu.ifsp.scl.sdm.photos.R
import br.edu.ifsp.scl.sdm.photos.adapter.PhotoAdapter
import br.edu.ifsp.scl.sdm.photos.adapter.PhotoImageAdapter
import br.edu.ifsp.scl.sdm.photos.databinding.ActivityMainBinding
import br.edu.ifsp.scl.sdm.photos.model.JSONPlaceholder
import br.edu.ifsp.scl.sdm.photos.model.PhotosListItem
import com.android.volley.toolbox.ImageRequest

class MainActivity : AppCompatActivity() {
    private val amb: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val photosList: MutableList<PhotosListItem> = mutableListOf()
    private val photoAdapter: PhotoAdapter by lazy {
        PhotoAdapter(this, photosList)
    }

    private val photoImageList: MutableList<Bitmap> = mutableListOf()
    private val photoImageAdapter: PhotoImageAdapter by lazy {
        PhotoImageAdapter(this, photoImageList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(amb.root)
        setSupportActionBar(amb.mainTb.apply {
            title = getString(R.string.app_name)
        })

        amb.photosSp.apply {
            adapter = photoAdapter
            onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    p0: AdapterView<*>?,
                    p1: View?,
                    position: Int,
                    p3: Long
                ) {
                    val size = photoImageList.size
                    photoImageList.clear()
                    photoImageAdapter.notifyItemRangeRemoved(0, size)
                    retrievePhotosImages(photosList[position].url, amb.imagePhoto)
                    retrievePhotosImages(photosList[position].thumbnailUrl, amb.imageThumbnail)
                }

                override fun onNothingSelected(p0: AdapterView<*>?) {
                }
            }
        }
        retrievePhotos()
    }

    private fun retrievePhotos() = JSONPlaceholder.PhotoListRequest(
        { photoList ->
            photoList.also {
                photoAdapter.addAll(it)
            }
        },
        {
            Toast.makeText(this, "Falha ao obter as fotos da API !", Toast.LENGTH_SHORT).show()
        }).also {
        JSONPlaceholder.getInstance(this).addToRequestQueue(it)
    }

    private fun retrievePhotosImages(imageUrl: String, view: ImageView) =
        ImageRequest(imageUrl,
            { response ->
                view.setImageBitmap(response)
            }, 0, 0, ImageView.ScaleType.CENTER, Bitmap.Config.ARGB_8888, {
                Toast.makeText(this, "Falha ao carregar a imagem da API!", Toast.LENGTH_SHORT)
                    .show()
            }).also {
            JSONPlaceholder.getInstance(this).addToRequestQueue(it)
        }
}