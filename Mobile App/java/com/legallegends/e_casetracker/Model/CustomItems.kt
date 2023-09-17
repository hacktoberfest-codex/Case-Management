package com.legallegends.e_casetracker.Model

data class CustomItems(
    val caseNumber: String,
    val caseStatus: String,
    val city: String,
    val categories: String,
    val type: String,
    val lawyerName: String,
    val caseDetails: String
)