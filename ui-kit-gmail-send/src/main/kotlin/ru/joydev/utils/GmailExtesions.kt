package ru.joydev.utils

import android.os.Looper

internal fun isMainThread() = Looper.getMainLooper().thread == Thread.currentThread()
