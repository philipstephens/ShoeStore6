package com.udacity.shoestore.models

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import timber.log.Timber

class ShoeViewModel(_language: String): ViewModel() {
    private var language = MutableLiveData<String>()
    val languageValue: LiveData<String>
    get() = language

    private var shoeListData = MutableLiveData<ArrayList<Shoe>>()
    val shoeListDataValue: LiveData<ArrayList<Shoe>>
    get() = shoeListData

    val nullShoeDetail = Shoe("", 0.0, "", "", emptyList())
    var tempShoeData = MutableLiveData<Shoe>()

    init {
        language.value = _language
        shoeListData.value = arrayListOf<Shoe>(
            Shoe("Nike", 10.0, "Nike Company", "man's runner", mutableListOf("Nike.jpg", "Joe.png"))
        )
    }

    fun setLanguage(_language: String) {
        when(_language) {
            "en" -> language.value = "en"
            "fr" -> language.value = "fr"
            else -> language.value = "en"
        }
    }

    fun updateShoeList(shoe: Shoe){
        var found = false
        var i = 0

        if (shoe.name.trim() == "") return

        if (shoeListData.value!!.isEmpty()) {
            shoeListData.value!!.add(shoe)
            return
        }

        for ( s in shoeListData.value!! ) {
            if (s.name == shoe.name) {
                shoeListData.value!![i] = shoe
                return
            }
        }

        shoeListData.value!!.add(shoe)
    }

    fun getShoeList(): Array<String> {
        var shoeList: Array<String> = emptyArray()

        if (shoeListData.value.isNullOrEmpty()) {
            return emptyArray()
        }

        Timber.d("***** Debug Message: Hello!")

        for (shoe in shoeListData.value!!) {
            Timber.d(shoe.toString())
            shoeList = shoeList.plusElement(shoe.name)
        }

        Timber.d("size: ${shoeList.size}")
        return shoeList
    }

    fun getShoeListDetail(i: Int):Shoe {
        val shoeListSize = shoeListData.value?.size ?: 0

        if (shoeListSize > 0) {
            if ((i >= 0) && (i <= shoeListSize-1)) return shoeListData.value?.get(i) ?: nullShoeDetail
        }
        return nullShoeDetail
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

    fun getNameListMap() : Map<String, String> {
        return when (language.value) {
            "en" -> { mapOf(
                "list_shoes_text_heading" to "List of Shoes",
                "name_heading" to "Names") }
            "fr" -> { mapOf(
                "list_shoes_text_heading" to "Liste des chaussures",
                "name_heading" to "Les noms") }
            else -> emptyMap()
        }
    }

    fun getDetailMap() : Map<String, String> {
        return when (language.value) {
            "en" -> { mapOf(
                "cancel_button_text" to "Cancel",
                "save_button_text" to "Save") }
            "fr" -> { mapOf(
                "cancel_button_text" to "Annuler",
                "save_button_text" to "Sauver"
            ) }
            else -> emptyMap()
        }
    }

    fun getDetailHeadingArray() : Array<String> {
        return when (language.value) {
            "en" -> { arrayOf( "Name of Shoe", "Company Name", "Shoe Size", "Description") }
            "fr" -> { arrayOf( "Nom de la chaussure", "Nom de l'entreprise", "Taille des chaussures", "Description") }
            else -> emptyArray()
        }
    }

    fun clearTempForm() {
        tempShoeData.value = nullShoeDetail
    }

    fun setFormData(_index: Int) {
        if (_index in 0..shoeListData.value!!.lastIndex) {
            tempShoeData.value = shoeListData.value!![_index]
        }
    }

    fun saveEditedForm(_name: String, _company: String, _size: String, _description: String) {
        tempShoeData.value?.name = _name
        tempShoeData.value?.company = _company
        tempShoeData.value?.size = _size.toDouble()
        tempShoeData.value?.description = _description
        tempShoeData.value?.images = emptyList()

        if (shoeListData.value?.isEmpty() == true) {
            if(_name.trim() != "") {
                tempShoeData.value?.let { shoeListData.value?.add(it) }
                return
            }
        }

        var found = false
        var index = 0

        for(i in 0..shoeListData.value!!.lastIndex) {
            if (_name == shoeListData.value?.get(i)?.name) {
                index = i
                found = true
            }
        }

        if (found) {
            shoeListData.value!![index] = tempShoeData.value!!
        } else {
            if (_name.trim() != "") {
                tempShoeData.value?.let { shoeListData.value?.add(it) }
            }
        }
    }
}
