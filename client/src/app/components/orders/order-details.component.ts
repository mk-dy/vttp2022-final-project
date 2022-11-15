import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { OrderItemResponse, OrderResponse } from 'src/app/models';
import { OrderService } from 'src/app/services/order.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-order-details',
  templateUrl: './order-details.component.html',
  styleUrls: ['./order-details.component.css']
})
export class OrderDetailsComponent implements OnInit {

  constructor(private orderSvc: OrderService, private tokenSvc: TokenStorageService, private ar: ActivatedRoute) { }

  userId!: string
  orderList: OrderItemResponse[] = []

  orderNo!: string

  ngOnInit(): void {
    console.info('>>>> in ngOnInit = ', this.ar.snapshot.params)
    // this.userId = this.tokenSvc.getUser().id
    // //
    // console.info('>>>> userId', this.userId)

    // // this.orderSvc.getOrders(this.userId).subscribe(
    // //   data => {
    // //     this.orderList = data as OrderResponse[]
    // //   },
    // // )
    // this.orderSvc.getOrders(this.userId).then(result => {
    //   this.orderList = result as OrderResponse[]
    // })
    
    this.ar.params.subscribe((param) => {
      this.orderNo = param['id']
    })

    console.log(this.orderNo)
    this.orderSvc.getOrderItems(this.orderNo).then(result => {
      this.orderList = result as OrderItemResponse[]
    })

    // // loop through and if orderList does not contain orderId, rmeove it
    // for (let order of this.orderList) {
    //   if (order.orderTrackingNumber === this.orderNo!) {
    //     this.newOrder.push(order)
    //   }
    // }
    // console.log(this.orderList)
    // console.log(this.newOrder)
    

  }

}
