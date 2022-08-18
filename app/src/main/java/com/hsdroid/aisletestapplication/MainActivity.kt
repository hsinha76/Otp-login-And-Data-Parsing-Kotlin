package com.hsdroid.aisletestapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import com.hsdroid.aisletestapplication.data.ResponseLogin
import com.hsdroid.aisletestapplication.data.ResponseOtp
import com.hsdroid.aisletestapplication.databinding.ActivityMainBinding
import com.hsdroid.aisletestapplication.network.APIClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var dataBinding: ActivityMainBinding
    private lateinit var input: String
    private lateinit var otp: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(dataBinding.root)

        dataBinding.btnContinue.setOnClickListener(View.OnClickListener {
            input = dataBinding.etPhoneNo.editableText.toString().trim()
            if (input.length == 10) {
                loginPhone(input)
            } else
            //Show some error
                dataBinding.etPhoneNo.error = ""
        })

        dataBinding.tvPhoneNo.setOnClickListener(View.OnClickListener {
            dataBinding.viewPhoneNo.visibility = View.VISIBLE
            dataBinding.viewOtp.visibility = View.GONE
            dataBinding.etPhoneNo.setText(input)

            hideProgressBarPhone()
        })

        dataBinding.btnContinueOtp.setOnClickListener {
            otp = dataBinding.etOtp.editableText.toString().trim()
            if (otp.length == 4) {
                verifyOtp(otp)
            } else
            //Show some error
                dataBinding.etOtp.error = ""
        }

    }

    private fun loginPhone(input: String) {
        showProgressBarPhone()
        lifecycleScope.launchWhenCreated {
            try {
                val response = APIClient.getInstance().loginPhone("+91" + input.trim())
                response.enqueue((object : Callback<ResponseLogin> {
                    override fun onResponse(
                        call: Call<ResponseLogin>,
                        response: Response<ResponseLogin>
                    ) {

                        var res: ResponseLogin? = response.body()
                        if (res != null) {
                            if (res.status) {
                                dataBinding.tvPhoneNo.text = "+91 $input"
                                dataBinding.etOtp.requestFocus()
                                hideProgressBarPhone()
                            } else {
                                hideProgressBarPhone()
                                Toast.makeText(
                                    this@MainActivity,
                                    "Login Failed",
                                    Toast.LENGTH_LONG
                                ).show()

                            }
                        }

                    }


                    override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                        hideProgressBarPhone()
                        Toast.makeText(
                            this@MainActivity,
                            t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }))

            } catch (Ex: Exception) {
                Ex.localizedMessage?.let { Log.e("Error", it) }
                hideProgressBarPhone()
            }
        }

    }

    private fun verifyOtp(otp: String) {
        showProgressBarOtp()
        lifecycleScope.launchWhenCreated {
            try {
                val response = APIClient.getInstance().verifyOtp("+91" + input.trim(), otp)
                response.enqueue((object : Callback<ResponseOtp> {
                    override fun onResponse(
                        call: Call<ResponseOtp>,
                        response: Response<ResponseOtp>
                    ) {

                        var res: ResponseOtp? = response.body()
                        if (res != null) {
                            if (res.token != null) {

                                hideProgressBarOtp()
                                Toast.makeText(
                                    this@MainActivity,
                                    "Login Successful",
                                    Toast.LENGTH_LONG
                                ).show()

                                val intent: Intent =
                                    Intent(this@MainActivity, NotesActivity::class.java)
                                intent.putExtra("token", res.token)
                                startActivity(intent)
                                finish()

                            } else {
                                hideProgressBarOtp()
                                dataBinding.etOtp.setText("")
                                Toast.makeText(
                                    this@MainActivity,
                                    "Invalid OTP",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    }


                    override fun onFailure(call: Call<ResponseOtp>, t: Throwable) {
                        hideProgressBarOtp()
                        Toast.makeText(
                            this@MainActivity,
                            t.message,
                            Toast.LENGTH_LONG
                        ).show()
                    }

                }))

            } catch (Ex: Exception) {
                Ex.localizedMessage?.let { Log.e("Error", it) }
                hideProgressBarOtp()
            }
        }
    }

    //Progressbar handling
    private fun showProgressBarPhone() {
        dataBinding.btnContinue.visibility = View.GONE
        dataBinding.progressCircular.visibility = View.VISIBLE
    }

    private fun hideProgressBarPhone() {
        dataBinding.viewPhoneNo.visibility = View.GONE
        dataBinding.viewOtp.visibility = View.VISIBLE
    }

    private fun showProgressBarOtp() {
        dataBinding.btnContinueOtp.visibility = View.GONE
        dataBinding.progressCircularOtp.visibility = View.VISIBLE
    }

    private fun hideProgressBarOtp() {
        dataBinding.btnContinueOtp.visibility = View.VISIBLE
        dataBinding.progressCircularOtp.visibility = View.GONE
    }

}