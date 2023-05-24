package com.sum.assignment_aisle.model.notesmodel

data class ProfileData(
    val invitation_type: String,
    val preferences: List<PreferenceX>,
    val question: String
)