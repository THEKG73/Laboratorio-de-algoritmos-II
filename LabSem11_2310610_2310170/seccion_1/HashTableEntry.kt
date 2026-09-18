class HashTableEntry(val clave: Int, var valor: String) {
    var anterior: HashTableEntry? = null
    var siguiente: HashTableEntry? = null
}