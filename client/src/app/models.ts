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

export interface FinalProduct {
    id: string
    prodId: string
    userId: string

    // chalk bag
    baseBagDesign: string
    bootDesign: string
    exteriorDesign: string
    withBoot: string
    hoopStraps: string
    keychainHolders: string
    keychainNum: string
    upsize: string

    // chalk bucket
    baseType: string
    frontSideClosure: string
    magneticClosure: string
    dRingWebbing: string
    frontPocketDesign: string
    frontPocketBackDesign: string
    backDesign: string
    baseBucketDesign: string

    quantity: number
    remarks: string
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

export interface PaymentInfo {
    amount: number; // to be multiplied by 100 into cents
    currency: string;
    receiptEmail: string;
}


export interface Address {
    street: string;
    postalCode: string;
}

export interface Purchase {
    user: User;
    shippingAddress: Address;
    billingAddress: Address;
    order: Order;
    orderItems: FinalProduct[]; 
}

// export interface Order {
//     totalQuantity: number
//     totalPrice: number
// }

export class Order {
    totalQuantity!: number
    totalPrice!: number;
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