package dev.roeeyn.neonergia.data.models

data class DeviceDemoResponse(
    val ssid: String,
    val deviceId: String,
    val timestamp: String,
    val location: String
)

data class FirestoreDeviceEntry(
    val ssid: String,
    val deviceId: String,
    val timestamp: String,
    val location: String
)

data class SuccessEntryPost(
    val success: String
)