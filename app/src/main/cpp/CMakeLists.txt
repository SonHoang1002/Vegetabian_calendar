cmake_minimum_required(VERSION 3.22.1)

project("native-lib")

# ===== AJOUT POUR COMPATIBILITÉ 16KB =====
# Définir les flags de linker pour alignment 16KB
set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -Wl,-z,max-page-size=16384")
set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Wl,-z,max-page-size=16384")

# Alternative: utiliser target_link_options (CMake 3.13+)
# add_link_options(-Wl,-z,max-page-size=16384)

add_library(
    native-lib
    SHARED
    native-lib.cpp
)

find_library(
    log-lib
    log
)

target_link_libraries(
    native-lib
    ${log-lib}
)

# ===== CONFIGURATION ADDITIONNELLE POUR 16KB =====
# S'assurer que la bibliothèque est alignée correctement
set_target_properties(native-lib PROPERTIES
    LINK_FLAGS "-Wl,-z,max-page-size=16384"
)
