package com.mmpocket.ideapocket.domain.service

interface SequenceGenerator {
    fun generate(name: String): Long
}