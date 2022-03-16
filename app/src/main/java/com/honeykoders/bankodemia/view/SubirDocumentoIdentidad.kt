package com.honeykoders.bankodemia.view

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.honeykoders.bankodemia.R
import com.honeykoders.bankodemia.databinding.FragmentSubirDocumentoIdentidadBinding
import com.honeykoders.bankodemia.model.SingUpModel
import com.honeykoders.bankodemia.viewmodel.SingUpViewModel
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*

@Suppress("DEPRECATION")

class SubirDocumentoIdentidad : Fragment() {

    private var _binding: FragmentSubirDocumentoIdentidadBinding? = null
    private val binding get() = _binding!!
    lateinit var absolutePathImagen: String
    var archivoFoto: File? = null
    var imagen: Bitmap? = null
    val viewModel: SingUpViewModel by viewModels()

    private val startForResult = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ){
            result: ActivityResult->
        if (result.resultCode == Activity.RESULT_OK){
            imagen = BitmapFactory.decodeFile(absolutePathImagen)
            binding.ivDocId.setImageBitmap(imagen)
            /*archivoFoto?.also { foto ->
                viewModel.enviarFoto(foto)
            }*/
        }
    }

    private fun singUp(){
        val singUp = SingUpModel(
            "root@email.com",
            "root",
            "rootest",
            "2013-04-14T00:40:37.437Z",
            "hola1234",
            "+524491234567",
            "R0lGODlhAQABAIAAAAAAAP///yH5BAEAAAAALAAAAAABAAEAAAIBRAA7",
            "INE",
            "Ingeniero"
        )
        mandarDatos(singUp)
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
        val docIdent = arguments?.getString("docIdent")
        binding.btnAtras.setText(docIdent)

        binding.btnTomarFoto.setOnClickListener {
            solicitarPermisos()
        }

        binding.btnSubirInformacion.setOnClickListener {
            //observers()
            //singUp()
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