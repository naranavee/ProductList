# Product List
## Table of contents

- [Introduction](#introduction)
- [Demo](#demo)
- [Run](#run)
- [Technology](#technology)
- [Features](#features)
- [Database Models](#database)
- [Color Palette](#colors)
- [License](#license)

## Introduction

Product List(Android Assessment) is an Android application that fetches a list of products from the Fake store API and displays them using a RecyclerView. The app uses Room for local database storage and follows the MVVM architecture.


NOTE: Please read the RUN section before opening an issue.

## Demo

![demo1](https://github.com/user-attachments/assets/b087876d-7704-4a56-acb5-b0e0873767da)

![demo2](https://github.com/user-attachments/assets/432529ff-f588-4461-b9c1-cfaa051d956b)




The app displays a list of products with details such as id, title, Category, Description. You can view the list of products fetched from the API and stored locally in the database.
## Run

To run this application, you need to set up the necessary environment and dependencies. Follow these steps:

1. *Clone the repository*:
   ```bash
   git clone "https://github.com/naranavee/ProductList.git"

2.Open the project in Android Studio.

3.Install dependencies: Open build.gradle files and ensure that all dependencies are included.

4:API Configuration:

Update the base URL in MainActivity.kt if needed:

Api:private val URL_BASE = "https://fakestoreapi.com/products"


## Technology


The application is built with:

-Kotlin
-Android SDK
-Room for local database
-Retrofit for network operations
-RecyclerView for displaying lists
-MVVM architecture
-ViewModel
-LiveData
-Coroutines
-Gson for JSON serialization

## Features

The application provides the following features:

-Fetches Products data from the Fake store API.
-Stores Products data locally using Room Database.
-Displays a list of books with details including ID, title, category, Description.
-Implements MVVM architecture for a clean and maintainable codebase.


## Database

Database Models
Product Entity
The Product entity represents a product in the Room database.

-id (Int) - Product ID from the API
-title (String) - Title of the product
-Category (String) - Category of the product
-Description (String) - Description of Product

## Colors

Below is the color palette used in this application:

- ![#478ba2](https://via.placeholder.com/15/478ba2/000000?text=+) #478ba2


## License

Apache-2.0 License:
