//// activity_main.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

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

  ///MainActivity.kt

  package com.example.navegaciondrawerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnGoToSecondActivity: Button = findViewById(R.id.btnGoToSecondActivity)
        btnGoToSecondActivity.setOnClickListener {
            // Navegar a la Segunda Actividad
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        val btnGoToCurrencyConversion: Button = findViewById(R.id.btnGoToCurrencyConversion)
        btnGoToCurrencyConversion.setOnClickListener {
            // Navegar a la Actividad de Conversión de Monedas
            val intent = Intent(this, CurrencyConversionActivity::class.java)
            startActivity(intent)
        }

        val btnGoToDrawer: Button = findViewById(R.id.btnGoToDrawer)
        btnGoToDrawer.setOnClickListener {
            // Navegar al Drawer Layout
            val intent = Intent(this, DrawerActivity::class.java)
            startActivity(intent)
        }
    }
}


///CurrencyConversionActivity.ky

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

///activity_currency_conversion.xml
<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/currency_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:padding="24dp"
    tools:context=".CurrencyConversionActivity">

    <TextView
        android:id="@+id/tvTitleCurrency"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:text="Conversion de Monedas"
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
        android:hint="Ingrese monto en dolares"
        android:inputType="numberDecimal"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/tvTitleCurrency" />

    <Button
        android:id="@+id/btnConvert"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="20dp"
        android:text="Convertir a soles"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/edtAmount" />

    <TextView
        android:id="@+id/tvResult"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_marginTop="24dp"
        android:text="Resultado"
        android:textSize="18sp"
        android:textStyle="bold"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toBottomOf="@id/btnConvert" />

</androidx.constraintlayout.widget.ConstraintLayout>

///SecondActivity
  package com.example.navegaciondrawerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }
}

///activity_second.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.constraintlayout.widget.ConstraintLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/second_main"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".SecondActivity">

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

  ///AndroidManifest.xml

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:tools="http://schemas.android.com/tools">

    <application
        android:allowBackup="true"
        android:dataExtractionRules="@xml/data_extraction_rules"
        android:fullBackupContent="@xml/backup_rules"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/Theme.NavegacionDrawerApp"
        tools:targetApi="31">

        <activity
            android:name=".DrawerActivity"
            android:exported="false" />

        <activity
            android:name=".CurrencyConversionActivity"
            android:exported="false" />

        <activity
            android:name=".SecondActivity"
            android:exported="false" />

        <activity
            android:name=".MainActivity"
            android:exported="true">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />
                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>

    </application>

</manifest>


///DrawerActivity.kt

  package com.example.navegaciondrawerapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

class DrawerActivity : AppCompatActivity() {

    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawer)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val openDrawerButton: Button = findViewById(R.id.openDrawerButton)

        openDrawerButton.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START)
        }

        navView.setNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_main -> {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }

                R.id.item_currency_conversion -> {
                    val intent = Intent(this, CurrencyConversionActivity::class.java)
                    startActivity(intent)
                }
            }

            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }
    }
}

///activity_drawen.xml

<?xml version="1.0" encoding="utf-8"?>
<androidx.drawerlayout.widget.DrawerLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:id="@+id/drawer_layout"
    android:layout_width="match_parent"
    android:layout_height="match_parent">

    <RelativeLayout
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <Button
            android:id="@+id/openDrawerButton"
            android:layout_width="wrap_content"
            android:layout_height="wrap_content"
            android:layout_centerInParent="true"
            android:text="Abrir Menu" />
    </RelativeLayout>

    <com.google.android.material.navigation.NavigationView
        android:id="@+id/nav_view"
        android:layout_width="280dp"
        android:layout_height="match_parent"
        android:layout_gravity="start"
        android:background="@android:color/white"
        app:menu="@menu/drawer_menu" />

</androidx.drawerlayout.widget.DrawerLayout>

  ///drawer_menu.xml
  <?xml version="1.0" encoding="utf-8"?>
<menu xmlns:android="http://schemas.android.com/apk/res/android">

    <item
        android:id="@+id/item_main"
        android:title="Inicio" />

    <item
        android:id="@+id/item_currency_conversion"
        android:title="Conversion de monedas" />

</menu>

  ///strings.xml

  <resources>
    <string name="app_name">NavegacionDrawerApp</string>
</resources>

  
