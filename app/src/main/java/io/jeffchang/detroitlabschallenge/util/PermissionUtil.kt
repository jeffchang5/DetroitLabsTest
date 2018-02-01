package io.jeffchang.detroitlabschallenge.util

import android.content.pm.PackageManager
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity


/**
 * Created by jeffreychang on 1/30/18.
 */
object PermissionUtil {
    class PermissionObject(private val activity: AppCompatActivity?,
                           private val fragment: Fragment?) {
        fun request(permissionName: String): PermissionRequestObject {
            return if (activity != null) {
                PermissionRequestObject(activity, arrayOf(permissionName))
            } else {
                PermissionRequestObject(this.fragment!!, arrayOf(permissionName))
            }
        }

        fun has(permissionName: String): Boolean {
            val permissionCheck: Int = if (activity != null) {
                ContextCompat.checkSelfPermission(activity, permissionName)
            } else {
                ContextCompat.checkSelfPermission(fragment!!.context!!,
                        permissionName)
            }
            return permissionCheck == PackageManager.PERMISSION_GRANTED
        }

    }

    class PermissionRequestObject(private var permissionNames: Array<String>) {
        private var onAllGrantedFunc: (() -> Unit)? = null
        private var onDeniedFunc: ((onDenied: String) -> Unit)? = null
        private var activity: AppCompatActivity? = null
        private var fragment: Fragment? = null

        private var requestCode: Int = -1

        constructor(activity: AppCompatActivity,
                    permissionNames: Array<String>) : this(permissionNames) {
            this.activity = activity
        }

        constructor(fragment: Fragment, permissionNames: Array<String>) : this(permissionNames) {
            this.fragment = fragment
        }

        fun ask(reqCode: Int): PermissionRequestObject {
            requestCode = reqCode
            if (activity != null) {
                ActivityCompat.requestPermissions(activity!!, permissionNames, reqCode)
            } else {
                fragment!!.requestPermissions(permissionNames, reqCode)
            }
            return this
        }

        fun onAllGranted(onAllGranted: () -> Unit): PermissionRequestObject {
            onAllGrantedFunc = onAllGranted
            return this
        }

        fun onAnyDenied(onDenied: (permisison: String) -> Unit): PermissionRequestObject {
            onDeniedFunc = onDenied
            return this
        }
        fun onRequestPermissionsResult(requestCode: Int,
                                       permissions: Array<out String>,
                                       grantResults: IntArray) {
            if (this.requestCode == requestCode) {
                var isPermissionDenied = false
                (0 until permissions.size)
                        .filter { grantResults[it] == PackageManager.PERMISSION_DENIED }
                        .forEach {
                            if (onDeniedFunc != null) {
                                onDeniedFunc?.invoke(permissions[it])
                                isPermissionDenied = true
                            }
                        }
                if ((onAllGrantedFunc != null) and (isPermissionDenied == false))
                    onAllGrantedFunc?.invoke()
            }

        }
    }

    fun with(activity: AppCompatActivity): PermissionObject {
        return PermissionObject(activity, null)
    }

    fun with(fragment: Fragment): PermissionObject {
        return PermissionObject(null, fragment)
    }
}