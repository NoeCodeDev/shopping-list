package com.example.shoppinglist.model

import android.os.Parcel
import android.os.Parcelable


val SHOPPING_LISTS = listOf(
    ShoppingList(
        "List 1", "Description list 1", listOf(
            Item("Calabacín", true),
            Item("Carne de cerdo", false),
            Item("Rúcula", false),
            Item("Queso semicurado", true),
        )
    ),
    ShoppingList(
        "List 2", "Description list 2", listOf(
            Item("Tortilla de patatas", false),
            Item("Makis", true),
            Item("Mochis", true),
            Item("Sushi", true),
            Item("Palillos", false),
        )
    ),
    ShoppingList(
        "List 3", "Description list 3", listOf()
    ),
)

data class Item(
    val name: String,
    val bought: Boolean
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readByte() != 0.toByte()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeByte(if (bought) 1 else 0)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            return Item(parcel)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }

}

data class ShoppingList(
    val name: String,
    val description: String,
    val item: List<Item>,
): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.createTypedArrayList(Item)!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeTypedList(item)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<ShoppingList> {
        override fun createFromParcel(parcel: Parcel): ShoppingList {
            return ShoppingList(parcel)
        }

        override fun newArray(size: Int): Array<ShoppingList?> {
            return arrayOfNulls(size)
        }
    }
}