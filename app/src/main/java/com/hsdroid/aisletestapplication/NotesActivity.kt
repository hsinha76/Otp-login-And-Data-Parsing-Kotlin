package com.hsdroid.aisletestapplication

import NotesResponse
import Profiles
import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.hsdroid.aisletestapplication.adapter.NotesAdapter
import com.hsdroid.aisletestapplication.databinding.ActivityNotesBinding
import com.hsdroid.aisletestapplication.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NotesActivity : AppCompatActivity() {
    lateinit var dataBinding: ActivityNotesBinding
    lateinit var adapter: NotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityNotesBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        dataBinding.dataAvailable = false

        val intent: Intent = intent
        val token: String? = intent.getStringExtra("token")
        if (token != null) {

            adapter = NotesAdapter()
            adapter.context = this@NotesActivity
            adapter.data = ArrayList<Profiles>()

            dataBinding.recyclerView.setHasFixedSize(true)
            dataBinding.recyclerView.adapter = adapter

            lifecycleScope.launchWhenCreated {
                try {
                    val response = APIClient.getInstance().getNotes(token)
                    response.enqueue((object : Callback<NotesResponse> {
                        @SuppressLint("NotifyDataSetChanged")
                        override fun onResponse(
                            call: Call<NotesResponse>,
                            response: Response<NotesResponse>
                        ) {
                            val res: NotesResponse? = response.body()
                            if (res != null) {

                                val list: List<Profiles> = res.invites.profiles;
                                val genInfo = list[0].genInfo
                                Glide.with(this@NotesActivity)
                                    .load(list[0].photos[0].photo)
                                    .into(dataBinding.imageView)

                                dataBinding.name.setText(genInfo.first_name + ", " + genInfo.age)
                                adapter.data = res.likes.profiles
                                adapter.notifyDataSetChanged()
                                dataBinding.dataAvailable = true
                            }

                        }


                        override fun onFailure(call: Call<NotesResponse>, t: Throwable) {

                            Toast.makeText(
                                this@NotesActivity,
                                t.message,
                                Toast.LENGTH_LONG
                            ).show()
                        }

                    }))

                } catch (Ex: Exception) {
                    Ex.localizedMessage?.let { Log.e("Error", it) }

                }
            }

        }

    }
}