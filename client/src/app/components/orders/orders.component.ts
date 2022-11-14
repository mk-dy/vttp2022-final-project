import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { OrderResponse } from 'src/app/models';
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

  ngOnInit(): void {
    this.userId = this.tokenSvc.getUser().id
    //

    // this.orderSvc.getOrders(this.userId).subscribe(
    //   data => {
    //     this.orderList = data as OrderResponse[]
    //   },
    // )

    this.orderSvc.getOrders(this.userId).then(result => {
      // let ord = result as OrderResponse[]
      // console.log(ord)
      this.orderList = result as OrderResponse[]
    })

    console.info('>>>> in orders',this.orderList)
    
  }

}
