package com.sum.assignment_aisle.model.notesmodel

data class Invites(
    val pending_invitations_count: Int,
    val profiles: List<Profile>,
    val totalPages: Int
)