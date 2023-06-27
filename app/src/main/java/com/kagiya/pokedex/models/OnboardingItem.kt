package com.kagiya.pokedex.models

data class  OnboardingItem(
val image: Int,
val title: Int,
val description: Int,
val buttonText: Int,
val onButtonClicked: () -> Unit
)
