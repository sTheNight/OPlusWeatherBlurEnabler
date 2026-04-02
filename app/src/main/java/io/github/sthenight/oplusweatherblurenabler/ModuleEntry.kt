package io.github.sthenight.oplusweatherblurenabler

import android.util.Log
import io.github.libxposed.api.XposedModule
import io.github.libxposed.api.XposedModuleInterface

class ModuleEntry : XposedModule() {
    override fun onPackageLoaded(param: XposedModuleInterface.PackageLoadedParam) {
        if (param.packageName != "com.coloros.weather2") return
        try {
            val cl = param.defaultClassLoader
            val clazz = cl.loadClass(
                "com.oplus.weather.main.utils.BackgroundBlurUtils"
            )
            val method1 = clazz.getDeclaredMethod("isBlurSupportMachine")
            val method2 = clazz.getDeclaredMethod("isBlurSupported")
            hook(method1).intercept {
                true
            }
            hook(method2).intercept {
                true
            }
        } catch (e: Exception) {
            log(Log.ERROR,"OPlusWeatherBlurEnabler",e.toString())
        }
    }
}