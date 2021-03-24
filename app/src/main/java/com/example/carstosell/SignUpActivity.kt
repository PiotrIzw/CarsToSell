package com.example.carstosell

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText


class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpButton: Button;
    private lateinit var firstNameEditText: TextInputEditText
    private lateinit var lastNameEditText: TextInputEditText
    private lateinit var usernameEditText: TextInputEditText
    private lateinit var emailEditText: TextInputEditText
    private lateinit var passwordEditText: TextInputEditText
    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        signUpButton = findViewById(R.id.loginButton)
        firstNameEditText = findViewById(R.id.firstNameEditText)
        lastNameEditText = findViewById(R.id.lastNameEditText)
        usernameEditText = findViewById(R.id.usernameEditText)
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)
        progressBar = findViewById(R.id.progress_bar)


        signUpButton.setOnClickListener {

            var firstName = firstNameEditText.text.toString()
            var lastName = lastNameEditText.text.toString()
            var username = usernameEditText.text.toString()
            var email = emailEditText.text.toString()
            var password = passwordEditText.text.toString()

            if (!firstName.equals("") && !lastName.equals("") &&
                !username.equals("") && !email.equals("") && !password.equals("")) {

                progressBar.visibility = View.VISIBLE

                val handler = Handler(Looper.getMainLooper())
                handler.post(Runnable {
                    //Starting Write and Read data with URL
                    //Creating array for parameters
                    val field = arrayOfNulls<String>(5)
                    field[0] = "firstname"
                    field[1] = "lastname"
                    field[2] = "email"
                    field[3] = "username"
                    field[4] = "password"
                    //Creating array for data
                    val data = arrayOfNulls<String>(5)
                    data[0] = firstName
                    data[1] = lastName
                    data[2] = email
                    data[3] = username
                    data[4] = password

                    val putData = PutData("http://192.168.1.15/signup.php", "POST", field, data)
                    if (putData.startPut()) {
                        if (putData.onComplete()) {

                            progressBar.visibility = View.GONE
                            val result = putData.result
                            if(result.equals("Sign Up Success")){
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                                val intent = Intent(applicationContext, LoginActivity::class.java)
                                startActivity(intent)
                                finish()
                            }
                            else{
                                print("lalal")
                                Toast.makeText(applicationContext, result, Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    //End Write and Read data with URL
                })
            }
            else{
                Toast.makeText(applicationContext, "All fields are required!", Toast.LENGTH_SHORT).show()
            }
        }



    }
}