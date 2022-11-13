export interface UserLogin {
    email: string
    password: string
}

export class User {
    id?: string
    firstName!: string
    lastName!: string
    email!: string
    mobile!: string
    password!: string
}

export interface UserResponse {
    id: string
    firstName: string
    lastName: string
    email: string
    mobile: string
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

export class CartItem {
    id: string
    prodId: string
    userId: string
    baseBagDesign: string
    bootDesign: string
    exteriorDesign: string
    withBoot: string
    hoopStraps: string
    keychainHolders: string
    keychainNum: string
    upsize: string
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

    constructor(product: FinalProduct) {
        this.id = product.id;
        this.prodId = product.prodId;
        this.userId = product.userId;

        // chalk bag
        this.baseBagDesign = product.baseBagDesign;
        this.bootDesign = product.bootDesign;
        this.exteriorDesign = product.exteriorDesign;
        this.withBoot = product.withBoot;
        this.hoopStraps = product.hoopStraps;
        this.keychainHolders = product.keychainHolders;
        this.keychainNum = product.keychainNum;
        this.upsize = product.upsize;
        // chalk bucket
        this.baseType = product.baseType;
        this.frontSideClosure = product.frontSideClosure;
        this.magneticClosure = product.magneticClosure;
        this.dRingWebbing = product.dRingWebbing;
        this.frontPocketDesign = product.frontPocketDesign;
        this.frontPocketBackDesign = product.frontPocketBackDesign;
        this.backDesign = product.backDesign;
        this.baseBucketDesign = product.baseBucketDesign;

        this.quantity = product.quantity;
        // this.quantity = 1;
        this.remarks = product.remarks;
        this.price = product.price;
        this.imgLink = product.imgLink;

    }
    
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

export class OrderItem {
    imageUrl: string;
    unitPrice: number;
    quantity: number;
    productId: string;

    constructor(cartItem: CartItem) {
        this.imageUrl = cartItem.imgLink;
        this.quantity = cartItem.quantity;
        this.unitPrice = cartItem.price;
        this.productId = cartItem.prodId;
    }
}


// export class Purchase {
//     user: User;
//     shippingAddress: Address;
//     billingAddress: Address;
//     order: Order;
//     orderItems: OrderItem[]; 
// }

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