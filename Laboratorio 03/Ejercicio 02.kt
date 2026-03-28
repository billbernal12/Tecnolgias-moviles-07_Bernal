///. Adicione a su aplicación una vista progressBar para el llamado a cada actividad con un mensaje de “carga completa"

//activity_main.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <!-- ProgressBar que aparecerá durante la carga -->
    <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:visibility="gone" />

    <Button
        android:id="@+id/btnGoToSecondActivity"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="48dp"
        android:layout_marginEnd="32dp"
        android:text="Ir a Segunda Actividad"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />

    <Button
        android:id="@+id/btnGoToCurrencyConversion"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="24dp"
        android:layout_marginEnd="32dp"
        android:text="Ir a Conversion de Monedas"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/btnGoToSecondActivity" />

    <Button
        android:id="@+id/btnGoToDrawer"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="32dp"
        android:layout_marginTop="24dp"
        android:layout_marginEnd="32dp"
        android:text="Ir al Navigator Drawer"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/btnGoToCurrencyConversion" />

</androidx.constraintlayout.widget.ConstraintLayout>

  /// MainActivity.ky

  package com.example.navegaciondrawerapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializamos el ProgressBar
        progressBar = findViewById(R.id.progressBar)

        // Botones
        val btnGoToSecondActivity: Button = findViewById(R.id.btnGoToSecondActivity)
        val btnGoToCurrencyConversion: Button = findViewById(R.id.btnGoToCurrencyConversion)
        val btnGoToDrawer: Button = findViewById(R.id.btnGoToDrawer)

        btnGoToSecondActivity.setOnClickListener {
            showProgressBar()
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        btnGoToCurrencyConversion.setOnClickListener {
            showProgressBar()
            val intent = Intent(this, CurrencyConversionActivity::class.java)
            startActivity(intent)
        }

        btnGoToDrawer.setOnClickListener {
            showProgressBar()
            val intent = Intent(this, DrawerActivity::class.java)
            startActivity(intent)
        }
    }

    private fun showProgressBar() {
        progressBar.visibility = ProgressBar.VISIBLE

        Handler().postDelayed({
            progressBar.visibility = ProgressBar.GONE
            Toast.makeText(this, "Carga Completa", Toast.LENGTH_SHORT).show()
        }, 2000)  // 2 segundos de espera 
    }
}

///CUrrencyConversionActivity.kt
package com.example.navegaciondrawerapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class CurrencyConversionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_conversion)

        val edtAmount: EditText = findViewById(R.id.edtAmount)
        val btnConvert: Button = findViewById(R.id.btnConvert)
        val tvResult: TextView = findViewById(R.id.tvResult)

        btnConvert.setOnClickListener {
            val amountText = edtAmount.text.toString().trim()

            if (amountText.isEmpty()) {
                tvResult.text = "Ingrese un monto"
                return@setOnClickListener
            }

            val amount = amountText.toDoubleOrNull()

            if (amount == null) {
                tvResult.text = "Ingrese un numero valido"
                return@setOnClickListener
            }

            val exchangeRate = 3.80
            val result = amount * exchangeRate

            tvResult.text = "Monto en soles: %.2f".format(result)
        }
    }
}

/// activity_currency_conversion.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/currency_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="24dp"
    tools:context=".CurrencyConversionActivity">

    <!-- ProgressBar que aparecerá durante la carga -->
    <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:visibility="gone"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintEnd_toEndOf="parent" />

    <TextView
        android:id="@+id/tvTitleCurrency"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="@string/conversion_moneda"
    android:textSize="22sp"
    android:textStyle="bold"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toTopOf="parent" />

    <EditText
        android:id="@+id/edtAmount"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginTop="32dp"
        android:ems="10"
        android:hint="@string/ingresar_monto"
        android:inputType="numberDecimal"
        android:autofillHints="price"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@id/tvTitleCurrency" />

    <Button
        android:id="@+id/btnConvert"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="@string/convertir_a_soles"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@id/edtAmount" />

    <TextView
        android:id="@+id/tvResult"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:text="@string/resultado"
    android:textSize="18sp"
    android:textStyle="bold"
    app:layout_constraintEnd_toEndOf="parent"
    app:layout_constraintStart_toStartOf="parent"
    app:layout_constraintTop_toBottomOf="@id/btnConvert" />

</androidx.constraintlayout.widget.ConstraintLayout>


  ///SecondActivity.kt
  package com.example.navegaciondrawerapp

import android.os.Bundle
import android.os.Handler
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        progressBar = findViewById(R.id.progressBar)
        showProgressBar()
    }

    private fun showProgressBar() {
        progressBar.visibility = ProgressBar.VISIBLE

        Handler().postDelayed({
            progressBar.visibility = ProgressBar.GONE
            Toast.makeText(this, "Carga Completa", Toast.LENGTH_SHORT).show()
        }, 2000)  // 2 segundos de espera (simulación de carga)
    }
}

///activity_second.xml

<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/second_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SecondActivity">

    <!-- ProgressBar que aparecerá durante la carga -->
    <ProgressBar
        android:id="@+id/progressBar"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_centerInParent="true"
        android:visibility="gone" />

    <TextView
        android:id="@+id/tvSecond"
        android:layout_width="0dp"
        android:layout_height="wrap_content"
        android:layout_marginStart="24dp"
        android:layout_marginEnd="24dp"
        android:background="#FFEB3B"
        android:gravity="center"
        android:padding="12dp"
        android:text="Esta es la Segunda Actividad"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent" />
</androidx.constraintlayout.widget.ConstraintLayout>

  ///strings.xml

<resources>
    <string name="app_name">NavegacionDrawerApp</string>
    <string name="conversion_moneda">Conversión de Monedas</string>
    <string name="ingresar_monto">Ingrese monto en dolares</string>
    <string name="convertir_a_soles">Convertir a soles</string>
    <string name="resultado">Resultado</string>
</resources>

  --------Las demas partes del codigo no fueron modificadas con ProgressBar, continuan iguales------
