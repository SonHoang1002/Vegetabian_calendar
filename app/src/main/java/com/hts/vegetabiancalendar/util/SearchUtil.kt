package com.hts.vegetabiancalendar.util


// Hàm helper để trích xuất descriptions dựa trên ký hiệu [số]
fun getDescriptionsForText(texts: List<String>, descriptions: List<String>?): List<String> {
    if (descriptions.isNullOrEmpty()) return emptyList()

    val result = mutableListOf<String>()
    val referencePattern = Regex("\\[(\\d+)\\]") // Pattern để tìm [1], [2], etc.

    // Tìm tất cả ký hiệu [số] trong text
    val foundReferences = mutableSetOf<Int>()
    texts.forEach { text ->
        referencePattern.findAll(text).forEach { matchResult ->
            val number = matchResult.groupValues[1].toIntOrNull()
            if (number != null) {
                foundReferences.add(number)
            }
        }
    }

    // Lấy descriptions tương ứng
    foundReferences.sorted().forEach { refNumber ->
        val index = refNumber - 1 // Convert to 0-based index
        if (index >= 0 && index < descriptions.size) {
            result.add("[$refNumber] ${descriptions[index]}")
        }
    }

    return result
}