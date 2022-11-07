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
    access: string
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

export interface CreationMsg {
    message: string
}

/*

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