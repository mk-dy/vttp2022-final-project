import { Address, Order, OrderItem, User } from "../models";


export class Purchase {
    user: User = new User;
    shippingAddress!: Address;
    billingAddress!: Address;
    order!: Order;
    orderItems!: OrderItem[]; 
}
