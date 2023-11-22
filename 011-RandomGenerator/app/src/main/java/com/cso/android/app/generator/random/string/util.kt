package com.cso.android.app.generator.random.string

import kotlin.random.Random

fun generateRandomTextEN(count: Int, random:Random = Random): String
{
    var sb = StringBuilder(count)

    (1..count).forEach{_ -> sb.append((if (random.nextBoolean()) 'A' else 'a') + random.nextInt(26))}

    return sb.toString()
}