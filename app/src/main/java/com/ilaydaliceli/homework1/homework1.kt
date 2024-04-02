// Item sınıfı
open class Item(val name: String, val price: Double)

// Food sınıfı Item sınıfından türetilmiş alt sınıf
class Food(name: String, price: Double, val weight: String) : Item(name, price)

// Cloth sınıfı Item sınıfından türetilmiş alt sınıf
class Cloth(name: String, price: Double, val type: String) : Item(name, price)

// ShoppingList sınıfı
class ShoppingList {
    private val itemList = mutableListOf<Item>()

    // Ürün ekleme fonksiyonu
    fun addItem() {
        println("Enter the item type you want to add (1 for Food, 2 for Cloth) :")
        var type = readLine()?.toIntOrNull()

        while (type !in listOf(1, 2)) {
            println("Invalid number. Enter the item type you want to add (1 for Food, 2 for Cloth): ")
            type = readLine()?.toIntOrNull()
        }

        print("Enter the item name you want to add: ")
        val name = readLine() ?: ""

        print("Enter the item price you want to add : ")
        var price: Double? = null
        while (price == null) {
            price = readLine()?.toDoubleOrNull()
            if (price == null) {
                println("Please enter a valid price!")
            }
        }

        if (type == 1) {
            print("Enter the food weight: ")
            val weight = readLine() ?: ""
            itemList.add(Food(name, price, weight))
        } else {
            print("Enter the cloth type: ")
            val clothType = readLine() ?: ""
            itemList.add(Cloth(name, price, clothType))
        }
        println("$name is added successfully!")
    }

    // Ürünleri listeleme fonksiyonu
    fun displayItems() {
        if (itemList.isEmpty()) {
            println("Your shopping list is empty.")
            return
        }

        println("Your shopping list:")
        for ((index, item) in itemList.withIndex()) {
            if (item is Food) {
                println("${index + 1}. ${item.name} ${item.price}$ ${item.weight}kg")
            } else if (item is Cloth) {
                println("${index + 1}. ${item.name} ${item.price}$ ${item.type}")
            }
        }
    }

    // Ürün silme fonksiyonu
    fun deleteItem() {
        if (itemList.isEmpty()) {
            println("Your shopping list is empty.")
            return
        }

        displayItems()

        print("Enter the item number you want to delete : ")
        var index: Int? = null
        while (index == null) {
            index = readLine()?.toIntOrNull()?.minus(1)
            if (index == null || index !in itemList.indices) {
                println("Invalid number! Please try again.")
            }
        }

        println("${itemList[index].name} is deleted successfully!")
        itemList.removeAt(index)
    }
}

fun main() {
    val shoppingList = ShoppingList()

    println("***** Welcome to Shopping List App *****")

    var choice: Int

    do {
        println("\nMake your choice: (1-2-3-4)")
        println("1. Add Item")
        println("2. Display Item")
        println("3. Delete Item")
        println("4. Exit")
        print("Your choice is : ")
        choice = readLine()?.toIntOrNull() ?: 0

        when (choice) {
            1 -> shoppingList.addItem()
            2 -> shoppingList.displayItems()
            3 -> shoppingList.deleteItem()
            4 -> println("Exiting...")
            else -> println("Invalid choice! Please try again.")
        }
    } while(choice!=4)
}