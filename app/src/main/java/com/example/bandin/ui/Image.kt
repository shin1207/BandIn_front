package com.example.bandin.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.os.FileUtils
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bandin.R
import com.example.bandin.data.api.MainService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.File
import java.io.FileOutputStream


class Image : AppCompatActivity() {

    private lateinit var imageUri: Uri
    private lateinit var imageApi: MainService


object FileUtil {
    //임시 (이미지 저장용)파일 생성
    fun createTempFile(context: Context, fileName: String): File {
        val storageDir: File? = context.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File(storageDir, fileName)
    }

    fun compressAndSave(context: Context, uri: Uri, file: File) {
        val inputStream = context.contentResolver.openInputStream(uri)
        val bitmap = BitmapFactory.decodeStream(inputStream)
        val outputStream = FileOutputStream(file)

        // 압축할 품질과 함께 Bitmap을 압축하여 저장
        bitmap.compress(Bitmap.CompressFormat.JPEG, 50, outputStream)

        outputStream.flush()
        outputStream.close()
    }
}


    //Uri에서 이미지 얻어오기
    object uriUtil {

        fun toFile(context: Context, uri: Uri): File {
            val fileName = getFileName(context, uri)
            val file = FileUtil.createTempFile(context, fileName)

            FileUtil.compressAndSave(context, uri, file) //임시 파일에 저장 이루어짐

            return File(file.absolutePath)
        }

        fun getFileName(context: Context, uri: Uri): String {
            val name = uri.toString().split("/").last()
            val ext = context.contentResolver.getType(uri)!!.split("/").last()

            return "$name.$ext"
        }
    }


}