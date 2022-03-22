package com.honeykoders.bankodemia.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.common.mostrarMensajeDeError
import com.honeykoders.bankodemia.databinding.FragmentSubirDocumentoIdentidadBinding
import com.honeykoders.bankodemia.model.SingUpModel
import com.honeykoders.bankodemia.viewmodel.SingUpViewModel
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import java.util.Base64.getEncoder


@Suppress("DEPRECATION")

class SubirDocumentoIdentidad : Fragment() {

    private var _binding: FragmentSubirDocumentoIdentidadBinding? = null
    private val binding get() = _binding!!
    lateinit var absolutePathImagen: String
    var archivoFoto: File? = null
    var image: Bitmap? = null
    var imageToBase64: String? = null
    var docIdent: String? = null
    val viewModel: SingUpViewModel by viewModels()

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result: ActivityResult->
        if (result.resultCode == Activity.RESULT_OK){
            image = BitmapFactory.decodeFile(absolutePathImagen)
            Log.e("ImagenP", image.toString())
            binding.ivDocId.setImageBitmap(image)
            imageToBase64 = convertImageToBase64(image!!)
            Log.e("Imagen", imageToBase64.toString())
            /*archivoFoto?.also { foto ->
                viewModel.enviarFoto(foto)
            }*/
        }
    }

    fun convertImageToBase64(image: Bitmap): String {
        val baos = ByteArrayOutputStream()
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val b = baos.toByteArray()
        val imageEncoded = Base64.encodeToString(b,0,64, Base64.NO_WRAP)
        return imageEncoded;
    }

    private fun singUp(){
        /*imagen:"R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7" */
        Log.e("DocIdent",docIdent.toString())
        try {
            val singUp = imageToBase64?.let {image ->
                SingUpModel(
                    "jesusPrueba@email.com",
                    "Jesus",
                    "Prueba",
                    "2013-04-14T00:40:37.437Z",
                    "hola1234",
                    "+524491234589",
                    image,
                    docIdent.toString(),
                    //"INE",
                    "Ingeniero"
                )
            }
            if (singUp != null) {
                mandarDatos(singUp)
            }
        }catch (e: IOException){
            Log.e("Error",e.toString())
        }

    }

    private fun mandarDatos(singUp: SingUpModel) {
        viewModel.singUp(singUp)

    }

    private fun observers() {
        viewModel.singUpResponse.observe(viewLifecycleOwner){ singUp ->
            Log.d("SingUp",singUp.success.toString())
            //shared.saveToken(it.access_token)
            //shared.saveSession(login)
        }
        viewModel.error.observe(viewLifecycleOwner){ error ->
            Log.d("ErrorMessage", error)
            when(error){
                "User phone already exists" -> context?.let { mostrarMensajeDeError(it,"Número teléfonico ya registrado, ingrese uno diferente") }
                "User already exists" -> context?.let { mostrarMensajeDeError(it,"Correo registrado con un usuario existente") }
                else -> null
            }

        }

        viewModel.badRequest.observe(viewLifecycleOwner){ badRequest ->
            if (badRequest){
                Log.e("bad",badRequest.toString())
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSubirDocumentoIdentidadBinding.inflate(inflater, container, false)
        val root: View = binding.root
        docIdent = arguments?.getString("docIdent")
        binding.btnAtras.setText(docIdent)

        binding.btnTomarFoto.setOnClickListener {
            solicitarPermisos()
        }

        binding.btnSubirInformacion.setOnClickListener {
            observers()
            singUp()
        }

        binding.btnAtras.setOnClickListener {
            findNavController().navigate(R.id.seleccionarDocumentoIdentidad)
        }
        return root
    }

    private fun solicitarPermisos() {
        val REQUEST_CODE_CAMARA = 100
        val permisoCamara = ContextCompat.checkSelfPermission(requireContext(),android.Manifest.permission.CAMERA)
        if (permisoCamara == PackageManager.PERMISSION_GRANTED){
            lanzarCamara()
        }else{
            requestPermissions(
                arrayOf(android.Manifest.permission.CAMERA),
                REQUEST_CODE_CAMARA
            )
        }
    }

    @SuppressLint("QueryPermissionsNeeded")
    private fun lanzarCamara() {
        val packageManager = requireContext().packageManager
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { camara ->
            camara.resolveActivity(packageManager)?.also{
                archivoFoto = try {
                    crearImagen()
                }catch (ex:IOException){
                    null
                }
                archivoFoto?.also {archivo ->
                    val photoPATH: Uri = FileProvider.getUriForFile(
                        requireContext(),
                        "com.honeykoders.bankodemia.fileprovider",
                        archivo
                    )
                    camara.putExtra(MediaStore.EXTRA_OUTPUT, photoPATH)
                    camara.putExtra("REQUEST_TAKE_PHOTO", 100)
                    startForResult.launch(camara)
                }
            }
        }
    }

    @SuppressLint("SimpleDateFormat")
    private fun crearImagen(): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val directorioAlmacenamineto: File? = getActivity()?.getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        return File.createTempFile(
            "JPEG_${timeStamp}",
            ".jpeg",
            directorioAlmacenamineto
        ).apply {
            absolutePathImagen = absolutePath
        }
    }


}