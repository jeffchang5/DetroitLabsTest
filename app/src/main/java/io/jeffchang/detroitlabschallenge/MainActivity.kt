package io.jeffchang.detroitlabschallenge

import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.annotation.SuppressLint
import android.content.IntentSender
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import io.jeffchang.detroitlabschallenge.ui.now.NowFragment
import io.jeffchang.detroitlabschallenge.ui.places.PlacesFragment
import io.jeffchang.detroitlabschallenge.ui.settings.SettingsFragment
import kotlinx.android.synthetic.main.activity_main.*
import com.google.android.gms.location.LocationRequest
import io.jeffchang.detroitlabschallenge.util.PermissionUtil
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableHelper.dispose
import io.reactivex.schedulers.Schedulers
import pl.charmas.android.reactivelocation2.ReactiveLocationProvider
import timber.log.Timber


class MainActivity : AppCompatActivity() {

    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var settingsClient: SettingsClient
    private lateinit var locationSettingsRequest: LocationSettingsRequest

    private var requestingLocation = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupLocation()

        switchFragments(NowFragment.newInstance())
        activity_main_bottom_navigation.setOnNavigationItemSelectedListener({item ->
            when (item.itemId) {
                R.id.action_whatshot -> {
                    switchFragments(NowFragment.newInstance())
                    true
                }
                R.id.action_places -> {
                    switchFragments(PlacesFragment.newInstance())
                    true
                }
                R.id.action_settings-> {
                    switchFragments(SettingsFragment.newInstance())
                    true
                }
                else -> false
            }

        })
    }

    override fun onResume() {
        super.onResume()
        if (PermissionUtil.with(this).has(ACCESS_FINE_LOCATION))
            startLocationUpdates()
        else {
            if (requestingLocation) {
                requestObject = PermissionUtil.with(this)
                        .request(ACCESS_FINE_LOCATION)
                        .onAllGranted({
                            startLocationUpdates()
                        })
                        .onAnyDenied({ _ ->
                            requestingLocation = false
                            Toast.makeText(this, "Add your location yourself",
                                    Toast.LENGTH_LONG).show()
                        })
                        .ask(REQUEST_PERMISSION_FINE_LOCATION)
            }
        }

    }

    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private var locationRequest: LocationRequest? = null

    private fun setupLocation() {
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
        settingsClient = LocationServices.getSettingsClient(this)
        val builder = LocationSettingsRequest.Builder()
        locationRequest = LocationRequest()
                .setInterval(UPDATE_INTERVAL_IN_MILLISECONDS)
                .setFastestInterval(FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS)
                .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)

        builder.addLocationRequest(locationRequest!!)
        locationSettingsRequest = builder.build()

    }


    private lateinit var requestObject: PermissionUtil.PermissionRequestObject

    private lateinit var locationDisposable: Disposable

    @SuppressLint("MissingPermission")
    private fun startLocationUpdates() {
        settingsClient.checkLocationSettings(locationSettingsRequest)
                .addOnCompleteListener({
                    val locationProvider = ReactiveLocationProvider(this)
                    locationDisposable = locationProvider.getUpdatedLocation(locationRequest)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe({ location ->
                                Toast.makeText(this, location?.latitude.toString(), Toast.LENGTH_LONG).show()
                            })

//                    fusedLocationProviderClient.requestLocationUpdates(LocationRequest.create(),
//                                        locationCallback, Looper.myLooper())

                })
                .addOnFailureListener { exception ->
                    when ((exception as ApiException).statusCode) {
                        LocationSettingsStatusCodes.RESOLUTION_REQUIRED -> {
                            try {
                                (exception as ResolvableApiException)
                                        .startResolutionForResult(this, REQUEST_CHECK_SETTINGS)
                            } catch (sie: IntentSender.SendIntentException) {
                                Timber.i("Failed to open settings")
                            }

                        }
                    }
                }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        requestObject.onRequestPermissionsResult(requestCode, permissions, grantResults)
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun stopLocationUpdates() {
        locationDisposable.dispose()
    }

    private fun switchFragments(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.activity_main_container,
                fragment).commit()
    }
    companion object {

        private const val REQUEST_CHECK_SETTINGS = 0x1
        private const val REQUEST_PERMISSION_FINE_LOCATION = 60

        // Constants for the location listener.
        private const val UPDATE_INTERVAL_IN_MILLISECONDS: Long = 10000
        private const val FASTEST_UPDATE_INTERVAL_IN_MILLISECONDS
                = UPDATE_INTERVAL_IN_MILLISECONDS / 2
    }
}
