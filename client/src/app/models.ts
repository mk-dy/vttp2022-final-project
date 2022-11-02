export interface UserLogin {
    email: string
    password: string
}

export interface User {
    id?: string
    firstName: string
    lastName: string
    email: string
    mobile: string
    password: string
}

// this will be used on the main page, also create this table in mysql
export interface Product {
    id: string
    name: string
    descr: string
    price: number
    imgLink: string
}

export interface Fabric {
    id: string
    name: string
    imgLink: string
}

/*
what do i need?

PRODUCT
=========
product id
product name
product description
price



CUSTOMISATION (see if i want to package it together with the product page)
=============
material


FAVOURITES?
============
include delete button

CART
=======
promo code
payment options
name
delivery address
billing address (form array, if checkbox is ticked for "If billing address differs from delivery address")

*/