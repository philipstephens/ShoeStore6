package com.udacity.shoestore.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeViewModel: ViewModel() {
    var language = MutableLiveData<String>()
    var shoeListData = MutableLiveData<ArrayList<Shoe>>()
    var loggedIn = MutableLiveData<Boolean>()
    val nullShoeDetail = Shoe("No shoes", 0.0, "Null", "Null", listOf("No images"))


    init {
        language.value = "en"
        loggedIn.value = false
        shoeListData.value = arrayListOf<Shoe>(
            Shoe("Nike", 10.0, "Nike Company", "man's runner", mutableListOf("Nike.jpg", "Joe.png")),
            Shoe("Adidas", 7.3, "Adios", "man's runner", mutableListOf("runner2.png")),
            Shoe("Girotti", 5.8, "Oxford Shoes", "luxury man's shoe", mutableListOf("17342.jpg")),
            Shoe("Calla Easta Loafer", 10.0, "Clark's", "woman's loafer", mutableListOf("loafer1.jpg", "brown.png")),
            Shoe("Puma", 10.0, "Puma Company", "mans's runner", mutableListOf("pa1.jpg", "pa2.png"))
        )
    }

    fun onLanguageChange(_language: String) {
        language.value = _language
    }

    fun getShoeList(): Array<String> {
        var shoeList: Array<String> = emptyArray()

        if (shoeListData.value.isNullOrEmpty()) {
            return emptyArray()
        }

        for (shoe in shoeListData.value!!) {
            shoeList.plusElement(shoe.name)
        }

        return shoeList
    }

    fun getShoeListDetail(i: Int):Shoe {
        val shoeListSize = shoeListData.value?.size ?: 0

        if (shoeListSize > 0) {
            if ((i >= 0) && (i <= shoeListSize-1)) return shoeListData.value?.get(i) ?: nullShoeDetail
        }
        return nullShoeDetail
    }

    fun setLogin() {
        loggedIn.value = true
    }

    fun getLogin():Boolean {
        return loggedIn.value ?: false
    }

    fun getLanguage(): String {
        return language.value ?: "en"
    }

    fun setLanguage(_language: String) {
        language.value = _language
    }

    fun getLoginMap() : Map<String, String> {
        return when (language.value) {
            "en" -> {
                 mapOf(
                    "create_button_title" to "Create Account",
                    "hint_email_address" to "Enter email address",
                    "hint_password" to "Enter password",
                    "login_button_title" to "Login",
                    "login_email_title" to "Email Address",
                    "password_title" to "Password"
                )
            }
            "fr" -> {
                mapOf(
                    "create_button_title" to "Créer un compte",
                    "hint_email_address" to "Entrer l\'adresse e-mail",
                    "hint_password" to "Entrer le mot de passe",
                    "login_button_title" to "l\' ouverture de session",
                    "login_email_title" to "l\' adresse électronique",
                    "login_email_title" to "l\' adresse électronique",
                    "password_title" to "Mot de passe"
                )
            }
            else -> emptyMap()
        }
    }

    fun getWelcomeMap() : Map<String, String> {
        return when (language.value) {
            "en" -> { mapOf(
                "welcome_heading" to "Welcome",
                "welcome_text1" to "Welcome to the shoe store inventory application!",
                "welcome_text1b" to "We hope you will find everything you need and enjoy all the features of this app!",
                "welcome_text2" to "With this app you will be able to:",
                "welcome_text2_list1" to "* View instructions",
                "welcome_text2_list2" to "* Obtain a list of shoes",
                "welcome_text2_list3" to "* View detailed information about a shoe",
                "welcome_button_text" to "Instructions")
            }

            "fr" -> { mapOf(
                "welcome_heading" to "Bienvenue",
                "welcome_text1" to "Bienvenue dans l'application d'inventaire de magasin de chaussures !",
                "welcome_text1b" to "Nous espérons que vous trouverez tout ce dont vous avez besoin et que vous apprécierez toutes les fonctionnalités de cette application !",
                "welcome_text2" to  "Avec cette application, vous pourrez :",
                "welcome_text2_list1" to "* Afficher les instructions",
                "welcome_text2_list2" to "* Obtenir une liste de chaussures",
                "welcome_text2_list3" to "* Afficher des informations détaillées sur une chaussure",
                "welcome_button_text" to "Instructions")
            }

            else -> emptyMap()
        }
    }

    fun getInstructionsMap() : Map<String, String> {
        return when (language.value) {
            "en" -> { mapOf(
                "instructions_button_text" to "List Shoes",
                "instructions_heading" to "Instructions",
                "instructions_text1" to "'List Shoes' button to see a list of shoes.",
                "instructions_text2" to "Click on a shoe to see a shoe\'s details.")
            }

            "fr" -> { mapOf(
                "instructions_button_text" to "Liste des chaussures",
                "instructions_heading" to "Instructions",
                "instructions_text1" to "'Liste des chaussures' bouton pour voir une liste de chaussures",
                "instructions_text2" to "Cliquez sur une chaussure pour en voir les détails.")
            }

            else -> emptyMap()
        }
    }
}
