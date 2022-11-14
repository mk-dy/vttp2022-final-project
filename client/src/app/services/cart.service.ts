import { Injectable } from '@angular/core';
import { BehaviorSubject, Subject } from 'rxjs';

import { FinalProduct, CartItem } from '../models';
import { TokenStorageService } from './token-storage.service';

@Injectable({
  providedIn: 'root'
})
export class CartService {
    
    
    cartItems: CartItem[] = [];
    cartItemsById: CartItem[] = [];

    totalPrice: Subject<number> = new BehaviorSubject<number>(0);
    totalQuantity: Subject<number> = new BehaviorSubject<number>(0);

    userId!: string
    // storage: Storage = localStorage;

    constructor(private tokenStorageSvc: TokenStorageService) { 

        // read data from storage
        const data = JSON.parse(localStorage.getItem('cartItems') || '[]');
        this.userId = this.tokenStorageSvc.getUser().id // TEST

        if (data !== null) {
            this.cartItems = data
            // TEST
            // console.info('>>>CHECK USER ID FIRST',this.userId)

            // WHY DOES THIS CLEAR EMPTY THE DATA IN cartItems? where cartItems = []
            // for (let item of this.cartItems) {
            //     if (this.userId === item.userId) {
            //         console.info('>>>ITEMZ',item)
            //         console.info('>>>CHECK USER ID IN IF LOOP',this.userId)
            //         this.cartItemsById.push(item)
            //     }
            // }

            // this.cartItems = this.cartItemsById
            // console.info('>>>CHECK USER ID',this.userId)
            // console.info('>>>CHECK NEW CART ITEM',this.cartItems)




            
            // compute totals based on the data that is read from storage

            this.computeCartTotals();
        }

    }

    addToCart(theCartItem: CartItem) {

        console.info('check the cart item',theCartItem)

        // check if we already have the item in our cart
        let alreadyExistsInCart: boolean = false;
        let existingCartItem: CartItem = undefined!;

        if (this.cartItems.length > 0) {
            // find the item in the cart based on item id
            existingCartItem = this.cartItems.find( tempCartItem => 
                tempCartItem.prodId === theCartItem.prodId )!; // TO LOOK AT AGAIN
            // check if we found it
            alreadyExistsInCart = (existingCartItem != undefined);
        }

        if (alreadyExistsInCart) {
            existingCartItem.quantity = existingCartItem.quantity + theCartItem.quantity;
        }
        else {
            // just add the item to the array
            this.cartItems.push(theCartItem);
        }

        // compute cart total price and total quantity
        this.computeCartTotals();
    }

    computeCartTotals(userId?: string) {
        // TEST
        this.userId = this.tokenStorageSvc.getUser().id
        for (let item of this.cartItems) {
            if (this.userId === item.userId) {
                this.cartItemsById.push(item)
            }
        }
        console.info('>>>> current cartItemsById: ' + this.cartItemsById )
        

        let totalPriceValue: number = 0;
        let totalQuantityValue: number = 0;

        for (let currentCartItem of this.cartItems) {
            totalPriceValue += currentCartItem.quantity * currentCartItem.price;
            totalQuantityValue += currentCartItem.quantity;
        }


        // // TEST
        // for (let currentCartItem of this.cartItemsById) {
        //     totalPriceValue += currentCartItem.quantity * currentCartItem.price;
        //     totalQuantityValue += currentCartItem.quantity;
        // }

        this.totalPrice.next(totalPriceValue);
        this.totalQuantity.next(totalQuantityValue);

        // log cart data just for debugging purposes
        this.logCartData(totalPriceValue, totalQuantityValue);

        // persist cart data
        this.storeCartItems();    
    }

    storeCartItems() {
        localStorage.setItem('cartItems', JSON.stringify(this.cartItems));
    }

    logCartData(totalPriceValue: number, totalQuantityValue: number) {

        console.log('Contents of the cart');
        for (let item of this.cartItems) {
            const subTotalPrice = item.quantity * item.price;
            console.log(`name: ${item.prodId}, quantity=${item.quantity}, unitPrice=${item.price}, subTotalPrice=${subTotalPrice}`);
        }

        console.log(`totalPrice: ${totalPriceValue.toFixed(2)}, totalQuantity: ${totalQuantityValue}`);
        console.log('----');
    }

    decrementQuantity(theCartItem: CartItem) {

        theCartItem.quantity--;

        if (theCartItem.quantity === 0) {
            this.remove(theCartItem);
        }
        else {
            this.computeCartTotals();
        }
    }

    incrementQuantity(theCartItem: CartItem) {

        theCartItem.quantity++;
        this.computeCartTotals();

    }

    remove(theCartItem: CartItem) {

        // get index of item in the array
        const itemIndex = this.cartItems.findIndex( tempCartItem => tempCartItem.prodId === theCartItem.prodId );
        // if found, remove the item from the array at the given index
        if (itemIndex > -1) {
            this.cartItems.splice(itemIndex, 1);
            this.computeCartTotals();
        }
    }

    getCartItemsById(userId: string) {

    }

}
