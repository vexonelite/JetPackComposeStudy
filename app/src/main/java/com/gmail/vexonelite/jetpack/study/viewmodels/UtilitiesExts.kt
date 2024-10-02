package com.gmail.vexonelite.jetpack.study.viewmodels


import java.util.UUID


fun generateRandomStringViaUuid(): String = UUID.randomUUID().toString() + "_" + System.currentTimeMillis()

