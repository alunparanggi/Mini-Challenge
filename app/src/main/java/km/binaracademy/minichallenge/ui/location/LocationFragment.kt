package km.binaracademy.minichallenge.ui.location

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import km.binaracademy.minichallenge.R
import km.binaracademy.minichallenge.databinding.FragmentLocationBinding

class LocationFragment : Fragment() {

    private lateinit var binding: FragmentLocationBinding
    private lateinit var locationProviderClient: FusedLocationProviderClient
    private var longitude = 0.0
    private var latitude = 0.0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLocationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setClickListeners()
    }

    private fun setClickListeners() {
        binding.btnGetLocation.setOnClickListener {
            getCurrentLocation()
        }

        binding.btnOpenMaps.setOnClickListener {
            openMaps(longitude, latitude)
        }
    }

    private fun openMaps(longitude: Double, latitude: Double) {
        val uri = Uri.parse("geo:$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        context?.startActivity(mapIntent)
    }

    private fun getCurrentLocation() {
        locationProviderClient = LocationServices.getFusedLocationProviderClient(context)
        this?.let {
            if (ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(
                    requireContext(),
                    Manifest.permission.ACCESS_COARSE_LOCATION
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                permissionRequestLauncher.launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
                return
            }
        }


        locationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                longitude = location.longitude
                latitude = location.latitude

                binding.tvLongitudeLocation.text =
                    getString(R.string.text_format_longitude, longitude.toString())
                binding.tvLatitudeLocation.text =
                    getString(R.string.text_format_latitude, latitude.toString())
                binding.tvProviderLocation.text =
                    getString(R.string.text_format_provider, location.provider)

            }.addOnFailureListener {
                Toast.makeText(context, it.message, Toast.LENGTH_SHORT).show()
            }
    }

    private val permissionRequestLauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { grantResult ->
            if (grantResult.isNotEmpty() && grantResult[Manifest.permission.ACCESS_FINE_LOCATION] == true &&
                grantResult[Manifest.permission.ACCESS_COARSE_LOCATION] == true
            ) {
                getCurrentLocation()
            } else {
                Toast.makeText(context, "Perlu izin", Toast.LENGTH_SHORT).show()
            }
        }

    companion object {
        private const val PERMISSION_CODE_LOCATION = 1001
    }

}