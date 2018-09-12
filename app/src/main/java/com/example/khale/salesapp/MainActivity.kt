package com.example.khale.salesapp

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_reg.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        login_signup.setOnClickListener {
            var intent: Intent = Intent(applicationContext, RegAct::class.java)
            startActivity(intent) }
        login_btn.setOnClickListener{
            var url = "https://30ffdb19.ngrok.io/SalesWeb/login.php?mobile=" +
                    login_mobile.text.toString() + "&password=" + login_password.text.toString()
            var rq: RequestQueue = Volley.newRequestQueue(this)
            var sr = StringRequest(Request.Method.POST, url, Response.Listener { response ->
                if (response.equals("0"))
                    Toast.makeText(this, "Login Fail", Toast.LENGTH_LONG).show()
                else{
                    UserInfo.mobile=login_mobile.text.toString()
                    var intent=Intent(this,HomeAct::class.java)
                    startActivity(intent)
                }
            },
                    Response.ErrorListener { error ->
                        Toast.makeText(this, error.message, Toast.LENGTH_LONG).show()

                    })

            rq.add(sr)
        }
    }
}
