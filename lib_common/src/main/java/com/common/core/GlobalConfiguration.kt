package com.common.core

import android.app.Application
import android.content.Context
import androidx.fragment.app.FragmentManager
import com.base.integration.AppLifeCycles
import com.base.integration.ConfigModule

class GlobalConfiguration : ConfigModule {

    override fun injectAppLifecycle(context: Context, lifeCycles: MutableList<AppLifeCycles>) {
        lifeCycles.add(ApplicationLifeCyclesImpl())
    }

    override fun injectActivityLifecycle(context: Context, lifeCycles: MutableList<Application.ActivityLifecycleCallbacks>) {
        lifeCycles.add(ActivityLifecycleCallbacksImpl())
    }

    override fun injectFragmentLifecycle(context: Context, lifeCycles: MutableList<FragmentManager.FragmentLifecycleCallbacks>) {
        lifeCycles.add(FragmentLifecycleCallbacksImpl())
    }

}