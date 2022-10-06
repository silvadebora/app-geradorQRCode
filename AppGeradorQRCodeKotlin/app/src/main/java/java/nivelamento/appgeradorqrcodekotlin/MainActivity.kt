package java.nivelamento.appgeradorqrcodekotlin

import android.graphics.Bitmap
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.google.zxing.WriterException
import com.google.zxing.qrcode.QRCodeWriter
import com.google.zxing.qrcode.encoder.QRCode

class MainActivity : AppCompatActivity() {

    var editQRCode: EditText? = null
    var btnGerarQRCode: Button? = null
    var imgQRCode: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initComponentes()

        btnGerarQRCode!!.setOnClickListener {

            if (TextUtils.isEmpty(editQRCode!!.text.toString())){

                //código - validar edittext

                editQRCode!!.error = "Campo Obrigatório"
                editQRCode!!.requestFocus()

            } else{

                //outro código - gerar qr code
                gerarQRCode(editQRCode!!.text.toString())

            }


        }

    }

    fun initComponentes() {

        editQRCode = findViewById(R.id.editQRCode)
        btnGerarQRCode = findViewById(R.id.btnGerarQRCode)
        imgQRCode = findViewById(R.id.imgQRCode)


    }

    fun gerarQRCode(conteudoQRCode: String) {

        val qrCode = QRCodeWriter()

        try {

            val bitMatrix = qrCode.encode(conteudoQRCode, BarcodeFormat.QR_CODE, 196, 196)
            val tamanho = bitMatrix.width
            val altura = bitMatrix.height
            
            val bitmap = Bitmap.createBitmap(tamanho, altura, Bitmap.Config.RGB_565)

            //enquanto o x não atingir o tamanho...
            for (x in 0 until tamanho){
                for (y in 0 until altura){
                    bitmap.setPixel(x, y, if(bitMatrix[x, y]) Color.BLACK else Color.WHITE)
                }

                imgQRCode!!.setImageBitmap(bitmap)
            }
            
        }catch (e: WriterException){

        }

    }
}
