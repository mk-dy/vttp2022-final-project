import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Order, OrderResponse } from 'src/app/models';
import { OrderService } from 'src/app/services/order.service';
import { TokenStorageService } from 'src/app/services/token-storage.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {

  constructor(private route: Router, private orderSvc: OrderService, private tokenSvc: TokenStorageService) { }

  userId!: string
  orderList: OrderResponse[] = []
  orders: OrderResponse[] = []
  // uniqueOrder: OrderResponse[] = []



  ngOnInit(): void {
    this.userId = this.tokenSvc.getUser().id
    // this.orderSvc.getOrders(this.userId).then(result => {

    //   this.orderList = result as OrderResponse[]
    //   // this.uniqueOrder = [...new Set(result as OrderResponse[])]
    // })

    this.orderSvc.getOrderList(this.userId).then(result => {
      this.orders = result as OrderResponse[]
    })

    console.info('>>>> in orders',this.orders)
    
  }

}
